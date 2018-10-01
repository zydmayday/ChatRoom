<template>
  <v-layout row wrap>
    <v-flex xs6>
      <ptf-form-setting></ptf-form-setting>
    </v-flex>
    <v-flex xs6>
      <ptf-progress-result></ptf-progress-result>
    </v-flex>
  </v-layout>
</template>

<script>
  import PtfFormSetting from '~/components/ptf-form-setting.vue'
  import PtfProgressResult from '~/components/ptf-progress-result.vue'

  export default {
    components: {
      'ptf-form-setting': PtfFormSetting,
      'ptf-progress-result': PtfProgressResult
    }
  }
</script>
