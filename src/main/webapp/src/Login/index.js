import Vue from 'vue'
import iView from 'iview'
import Login from './Login.vue'
import VueResource from 'vue-resource'
import 'iview/dist/styles/iview.css'   // 使用 CSS

Vue.use(iView)
Vue.use(VueResource)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  template: '<Login/>',
  components: { Login }
})
