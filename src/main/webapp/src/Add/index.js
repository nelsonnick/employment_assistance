import Vue from 'vue'
import iView from 'iview'
import App from './add.vue'
import 'iview/dist/styles/iview.css'   // 使用 CSS
Vue.use(iView)

/* eslint-disable no-new  */
new Vue({
  el: '#app',
  render: h => h(App)
})
