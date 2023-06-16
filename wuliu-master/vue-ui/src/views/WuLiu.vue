<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="addWuLiu">新增</el-button>
      <el-button type="primary" @click="exportWuliu">导出</el-button>
      <el-popconfirm
          title="确定删除吗？"
          @confirm="deleteBatch"
      >
        <template #reference>
          <el-button type="danger">批量删除</el-button>
        </template>
      </el-popconfirm>

    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="根据物流编号查询" style="width: 20%;" @clear="load" @keyup.enter="load"
                clearable></el-input>
      <el-button style="margin-left: 10px" type="primary" icon="el-icon-search" @click="load" circle></el-button>
    </div>

    <el-table
        :data="tableData"
        border
        stripe
        @selection-change="handleSelectionChange"
    >
      <!--多选框-->
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="id"
                       label="物流编号"
                       sortable
      />
      <el-table-column prop="carType"
                       label="车辆类型"/>

      <el-table-column prop="name"
                       label="司机姓名"/>

      <el-table-column prop="formgo"
                       label="出发地"/>

      <el-table-column prop="togo"
                       label="目的地"/>


      <el-table-column prop="goTime"
                       label="发车时间"
                       sortable/>

      <el-table-column prop="goodType"
                       label="发货状态"/>

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

      <!--      弹窗-->
      <el-dialog
          v-model="dialogVisible"
          title="添加物流"
          width="30%"
      >
        <el-form :model="form" label-width="100px">
          <el-form-item label="车辆类型">
            <el-radio v-model="form.carType" label="普货">普货</el-radio>
            <el-radio v-model="form.carType" label="特快小车">特快小车</el-radio>
            <el-radio v-model="form.carType" label="货运飞机">货运飞机</el-radio>
          </el-form-item>
          <el-form-item label="司机姓名">
            <el-input v-model="form.name" style="width: 80%;"></el-input>
          </el-form-item>
          <el-form-item label="出发地点">
            <el-input v-model="form.formgo" style="width: 80%;"></el-input>
          </el-form-item>
          <el-form-item label="目的地点">
            <el-input v-model="form.togo" style="width: 80%;"></el-input>
          </el-form-item>
          <el-form-item label="发车时间">
            <el-input v-model="form.goTime" value-format="YYYY-MM-DD" type="date" style="width: 80%"
                      clearable></el-input>
          </el-form-item>
          <el-form-item label="发货状态">
            <el-radio-group v-model="form.goodType">
            <el-radio-button label="已发货"></el-radio-button>
            <el-radio-button label="未发货"></el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveWuLiu"
        >确认</el-button
        >
      </span>
        </template>
      </el-dialog>


    </div>
  </div>
</template>

<script>

import request from "../utils/request";
import {ElMessage} from 'element-plus';
import XLSX from 'xlsx'
import FileSaver from 'file-saver'

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      user: {},
      form: {},
      dialogVisible: false,
      currentPage: 1,
      total: 0,
      search: '',
      tableData: [],
      pageSize: 10,
      multipleSelection: [],
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("/wuliu",
          //get方式需要添加params属性
          {
            params: {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              search: this.search
            }
          }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.records;
          //当发货时间为空时,提示暂未发货
          this.tableData.forEach(function (item){
            if (item.goTime == null){
              item.goTime = "暂未发货"

            }
          })
          this.total = res.data.total;
        }

      })
    },

    addWuLiu() {
      this.dialogVisible = true;
      //格式化表单 form
      this.form = {};
    },
    saveWuLiu() {
      if (this.form.id) {  // 更新
        request.put("/wuliu", this.form).then(res => {
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      } else {  // 新增
        request.post("/wuliu", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }

          this.load() // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      }
    },
    deleteBatch() {
      if (!this.multipleSelection.length) {
        ElMessage.error("请选择数据!")
        return;
      }
      request.post("/wuliu/deleteBatch", this.multipleSelection).then(res => {
        if (res.code === '0') {
          ElMessage.success("批量删除成功");
          this.load()
        }else {
          ElMessage.error(res.msg);
        }
      })
    },
    handleSelectionChange(val) {
      //val.map(v => v.id)只取出id
      this.multipleSelection = val.map(v => v.id)
    },
    handleEdit(row) {
      //深拷贝
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },

    handleDelete(id) {
      request.delete("/wuliu/" + id).then(res => {
        console.log(res);
        if (res.code === '0') {
          ElMessage({
            message: '删除成功!',
            type: 'success',
          })
        } else {
          ElMessage({
            message: '删除失败!',
            type: 'error'
          })
        }
        this.load();
      });
    },

    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum;
      this.load();
    },

    exportWuliu() {
      this.excelData = this.tableData; // this.tableData替换成页面表格的数据列表
      this.export2Excel(); //调用export2Excel函数，
    },
//表格数据写入excel
    export2Excel() {
      var that = this;
      require.ensure([], () => {
        const {export_json_to_excel,} = require("../vendor/Export2Excel");

        //表头
        const tHeader = [
          "物流编号","车辆类型","司机姓名","发货地点","目的地点","发车时间","发货状态"];
        //出的excel表头字段名，这个必须和数据表格的字段一致
        const filterVal = [
          "id","carType","name","formgo","togo","goTime",
          "goodType"];

        const list = that.excelData;
        //格式转换
        const data = that.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, "物流信息"); //导出的表格名称
      });
    },
//格式转换，直接复制即可,不需要修改什么
    formatJson(filterVal, jsonData) {
      return jsonData.map((v) => filterVal.map((j) => v[j]));
    }
  }
}
</script>


<style scoped>
</style>
