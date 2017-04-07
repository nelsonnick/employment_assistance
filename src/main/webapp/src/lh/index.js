import Vue from 'vue'
import iView from 'iview'
import App from './app.vue'

Vue.use(iView)

/* eslint-disable no-new  */
new Vue({
  el: '#app',
  render: h => h(App)
})
