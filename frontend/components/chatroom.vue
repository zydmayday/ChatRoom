<!-- chatroom的内容发送框 -->
<template>
  <v-layout column fill-height>
    <div class="chat">
      <chatroom-title></chatroom-title>
      <div class="messages">
        <div class="messages-content"></div>
      </div>
      <div class="message-box">
        <textarea type="text" class="message-input" placeholder="Eureka..!"></textarea>
        <button type="submit" class="message-submit">Send</button>
      </div>
    </div>
  </v-layout>
</template>

<script>
  import SockJS from 'sockjs-client'
  import Stomp from 'webstomp-client'
  import { mapMutations } from 'vuex'
  import { mapFields } from 'vuex-map-fields'
  import ChatTitle from './chatroom-title.vue'

  export default {
    computed: {
      ...mapFields('chat', [
        'sendMessage'
      ])
    },
    components: {
      'chatroom-title': ChatTitle
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
Mixins
--------------------*/
@mixin center {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

@mixin ball {
  @include center;
  content: '';
  display: block;
  width: 3px;
  height: 3px;
  border-radius: 50%;
  background: rgba(255, 255, 255, .5);
  z-index: 2;
  margin-top: 4px;
  animation: ball .45s cubic-bezier(0, 0, 0.15, 1) alternate infinite;
}


/*--------------------
Chat
--------------------*/
.chat {
  @include center;
  width: 100%;
  height: 100%;
  z-index: 2;
  overflow: hidden;
  box-shadow: 0 5px 30px rgba(0, 0, 0, .2);
  background: rgba(0, 0, 0, .5);
  // border-radius: 20px;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  position: relative;
}


/*--------------------
Chat Title
--------------------*/
.chat-title {
  flex: 0 1 45px;
  position: relative;
  z-index: 2;
  background: rgba(0, 0, 0, 0.2);
  color: #fff;
  text-transform: uppercase;
  text-align: left;
  padding: 10px 10px 10px 50px;

  h1, h2 {
    font-weight: normal;
    font-size: 10px;
    margin: 0;
    padding: 0;
  }

  h2 {
    color: rgba(255, 255, 255, .5);
    font-size: 8px;
    letter-spacing: 1px;
  }

  .avatar {
    position: absolute;
    z-index: 1;
    top: 8px;
    left: 9px;
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
}


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


/*--------------------
      Message Box
--------------------*/
.message-box {
  flex: 0 1 42px;
  width: 100%;
  background: rgba(21, 106, 99, 0.1);
  padding: 2px 10px;
  position: relative;

  & .message-input {
    position: relative;
    top: 4px;
    left: 5px;
    background: white;
    border: solid 2px;
    border-radius: 5px;
    outline: none!important;
    resize: none;
    color: #17a2b8;
    font-size: 14px;
    height: 31px;
    margin: 0;
    padding: 3px 10px;
    width: 80%;
    overflow: hidden;
  }

  textarea:focus:-webkit-placeholder{
      color: transparent;
  }

  & .message-submit {
    position: absolute;
    z-index: 1;
    top: 6px;
    right: 10px;
    color: #fff;
    border: none;
    background: #248A52;
    font-size: 12px;
    text-transform: uppercase;
    line-height: 1.5;
    padding: 6px 10px;
    border-radius: 15px;
    outline: none!important;
    transition: background .2s ease;
    width: 60px;

    &:hover {
      background: #1D7745;
    }
  }
}


/*--------------------
Custom Srollbar
--------------------*/
.mCSB_scrollTools {
  margin: 1px -3px 1px 0;
  opacity: 0;
}

.mCSB_inside > .mCSB_container {
  margin-right: 0px;
  padding: 0 10px;
}

.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar {
  background-color: rgba(0, 0, 0, 0.5)!important;
}


/*--------------------
Bounce
--------------------*/
@keyframes bounce {
  0% { transform: matrix3d(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  4.7% { transform: matrix3d(0.45, 0, 0, 0, 0, 0.45, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  9.41% { transform: matrix3d(0.883, 0, 0, 0, 0, 0.883, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  14.11% { transform: matrix3d(1.141, 0, 0, 0, 0, 1.141, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  18.72% { transform: matrix3d(1.212, 0, 0, 0, 0, 1.212, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  24.32% { transform: matrix3d(1.151, 0, 0, 0, 0, 1.151, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  29.93% { transform: matrix3d(1.048, 0, 0, 0, 0, 1.048, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  35.54% { transform: matrix3d(0.979, 0, 0, 0, 0, 0.979, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  41.04% { transform: matrix3d(0.961, 0, 0, 0, 0, 0.961, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  52.15% { transform: matrix3d(0.991, 0, 0, 0, 0, 0.991, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  63.26% { transform: matrix3d(1.007, 0, 0, 0, 0, 1.007, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  85.49% { transform: matrix3d(0.999, 0, 0, 0, 0, 0.999, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
  100% { transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
}


@keyframes ball {
  from {
    transform: translateY(0) scaleY(.8);
  }
  to {
    transform: translateY(-10px);
  }
}
</style>
