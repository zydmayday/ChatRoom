<template>
  <div class="message-box">
    <textarea type="text" class="message-input" placeholder="Eureka..!"></textarea>
    <button type="submit" class="message-submit" @click="send">Send</button>
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

</style>
