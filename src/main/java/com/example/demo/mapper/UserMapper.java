package com.example.demo.mapper;

import com.example.demo.entity.User;

public interface UserMapper  {
	User getUserInfoByName(String username);
	int registerUser(User user);
}

