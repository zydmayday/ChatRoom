<template>
	<div>
		<v-chip outline color="primary">CWS</v-chip>
		<v-layout row wrap>
			<v-flex xs4 xs4>
			  <v-select
			    :items="items"
			    label="Solo field"
			    solo
			    :class="select-version"
			  ></v-select>
			</v-flex>
		  <v-flex xs4 xs4>
			  <v-select
			    :items="items"
			    label="Solo field"
			    solo
			    :class="select-version"
			  ></v-select>
			</v-flex>
			<v-flex xs4 xs4>
			  <v-select
			    :items="items"
			    label="Solo field"
			    solo
			    :class="select-version"
			  ></v-select>
			</v-flex>
		</v-layout>
	</div>
</template>

<style type="text/css">
	.select-version {
		width: 30%;
	}
</style>