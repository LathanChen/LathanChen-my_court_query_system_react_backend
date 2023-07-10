package com.example.demo.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.example.demo.entity.LoginUser;
import com.example.demo.util.RedisCache;


@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	@Autowired
    private RedisCache redisCache;

//    @Autowired
//    private LogoutHandler logoutHandler;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//    	logoutHandler.logout(request, response, authentication);
//    	LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        Long userid = loginUser.getUser().getId();
//        redisCache.deleteObject("login:"+userid);
//    	System.out.println("注销成功！");
    	System.out.println("登出成功！");
    }
}
