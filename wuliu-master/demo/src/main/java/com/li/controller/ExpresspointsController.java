package com.li.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.common.PingYinUtil;
import com.li.common.Result;
import com.li.entity.User;
import com.li.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 物流站点相关操作控制器类
 */
@RestController
@RequestMapping("/expresspoints")
public class ExpresspointsController {
  @Resource
  UserMapper userMapper;
  
  //登陆验证
  @PostMapping("/login")
  public Result<?> login(@RequestBody User user) {
    //判断账号密码是否正确
    User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
      .eq(User::getUserName, user.getUserName())
      .eq(User::getPassword, user.getPassword()));
    if (res == null) {
      return Result.error("-1", "用户名或密码错误");
    }
    return Result.success(res);
  }
  
  //注册
  @PostMapping("/register")
  public Result<?> register(@RequestBody User user) {
    User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, user.getUserName()));
    if (res != null) {
      return Result.error("-1", "用户已存在");
    }
    userMapper.insert(user);
    return Result.success();
  }
  
  //根据id查询
  @GetMapping("/{id}")
  public Result<?> getById(@PathVariable Long id) {
    return Result.success(userMapper.selectById(id));
  }
  
  //put方法,更新
  @PutMapping
  public Result<?> update(@RequestBody User user) {
    userMapper.updateById(user);
    return Result.success();
  }
  
  //获取用户名
  @PostMapping("/getUserName")
  public Result<?> getUserName(@RequestBody User user) {
    String res = PingYinUtil.getFirstSpell(user.getName());
    res += user.getStuId().substring(user.getStuId().length() - 4, user.getStuId().length());
    if (res.length() < 5) {
      return Result.error("-1", "用户名获取失败");
    }
    return Result.success(res);
  }
  
  /**
   * 查找分页
   *
   * @param pageNum
   * @param pageSize
   * @param search
   * @return
   */
  @GetMapping
  public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "") String search) {
    LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
    if (StrUtil.isNotBlank(search)) {
      wrapper.like(User::getName, search);
    }
    Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    return Result.success(userPage);
  }
}