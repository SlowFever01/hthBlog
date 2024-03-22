package com.hth.controller;

import com.hth.annotation.mySystemlog;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.User;
import com.hth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @mySystemlog(xxxBusinessName = "查询个人信息")//接口描述，用于'日志记录'功能
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @mySystemlog(xxxBusinessName = "更新用户信息")//接口描述，用于'日志记录'功能
    public ResponseResult updateUserInfo(@RequestBody User user){ //在请求体中，要用RequestBody
        return userService.updateUserInfo(user);
    }

    @PostMapping("register")
    @mySystemlog(xxxBusinessName = "用户注册")//接口描述，用于'日志记录'功能
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

}
