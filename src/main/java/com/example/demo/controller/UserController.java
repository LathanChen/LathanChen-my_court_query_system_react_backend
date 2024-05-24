package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public boolean registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

//    用户登录后鉴权，前端根据对应角色权限加载页面
    @RequestMapping(value="/authority", method=RequestMethod.GET)
    @ResponseBody
    public ResponseResult userPermissions() {
    	return userService.userPermissions();
    }

//    用户登录后查询用户信息
    @RequestMapping(value="/UserInfo", method=RequestMethod.GET)
    @ResponseBody
    public ResponseResult fecthUserInfo() {
    	return userService.fecthUserInfo();
    }

    @RequestMapping(value="/userinfo", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }
}

