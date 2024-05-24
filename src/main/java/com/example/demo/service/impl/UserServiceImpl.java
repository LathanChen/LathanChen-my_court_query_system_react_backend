package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.RedisCache;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
    private RedisCache redisCache;

	@Override
	public boolean registerUser(User user) {
		boolean registerFlg = false;
		User userDB = userMapper.getUserInfoByName(user.getUserName());
		if (userDB == null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			if (userMapper.registerUser(user) > 0) {
				registerFlg = true;
				return registerFlg;
			}
		}
		return registerFlg;
	}

	@Override
	public ResponseResult<List> userPermissions() {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<String> list = new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userRole = "";
		// 判断当前用户是否已经认证
		if (authentication.isAuthenticated()) {
			// 获取用户详细信息
			LoginUser loginUser = (LoginUser) authentication.getPrincipal();

			System.out.println("userPermissions");
			System.out.println("userPermissions");
			System.out.println("userPermissions");
			System.out.println("userPermissions");
			System.out.println("userPermissions");
			System.out.println("userPermissions");
			System.out.println(loginUser);

			// 获取用户角色信息
			userRole = loginUser.getAuthorities().stream().map(Object::toString).findFirst().orElse("");
			list.add(userRole);
			if (userRole.isEmpty()) {
				return new ResponseResult<List>(401, "用户无授权", list);
			}
		}
		ResponseResult<List> responseResult = new ResponseResult(200, "认证通过", list);
		return responseResult;
	}

	@Override
	public ResponseResult fecthUserInfo() {
		// TODO 自動生成されたメソッド・スタブ
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// authentication instanceof AnonymousAuthenticationToken表达式为真，则代表未登录
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// 获取用户详细信息
			User userInfo = new User();
			LoginUser loginUser = (LoginUser) authentication.getPrincipal();
			User _userInfo = loginUser.getUser();
			userInfo.setNickName(_userInfo.getNickName());
			userInfo.setAge(_userInfo.getAge());
			userInfo.setSex(_userInfo.getSex());
			userInfo.setEmail(_userInfo.getEmail());
			System.out.println(userInfo);
			return new ResponseResult(200, "取得用户信息成功", userInfo);
		} else {
			return new ResponseResult(500, "用户登录已过期");
		}
	}

	public User fecthUserInfoCommon() {
		// TODO 自動生成されたメソッド・スタブ
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// authentication instanceof AnonymousAuthenticationToken表达式为真，则代表未登录
		User userInfo = new User();
		// 获取用户详细信息
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			LoginUser loginUser = (LoginUser) authentication.getPrincipal();
			User _userInfo = loginUser.getUser();
			userInfo.setNickName(_userInfo.getNickName());
			userInfo.setAge(_userInfo.getAge());
			userInfo.setSex(_userInfo.getSex());
			userInfo.setEmail(_userInfo.getEmail());
			userInfo.setId(_userInfo.getId());
		}
		System.out.println(userInfo);
		return userInfo;
	}

	@Override
	public ResponseResult updateUserInfo(User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		long userId = loginUser.getUser().getId();
		user.setId(userId);
		if (userMapper.updateUserInfo(user) == 1) {
			User newUserInfoUser = userMapper.getUserInfoByName(loginUser.getUser().getUserName());
			LoginUser newLoginUser = new LoginUser(newUserInfoUser,loginUser.getPermissions());
//			更新redis中的用户信息
			redisCache.setCacheObject("login:" + userId, newLoginUser);
//			updateSecurityContextHolder(newLoginUser);
			return new ResponseResult(200, "更新に成功しました！");
		}
		return new ResponseResult(500, "更新に失敗しました！");
	}


//	下面的方法可以删除
//	原因：JwtAuthenticationTokenFilter在每次用户的TOKEN进行验证时，都会将从redis中取出最新的用户信息，
//	并创建一个新的UsernamePasswordAuthenticationToken存储最新的用户信息
//	因此updateUserInfo方法只需要在更新数据库的同时更新redis就可以了，不用再执行下面的方法
	private void updateSecurityContextHolder(LoginUser updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
            updatedUser, authentication.getCredentials(), updatedUser.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
