package com.example.demo.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User userDB = new User();
		User userDB = userMapper.getUserInfoByName(username);
//		userDB.setPassword( passwordEncoder.encode(userDB.getPassword()));
		System.out.println("loadUserByUsername方法");
		System.out.println(username);
		System.out.println(userDB);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(userDB)){
            throw new RuntimeException("用户名或密码错误一");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> list = new ArrayList<>(Arrays.asList("test"));
        System.out.println(list);
        return new LoginUser(userDB,list);


	}
}

