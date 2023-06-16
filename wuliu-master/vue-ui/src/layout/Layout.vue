<template>
  <!--    头部区域-->
  <Header :user="user"/>
  <!--    主体区域-->
  <div style="display: flex" >
    <!--侧边栏-->
    <Aside/>
    <!--      内容区域-->
<!--    当用户信息改变时,直接刷新前端的用户数据-->
    <router-view style="flex : 1;"  @userInfo="refreshUser"/>
  </div>
</template>

<script>
import Header from "../components/Header";
import Aside from "../components/Aside";
import request from "../utils/request";

export default {
  name: "Layout",
  components: {
    Header,
    Aside
  },
  data() {
    return {
      user: {}

    }
  },
  created() {
    this.refreshUser()
  },
  methods: {
    refreshUser() {
      let userJson = sessionStorage.getItem("user");
      if (!userJson) {
        return
      }
      let userId = JSON.parse(userJson).id
      request.get("/accounts/" + userId).then(res => {
        this.user = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
