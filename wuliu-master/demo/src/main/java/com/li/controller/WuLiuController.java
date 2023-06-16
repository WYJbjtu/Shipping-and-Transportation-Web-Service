package com.li.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.common.Result;
import com.li.entity.WuLiu;
import com.li.mapper.WuLiuMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/wuliu")
public class WuLiuController {
  @Resource
  WuLiuMapper wuLiuMapper;
  
  /**
   * 查找分页
   */
  @GetMapping
  public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "") String search) {
    LambdaQueryWrapper<WuLiu> wrapper = Wrappers.<WuLiu>lambdaQuery();
    if (StrUtil.isNotBlank(search)) {
      wrapper.like(WuLiu::getName, search);
    }
    
    Page<WuLiu> userPage = wuLiuMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    return Result.success(userPage);
  }
  
  //post方法,添加
  @PostMapping
  public Result<?> save(@RequestBody WuLiu wuLiu) {
    wuLiuMapper.insert(wuLiu);
    return Result.success();
  }
  
  //根据id查询
  @GetMapping("/{id}")
  public Result<?> getById(@PathVariable Long id) {
    return Result.success(wuLiuMapper.selectById(id));
  }
  
  //put方法,更新
  @PutMapping
  public Result<?> update(@RequestBody WuLiu wuLiu) {
    wuLiuMapper.updateById(wuLiu);
    return Result.success();
  }
  
  //删除方法,根据id
  @DeleteMapping("/{id}")
  public Result<?> delete(@PathVariable Integer id) {
    wuLiuMapper.deleteById(id);
    return Result.success();
  }
  

  //批量删除
  @PostMapping("/deleteBatch")
  public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
//    mybatis-plus自带的批量删除
    wuLiuMapper.deleteBatchIds(ids);
    return Result.success();
  }
  

}
