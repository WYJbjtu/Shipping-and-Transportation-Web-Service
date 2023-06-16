package com.li.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_user")
@Data
public class User {
  //id自增长
  @TableId(type = IdType.AUTO)
  private Integer id;
  private String name;
  private String userName;
  private String password;
  private String stuId;
  private Integer age;
  private String sex;
  private String address;
  private String userType;
  private Long expresspointsId;
  private String userPhone;
}
