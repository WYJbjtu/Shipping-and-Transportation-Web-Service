<template>
  <div style="width: 100%; height: 100vh; overflow: hidden;" :style="backGround">

    <div style="width: 400px; left: 50%;
  top: 50%;    transform: translate(-50%, -50%); position: absolute; ">
      <el-form ref="form" :model="form" class="login-form" @keyup.enter="login()" :rules="rules">
        <h2 style="margin-bottom:15px">欢迎登录</h2>

        <el-form-item>
          <el-input style="width: 80%;"  placeholder="请输入用户名" prefix-icon="el-icon-user-solid" v-model="form.userName"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" style="width: 80%;" v-model="form.password" show-password></el-input>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 80%;" type="primary" @click="login">登录
          </el-button>
        </el-form-item>

        <el-form-item>
          <el-link type="primary" @click="register">没有账号?点击注册</el-link>
        </el-form-item>

      </el-form>
    </div>

  </div>
</template>

<script>
import {ElMessage} from "element-plus";
import request from "../utils/request";

import backImg from "../assets/img.png"

export default {
  name: "Login",
  data() {
    return {
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      },
      backGround: {
        // backgroundImage: "url(" + require("@/assets/bg.jpg") + ")",
        backgroundImage: "url(" + backImg + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "100% 100%"
      }
    }
  },

  methods: {
    login() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/accounts/login", this.form).then(res => {
            if (res.code === '0') {
              ElMessage({
                message: '登录成功!',
                type: 'success',
              })
              //缓存登录用户
              sessionStorage.setItem("user", JSON.stringify(res.data))
              //登录成功后跳转到主页
              this.$router.push("/person");
            } else {
              ElMessage({
                message: res.msg,
                type: 'error'
              })
            }
          })

        }
      })
    },
    //注册按钮
    register() {
      this.$router.push("/register")
    }
  }
}
</script>

<style scoped>
.login-form {
  display: flex;
  position: relative;
  width: 450px;
  height: 350px;
  flex-direction: column;
  padding: 40px;
  text-align: center;
  z-index: 1;
  background: inherit;
  border-radius: 18px;
  overflow: hidden;
}

.login-form::before {
  content: '';
  position: absolute;
  top: -10px;
  left: -10px;
  width: calc(100% + 20px);
  /* + 两边各有一个空格  否则 无效*/
  height: calc(100% + 20px);
  background: inherit;
  box-shadow: 0 0 0 200px rgba(255, 255, 255, .2) inset;
  z-index: -1;
  filter: blur(6px);
  overflow: hidden;
}

.login-form h2 {
  font-size: 18px;
  font-weight: 400;
  color: #3d5245;
}

</style>
