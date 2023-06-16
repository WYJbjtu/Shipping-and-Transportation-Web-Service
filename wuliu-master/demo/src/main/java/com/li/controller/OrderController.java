package com.li.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.common.DateUtil;
import com.li.common.Result;
import com.li.entity.Order;
import com.li.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Resource
  OrderMapper orderMapper;
  
  /**
   * 查找分页
   */
  @GetMapping("/findPage")
  public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "") String search,
                            @RequestParam(defaultValue = "all") String owner) {
    LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery();
    if (StrUtil.isNotBlank(search)) {
      wrapper.like(Order::getOrderNo, search);
    }
    if (StrUtil.isNotBlank(owner)) {
      if(!"all".equals(owner)){
        wrapper.like(Order:: getSenderPhone,owner);
      }
    }
    
    Page<Order> orderPage = orderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    return Result.success(orderPage);
  }
  
  //post方法,添加
  @PostMapping("/save")
  public Result<?> save(@RequestBody Order order) {
    order.setState("待发车");
    order.setOrderNo(DateUtil.getTimes());
    orderMapper.insert(order);
    return Result.success();
  }

  //根据id查询
  @GetMapping("/{id}")
  public Result<?> getById(@PathVariable Long id) {
    return Result.success(orderMapper.selectById(id));
  }
  
  //put方法,更新
  @PostMapping("/update")
  public Result<?> update(@RequestBody Order order) {

    if("运输中".equals(order.getState())){
      try {
        order.setSendTime(DateUtil.getDateFormat());
        orderMapper.updateById(order);
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    if("已签收".equals(order.getState())){
      try {
        order.setCheckTime(DateUtil.getDateFormat());
        orderMapper.updateById(order);
      }catch (Exception e){
        e.printStackTrace();
      }
    }

    return Result.success();
  }
  
  //删除方法,根据id
  @DeleteMapping("/{id}")
  public Result<?> delete(@PathVariable Integer id) {
    orderMapper.deleteById(id);
    return Result.success();
  }
  

  //批量删除
  @PostMapping("/deleteBatch")
  public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
    orderMapper.deleteBatchIds(ids);
    return Result.success();
  }
  

}
