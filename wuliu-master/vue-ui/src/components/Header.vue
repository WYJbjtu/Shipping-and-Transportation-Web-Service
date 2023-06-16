
<template>
  <!--  border-style（边框样式） solid 实线  #color-->
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #cccccc; display: flex">
    <div style="width: 200px; padding-left: 30px;font-weight: bold; color: deepskyblue;">物流后台管理</div>
    <!--    弹性布局平均分配空间,代表平均分配元素空间-->
    <div style="flex: 1"></div>

    <div style="width: 100px">
      <el-dropdown>
        <span class="el-dropdown-link">
            {{ user.name }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>

            <el-dropdown-item @click=logout>退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>

</template>

<script>
export default {
  name: "Header",
  props: ['user'],
  data() {
    return {};
  },
  created() {
  },
  methods:{
    logout() {
      this.$router.push('/login');
      sessionStorage.clear();
    }
  }
}
</script>

<style scoped>

</style>
