<template>
  <Card shadow>
    <p slot="title">欢迎使用</p>
    <Form inline>
      <Form-item>
        <Input type="text" v-model="login" placeholder="请输入用户名">
        <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </Form-item>
      <Form-item>
        <Input type="password" v-model="pass" placeholder="请输入密码">
        <Icon type="ios-locked-outline" slot="prepend"></Icon>
        </Input>
      </Form-item>
      <Form-item>
        <Button type="primary" @click="goLogin">登录</Button>
      </Form-item>
    </Form>
  </Card>
</template>
<script>
  import * as API from './API.js'
  export default {
    name: 'Login',
    data () {
      return {
        login: '',
        pass: ''
      }
    },
    methods: {
      goLogin () {
        if (this.login.toString() === '') {
          this.$Notice.error({
            title: '用户名不能为空!'
          })
        } else if (this.pass.toString() === '') {
          this.$Notice.error({
            title: '密码不能为空!'
          })
        } else {
          this.$http.get(
            API.login,
            { params: {
              login: this.login,
              pass: this.pass
            } },
            { headers: { 'X-Requested-With': 'XMLHttpRequest' } }
          ).then((response) => {
            switch (response.body) {
              case 'OK':
                window.location.href = '/person'
                break
              case 'noPower':
                this.$Notice.error({
                  title: '用户无管理权限!'
                })
                break
              case 'error':
                this.$Notice.error({
                  title: '用户名或密码错误!'
                })
                break
              default:
                window.location.href = '/'
            }
          }, (response) => {
            this.$Notice.error({
              title: '服务器内容错误!'
            })
          })
        }
      }
    }
  }
</script>

