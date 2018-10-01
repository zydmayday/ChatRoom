<template>
  <div class="messages">
    <div class="messages-content"></div>
  </div>
</template>

<script>
  import SockJS from 'sockjs-client'
  import Stomp from 'webstomp-client'
  import { mapMutations } from 'vuex'
  import { mapFields } from 'vuex-map-fields'

  export default {
    computed: {
      ...mapFields('chat', [
        'sendMessage'
      ])
    },
    methods: {
      ...mapMutations({
        addMessage: 'chat/addMessage'
      }),
      send () {
        console.log('Send message:' + this.sendMessage)
        if (this.stompClient && this.stompClient.connected) {
          const msg = { message: this.sendMessage }
          this.stompClient.send('/summer/talk', JSON.stringify(msg), {})
        }
      },
      connect () {
        this.socket = new SockJS('http://localhost:5678/summer-talk')
        this.stompClient = Stomp.over(this.socket)
        const that = this
        this.stompClient.connect(
          {},
          function (frame) {
            console.log(frame)
            that.stompClient.subscribe('/topic/nonsense', function (tick) {
              console.log(tick)
              // this.received_messages.push(JSON.parse(tick.body).content)
              that.addMessage({
                newMessage: tick
              })
            })
          },
          error => {
            console.log(error)
          }
        )
      }
    },
    mounted () {
      this.connect()
    }
  }
</script>

<style type="text/css" lang="scss">
/*--------------------
      Messages
--------------------*/
.messages {
  flex: 1 1 auto;
  color: rgba(255, 255, 255, .5);
  overflow: hidden;
  position: relative;
  width: 100%;

  & .messages-content {
    position: absolute;
    top: 0;
    left: 0;
    height: 101%;
    width: 100%;
  }


  .message {
    clear: both;
    float: left;
    padding: 6px 10px 7px;
    border-radius: 10px 10px 10px 0;
    background: rgba(0, 0, 0, .3);
    margin: 8px 0;
    font-size: 11px;
    line-height: 1.4;
    margin-left: 35px;
    position: relative;
    text-shadow: 0 1px 1px rgba(0, 0, 0, .2);

    .timestamp {
      position: absolute;
      bottom: -15px;
      font-size: 9px;
      color: rgba(255, 255, 255, .3);
    }

    &::before {
      content: '';
      position: absolute;
      bottom: -6px;
      border-top: 6px solid rgba(0, 0, 0, .3);
      left: 0;
      border-right: 7px solid transparent;
    }

    .avatar {
      position: absolute;
      z-index: 1;
      bottom: -15px;
      left: -35px;
      border-radius: 30px;
      width: 30px;
      height: 30px;
      overflow: hidden;
      margin: 0;
      padding: 0;
      border: 2px solid rgba(255, 255, 255, 0.24);

      img {
        width: 100%;
        height: auto;
      }
    }

    &.message-personal {
      float: right;
      color: #fff;
      text-align: right;
      background: linear-gradient(120deg, #248A52, #257287);
      border-radius: 10px 10px 0 10px;

      &::before {
        left: auto;
        right: 0;
        border-right: none;
        border-left: 5px solid transparent;
        border-top: 4px solid #257287;
        bottom: -4px;
      }
    }

    &:last-child {
      margin-bottom: 30px;
    }

    &.new {
      transform: scale(0);
      transform-origin: 0 0;
      animation: bounce 500ms linear both;
    }

    &.loading {

      &::before {
        @include ball;
        border: none;
        animation-delay: .15s;
      }

      & span {
        display: block;
        font-size: 0;
        width: 20px;
        height: 10px;
        position: relative;

        &::before {
          @include ball;
          margin-left: -7px;
        }

        &::after {
          @include ball;
          margin-left: 7px;
          animation-delay: .3s;
        }
      }
    }

  }
}
</style>
