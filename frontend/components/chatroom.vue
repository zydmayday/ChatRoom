<!-- chatroom的内容发送框 -->
<template>
  <v-layout column fill-height>
    <v-flex>
      <v-card height="100%">
        <v-toolbar color="indigo" dark>
          <v-toolbar-title>CHATROOM-TITLE</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon>
            <v-icon>search</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text
          class="scroll-y"
        >
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
      </v-card>
    </v-flex>
    <v-flex align-center xs1 class="sender">
      <v-layout row fill-height>
        <v-flex  xs9>
          <v-textarea
            rows="1"
            label="message"
            v-model="sendMessage"
            auto-grow
          ></v-textarea>
        </v-flex>
        <v-flex xs3>
          <v-btn
            color="info"
            @click="send">Info</v-btn>
          </v-flex>
      </v-layout>
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
  .sender {height: 0vh !important;}
</style>
