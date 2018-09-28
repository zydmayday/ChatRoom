<!-- chatroom的内容发送框 -->
<template>
  <v-layout justify-end>
    <v-flex xs12 sm8 md6>
      <v-card>
        <v-card-title primary-title>
          <v-toolbar color="indigo" dark>
            <v-toolbar-title>CHATROOM-TITLE</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon>
              <v-icon>search</v-icon>
            </v-btn>
          </v-toolbar>
        </v-card-title>
        <v-card-text
          class="scroll-y"
          style="max-height: 300px"
        >
          <v-layout column
            style="height: 1000px"
          >
            <p
              v-for="msg in messages"
            >
              {{ msg }}
            </p>
          </v-layout>
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
      </v-card>
    </v-flex>
    <v-flex md5>
      <v-textarea
        rows="1"
        label="message"
        v-model="sendMessage"
        auto-grow outline
      ></v-textarea>
    </v-flex>
    <v-flex md1>
      <v-btn
        color="info"
        @click="send">Info</v-btn>
    </v-flex>
  </v-layout>
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
