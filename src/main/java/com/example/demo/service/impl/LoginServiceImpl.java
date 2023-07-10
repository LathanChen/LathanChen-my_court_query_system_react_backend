package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.RedisCache;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = 
        		new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        System.out.println("ResponseResult的login方法");
        System.out.println(user);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误二");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseResult(200,"登陆成功",map);
    }

	@Override
	public ResponseResult logout() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
	}
}
