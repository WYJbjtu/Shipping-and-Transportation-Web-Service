<template>
  <div>
    <el-card style="width: 40%; margin: 10px">
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="寄件人">
          <el-input v-model="form.senderName" ></el-input>
        </el-form-item>
        <el-form-item label="寄件人电话" label-width="100px">
          <el-input v-model="form.senderPhone" ></el-input>
        </el-form-item>
        <el-form-item label="寄件人地址" label-width="100px">
          <el-input v-model="form.senderAddress" ></el-input>
        </el-form-item>

        <el-form-item label="收件人姓名" label-width="100px">
          <el-input v-model="form.addresseeName" ></el-input>
        </el-form-item>
        <el-form-item label="收件人电话" label-width="100px">
          <el-input v-model="form.addresseePhone" ></el-input>
        </el-form-item>
        <el-form-item label="收件人地址" label-width="100px">
          <el-input v-model="form.addresseeAddress"></el-input>
        </el-form-item>
        <el-form-item label="物品名称" label-width="100px">
          <el-input v-model="form.productName" ></el-input>
        </el-form-item>
        <el-form-item label="物品称重" label-width="100px">
          <el-input v-model="form.weight"  @input="handleEdit" style="width: 90%"></el-input>
          kg
        </el-form-item>
        <el-form-item label="费用" label-width="100px">
          <el-input v-model="form.price" style="width: 35%" disabled></el-input>
          <el-button  @click="getPrice" type="info" style="margin-left: 37px" >预估费用</el-button>
          3kg以内10元 超过 1kg增加1元
        </el-form-item>


        <el-form-item  prop="userType" label-width="100px">
          <div style="display: flex;margin-left:10%;">

            <el-select v-model="form.expresspointsId" placeholder="请选择快递站" clearable
                       :style="{width: '40%'}" >
              <el-option v-for="(item, index) in arr" :key="index" :label="item.name"
                         :value="item.expresspointsId" ></el-option>

            </el-select>

          </div>
        </el-form-item>

      </el-form>
      <div style="text-align: center">
   <el-button type="primary" @click="save">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "",
  data(){
    return{
      form:{},
      arr: []
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    let usrobj = JSON.parse(str)
    this.form.senderName = usrobj.name
    this.form.senderPhone = usrobj.userPhone
    this.form.senderAddress = usrobj.address
    this.selOptions()
  },
  methods:{
   // 在 Input 值改变时触发
    handleEdit(e) {

      let value = e.replace(/[^\d]/g, ""); // 只能输入数字
      value = value.replace(/^0+(\d)/, "$1"); // 第一位0开头，0后面为数字，则过滤掉，取后面的数字
      value = value.replace(/(\d{15})\d*/, '$1') // 最多保留15位整数
      this.form.weight = value
    },
    selOptions(){
      request.get("/accounts/findUsrAll",
          //get方式需要添加params属性
          {
            params: {
              search: '快递点'
            }
          }).then(res => {
            debugger
        if (res.code === '0') {
          this.arr = res.data;
        }
      })
    },
    getPrice() {
     let weight = this.form.weight
      if(weight!=null && weight!=''){
        //3kg以内10元 超过 1kg增加1元
        if(weight<=3){
          this.form.price = 10

        }else{
          let otherPrice = weight-3
          this.form.price = 10+otherPrice
        }
      }
    },
    save() {
      request.post("/order/save", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "保存成功"
          })
          //更新session携带的user信息
          sessionStorage.setItem("user", JSON.stringify(this.form))
          this.$router.push("/orderList")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
