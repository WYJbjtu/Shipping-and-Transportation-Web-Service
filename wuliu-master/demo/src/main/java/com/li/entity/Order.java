package com.li.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("t_order")
@Data
public class Order {
  //id自增长
  @TableId(type = IdType.AUTO)
  private Long id;
  private String senderName;//寄件人
  private String senderPhone;//寄件人电话
  private String senderAddress;//寄件地址
  private String addresseeName;//收件人
  private String addresseePhone;//收件人电话
  private String addresseeAddress;//收件地址
  private String productName;//货物名称
  private double weight;//重量
  private double price;//费用
  private Long expresspointsId;//站点ID
  private String state;//状态
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date sendTime;//发件时间
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date checkTime;//签收时间
  private String carType;//车辆类型
  private String driverName;//司机
  private Long orderNo;//运单编号

}
