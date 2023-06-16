<template>
  <!-- calc() 能计算的计算表达式里，计算符号两边要有空格
  default-active 高亮
  default-opends 默认展开-->
  <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      :default-active="path"
      router
  >
      <el-menu-item index="/user" v-show="isShow">
        <i class="el-icon-user"></i>
        <span>用户管理</span>
      </el-menu-item>

    <el-menu-item index="/expresspoints" v-show="isShow">
      <i class="el-icon-user"></i>
      <span>站点管理</span>
    </el-menu-item>

      <el-menu-item index="/person">
        <i class="el-icon-user-solid"></i>
        <span>个人信息</span>
      </el-menu-item>

      <el-menu-item index="/order" v-show="isUsrType">
        <i class="el-icon-truck"></i>
        <span>快件下单</span>
      </el-menu-item>

    <el-menu-item index="/orderList">
      <i class="el-icon-truck"></i>
      <span>运单管理</span>
    </el-menu-item>

  </el-menu>
</template>

<script>
export default {
  name: "",
  data() {
    return {
      isShow:false,
      isUsrType:false,
      user: {},
      path: this.$route.path   // 设置默认高亮的菜单为当前路由
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    let usrobj = JSON.parse(str)
    let usrType=usrobj.userType;
    if(usrType=='admin'){
      this.isShow = true
      this.isUsrType = false
    }
    if(usrType=='托运人'){
      this.isShow = false
      this.isUsrType = true
    }
  }
}
</script>

<style scoped>

</style>
