<template>
<v-app

  id="inspire"
>
  <v-navigation-drawer fixed clipped app
    v-model="drawer"
    :width="200"
  >
    <v-list dense>
      <v-list-tile
        v-for="item in items"
        :key="item.text"
        @click=""
        :to="item.url"
      >
        <v-list-tile-action>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-list-tile-title>
            {{ item.text }}
          </v-list-tile-title>
        </v-list-tile-content>
      </v-list-tile>
      <v-list-tile class="mt-3" @click="">
        <v-list-tile-action>
          <v-icon color="grey darken-1">add_circle_outline</v-icon>
        </v-list-tile-action>
        <v-list-tile-title class="grey--text text--darken-1">Browse Channels</v-list-tile-title>
      </v-list-tile>
      <v-list-tile @click="">
        <v-list-tile-action>
          <v-icon color="grey darken-1">settings</v-icon>
        </v-list-tile-action>
        <v-list-tile-title class="grey--text text--darken-1">Manage Subscriptions</v-list-tile-title>
      </v-list-tile>
    </v-list>
  </v-navigation-drawer>
  <v-toolbar
    color="red"
    dense
    fixed
    clipped-left
    app
  >
    <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
    <v-icon class="mx-3">fa-youtube</v-icon>
    <v-toolbar-title class="mr-5 align-center">
      <span class="title">Summer</span>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-layout row align-center style="max-width: 650px">
      <v-text-field
        placeholder="Search..."
        single-line
        append-icon="search"
        :append-icon-cb="() => {}"
        color="white"
        hide-details
      ></v-text-field>
    </v-layout>
  </v-toolbar>
  <v-content class="main-content">
    <v-container fill-height pa-0 ma-0 style="max-width: 100%">
      <nuxt />
    </v-container>
  </v-content>
</v-app>
</template>

<script>
export default {
  data: () => ({
    drawer: true,
    items: [
      { icon: 'trending_up', text: 'Main Page', url: '/' },
      { icon: 'trending_up', text: 'PTF Page', url: '/ptf' }
    ],
    items2: [
      { picture: 28, text: 'Joseph' },
      { picture: 38, text: 'Apple' },
      { picture: 48, text: 'Xbox Ahoy' },
      { picture: 58, text: 'Nokia' },
      { picture: 78, text: 'MKBHD' }
    ]
  }),
  props: {
    source: String
  }
}
</script>

<style type="text/css">
  .main-content {
    overflow: hidden;
  }
</style>
