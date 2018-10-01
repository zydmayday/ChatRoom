<!-- chatroom的内容发送框 -->
<template>
  <div class="chat-title">
    <h1>Summer</h1>
    <h2>Chatroom</h2>
    <!-- <figure class="avatar">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/156381/profile/profile-80.jpg" /></figure> -->
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

</style>
