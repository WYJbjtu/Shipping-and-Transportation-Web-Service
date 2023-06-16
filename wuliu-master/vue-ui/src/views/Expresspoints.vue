<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">

    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="根据姓名关键字查询" style="width: 20%;" @clear="load" @keyup.enter="load" clearable></el-input>
      <el-button style="margin-left: 10px" type="primary" icon="el-icon-search" @click="load" circle></el-button>
    </div>

    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column prop="id"
                       label="ID"
                       sortable
      />
      <el-table-column prop="name"
                       label="姓名"/>

      <el-table-column prop="userType"
                       label="用户类型"/>

      <el-table-column prop="userName"
                       label="用户名"/>

      <el-table-column prop="age"
                       label="年龄"/>

      <el-table-column prop="sex"
                       label="性别"/>

      <el-table-column prop="address"
                       label="地址"/>

      <el-table-column label="操作" width="200px">
        <template #default="scope">
          <el-button type="primary" style="margin-right: 20px" size="small" icon="el-icon-edit"
                     @click="handleEdit(scope.row)">编辑
          </el-button>

          <el-popconfirm title="确认删除此条数据吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small" icon="el-icon-delete">删除
              </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>
    <div>
      <!--      分页-->
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>

    </div>
  </div>
</template>

<script>

import request from "../utils/request";
import {ElMessage} from 'element-plus'

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      checkedValues:'站点',
      currentPage: 1,
      total: 0,
      search: '',
      tableData: [],
      pageSize: 10,
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("/accounts",
          //多个参数get方式需要添加params属性
          {
            params: {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              search: this.search,
              owner: '快递点'
            }
          }).then(res => {
        console.log(res);
        if (res.code === '0') {
          this.tableData = res.data.records;
          this.total = res.data.total;
        }

      })
    },

    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum;
      this.load();
    },

  }
}
</script>
