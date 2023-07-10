package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ResponseResult login(@RequestBody User user) {
    	System.out.println("--------------------------------------------------");
    	System.out.println("--------------------------------------------------");
    	System.out.println("--------------------------------------------------");
    	System.out.println("--------------------------------------------------");
    	System.out.println("--------------------------------------------------");
    	System.out.println("--------------------------------------------------");
    	System.out.println(user);
        return loginService.login(user);
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public ResponseResult logout() {
    	return loginService.logout();
    }
}

