<template>
	<v-card flat>
    <v-card-text>
      <v-container fluid>
        <v-layout row wrap>
          <v-flex xs12 sm4 md4>
            <v-switch
              v-model="cjk"
              label="CJK"
              color="primary"
              hide-details
            ></v-switch>
          </v-flex>
          <v-flex xs12 sm4 md4>
            <v-switch
              v-model="cws"
              label="CWS"
              color="success"
              hide-details
            ></v-switch>
          </v-flex>
          <v-flex xs12 sm4 md4>
            <v-switch
              v-model="csr"
              label="CSR"
              color="warning"
              hide-details
            ></v-switch>
          </v-flex>
        </v-layout>
        <v-layout row wrap>
          <ptf-form-setting-selector-version></ptf-form-setting-selector-version>
        </v-layout>
        <v-layout row wrap>
          <ptf-form-setting-selector-version></ptf-form-setting-selector-version>
        </v-layout>
      </v-container>
    </v-card-text>
  </v-card>
</template>

<script>
  import PtfFormSettingSelectorVersion from '~/components/ptf-form-setting-selector-version.vue'
  export default {
    data () {
      return {
        cjk: true,
        cws: true,
        csr: false
      }
    },
    components: {
      PtfFormSettingSelectorVersion
    }
  }
</script>