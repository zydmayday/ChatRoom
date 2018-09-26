<template>
  <v-container>
    <v-layout justify-center align-center>
      <v-flex xs12 sm8 md6>
        <v-card>
          <v-card-title primary-title>
            <v-toolbar dark
              color="indigo">
              <v-toolbar-title>CODE</v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>search</v-icon>
              </v-btn>
            </v-toolbar>
          </v-card-title>
          <v-card-text>
            <p>123</p>
          </v-card-text>
          <v-card-actions>
          </v-card-actions>
        </v-card>
      </v-flex>
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
    </v-layout>
    <z-message-sender></z-message-sender>
  </v-container>
</template>

<script>
  import ZMessageSender from '~/components/ZMessageSender.vue'
  import { mapState, mapMutations } from 'vuex'

  export default {
    computed: {
      ...mapState('chat', [
        'messages'
      ])
    },
    components: {
      'z-message-sender': ZMessageSender
    },
    methods: {
      ...mapMutations('chat', [
        'addMessage'
      ])
    },
    mounted () {
      this.addMessage({
        newMessage: 'adfs'
      })
    }
  }
</script>

<style type="text/css">
</style>
