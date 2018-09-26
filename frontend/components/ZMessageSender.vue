<!-- chatroom的内容发送框 -->
<template>
  <v-layout justify-end>
    <v-flex md5>
      <v-textarea
        rows="1"
        label="message"
        v-model="sendMessage"
        auto-grow outline
      ></v-textarea>
    </v-flex>
    <v-flex md1>
      <v-btn color="info">Info</v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
  import SockJS from 'sockjs-client'
  import Stomp from 'webstomp-client'
  import { mapActions, mapMutations } from 'vuex'
  import { mapFields } from 'vuex-map-fields'

  export default {
    computed: {
      ...mapFields('chat', [
        'sendMessage'
      ])
    },
    methods: {
      ...mapActions('chat', [
        'send'
      ]),
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
        this.stompClient.connect(
          {},
          frame => {
            console.log(frame)
            this.stompClient.subscribe('/topic/nonsense', tick => {
              console.log(tick)
              this.received_messages.push(JSON.parse(tick.body).content)
              this.addMessage(tick)
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

<style type="text/css">
  .fixed {
    position: fixed;
  }
  .bottom {
    bottom: 10px;
  }
  .right {
    right: 10px;
  }
  .message-sender {
    width: 40%;
  }
</style>
