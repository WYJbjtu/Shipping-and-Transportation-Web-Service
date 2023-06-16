package com.li.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("wuliu")
@Data
public class WuLiu {
  //id自增长
  @TableId(type = IdType.AUTO)
  private Integer id;
  //车辆类型
  private String carType;
  private String name;
  private String formgo;
  private String togo;
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date goTime;
  //发货状态
  private String goodType;
}
