<template>
    <v-layout row>
      <v-flex xs8>
        <coderoom></coderoom>
      </v-flex>
      <v-flex xs4>
        <chatroom></chatroom>
      </v-flex>
    </v-layout>
</template>

<script>
  import chatroom from '~/components/chatroom.vue'
  import coderoom from '~/components/coderoom.vue'

  import { mapState, mapMutations } from 'vuex'

  export default {
    computed: {
      ...mapState('chat', [
        'messages'
      ])
    },
    components: {
      'coderoom': coderoom,
      'chatroom': chatroom
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
