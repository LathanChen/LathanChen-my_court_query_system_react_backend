package com.example.demo.service;

import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;

public interface UserService {
	boolean registerUser(User user);

	ResponseResult userPermissions();

	ResponseResult fecthUserInfo();
}
