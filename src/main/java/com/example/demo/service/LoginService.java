package com.example.demo.service;

import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;

public interface LoginService {
	ResponseResult login(User user);
	ResponseResult logout();
}
