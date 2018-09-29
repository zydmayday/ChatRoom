<template>
    <v-layout row ma-0>
      <v-flex xs7>
        <coderoom></coderoom>
      </v-flex>
      <v-flex xs5>
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
