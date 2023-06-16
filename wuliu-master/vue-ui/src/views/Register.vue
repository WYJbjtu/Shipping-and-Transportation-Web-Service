<template>
  <div style="width: 100%; height: 100vh; overflow: hidden;" :style="backGround">
  <div style="width: 400px;  left: 50%;
  top: 50%;    transform: translate(-50%, -50%); position: absolute; ">

      <el-form ref="form" :model="form" :rules="registerRules"  class="login-form">
        <h2 style="margin-bottom:15px">欢迎注册</h2>


        <el-form-item  prop="userType">
          <div style="display: flex;margin-left:10%;">

            <el-select v-model="form.userType" placeholder="请选择用户类型" clearable
                       :style="{width: '80%'}" @change="selectOptions($event)">
              <el-option v-for="(item, index) in arr" :key="index" :label="item.name"
                         :value="item.name" ></el-option>
            </el-select>

          </div>
        </el-form-item>


        <el-form-item prop="name">
          <el-input prefix-icon="el-icon-user-solid" style="width: 80%;"  :placeholder="regUsrTitle" v-model="form.name"></el-input>
        </el-form-item>





        <el-form-item prop="userName">

          <div style="display: flex">
            <el-input prefix-icon="el-icon-user-solid"  style="width: 80%;margin-left: 37px"  placeholder="用户名"  v-model="form.userName"></el-input>
          </div>

        </el-form-item>


        <el-form-item prop="userPhone">

          <div style="display: flex">
            <el-input prefix-icon="el-icon-user-solid"  style="width: 80%;margin-left: 37px"  placeholder="联系电话"  v-model="form.userPhone"></el-input>
          </div>

        </el-form-item>


        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" style="width: 80%;" v-model="form.password"  placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        <el-form-item prop="confirm">
          <el-input prefix-icon="el-icon-lock" style="width: 80%;" v-model="form.confirm"  placeholder="请输入重复密码" show-password></el-input>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 80%" type="primary" @click="register">注册</el-button>
        </el-form-item>

        <el-form-item>
          <el-link type="primary" @click="login">已有账号?点击登录</el-link>
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
    var checkPassword = (rule, value, cb) => {
      // 验证邮箱的正则表达式
      const regPassword = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/

      if (regPassword.test(value)) {
        // 合法的密码
        return cb()
      }

      cb(new Error('请输入合法的密码：6~18位的字母+数字组合'))
    }


    return {
      regUsrTitle:'请输入托运人姓名',
      form: {},
      arr: [{ name: '托运人' }, { name: '快递点' }],
      registerRules: {
        name:[
          {
            required: true,
            message: '姓名不能为空',
            trigger: 'blur'
          },
        ],
        userName:[
          {
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          },
        ],
        stuId:[
          {
            required: true,
            message: '学号不能为空',
            trigger: 'blur'
          },
        ],


        password:[
          {required: true, message: '请输入密码', trigger: 'blur'},
          {validator: checkPassword, trigger: 'blur'}
        ],
        confirm:[
          {
            required: true,
            message: '重复密码不能为空',
            trigger: 'blur',
          },
        ],
      },
      backGround: {
        backgroundImage: "url(" + backImg + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "100% 100%"
      }
    }
  },
  methods:{

    selectOptions(event){
       if(event==='托运人'){
         this.regUsrTitle='请输入托运人姓名';
       }else{
         this.regUsrTitle='请输入站点名称';
       }
    },

    //获取用户名
    getUserName() {
      if (this.form.name == null || this.form.stuId == null) {
        ElMessage.error("请先输入姓名和学号");
        return;
      }
      request.post('/accounts/getUserName',this.form).then(res => {
        console.log(res);
        if (res.code === '0') {
          this.form.userName = res.data;
        }else {
          ElMessage.error(res.msg)
        }
      });
    },
    //登录
    login() {
      this.$router.push("/login")
    },
    //注册
    register() {
      if (this.form.password !== this.form.confirm) {
        ElMessage({
          type:'error',
          message: '2次密码输入不一致'
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if (valid) {
           debugger
          let utype=this.form.userType
          if(utype==='快递点'){
            this.form.expresspointsId=Date.now()
          }

          request.post("/accounts/register", this.form).then(res => {
            console.log(res);
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/login")  //登录成功之后进行页面的跳转，跳转到主页
            } else {
              this.$message({
                type: "error",
                message: res.data.msg
              })
            }
          })
        }
      });
    },
  }
}
</script>

<style scoped>
.login-form {
  display: flex;
  position: relative;
  width: 450px;
  height: 450px;
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
