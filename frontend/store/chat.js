import { getField, updateField } from 'vuex-map-fields'

export const state = () => ({
  messages: ['test'],
  sendMessage: '',
  socket: null,
  stompClient: null
})

export const getters = {
  getField
}

export const mutations = {
  addMessage (state, { newMessage }) {
    state.messages.push(newMessage)
  },
  updateField
}
