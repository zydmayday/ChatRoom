<template>
	<v-layout  justify-end>
		<v-flex md5>
			<v-textarea
		    rows="1"
		    label="message"
		    auto-grow outline
		  ></v-textarea>
		</v-flex>
		<v-flex md1>
		  <v-btn color="info">Info</v-btn>
		</v-flex>
	</v-layout>
</template>

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