package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.demo.filter.JwtAuthenticationTokenFilter;
import com.example.demo.service.impl.AccessDeniedHandlerImpl;
import com.example.demo.service.impl.AuthenticationEntryPointImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
//    Token验证过滤器
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
//    权限验证失败的异常类
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
//    身份认证失败的异常类
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
//    登出成功处理器
    private LogoutSuccessHandler logoutSuccessHandler;

//    @Autowired
////    登出成功处理器
//    private LogoutHandler logoutHandler;

    /**
     * 密码明文加密方式配置
     *默认加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * 默认认证
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable(); // 基于 token，不需要 csrf
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// 基于 token，不需要 session
        // 下面开始设置权限
        http.authorizeRequests(authorize -> authorize
                        .antMatchers("/user/login").permitAll()//不需要进行身份验证的接口
                        .antMatchers("/courtOpenInfo/getTodayinfo").permitAll()//不需要进行身份验证的接口
                        .antMatchers("/courtOpenInfo/getInfo").permitAll()//不需要进行身份验证的接口
                        .antMatchers("/teamPlanningInfo/getteamplanninginfo").permitAll()//不需要进行身份验证的接口
                        .antMatchers("/user/register").permitAll()//不需要进行身份验证的接口
                        .antMatchers("/iteminfo").permitAll()//不需要进行身份验证的接口
                        .antMatchers("/courtinfo").permitAll()//不需要进行身份验证的接口
                        .anyRequest().authenticated()//除上面外的所有请求全部需要 鉴权认证
                );
//        配置登出处理
        http.logout()
//        .permitAll()
//        .logoutUrl("/user/logout") // 登出URL
//        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler(logoutSuccessHandler); // 登出成功处理器
        //定义filter的先后顺序，保证 jwtFilter比用户验证的过滤器先执行

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //自定义异常捕获机制
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
        return http.build();
    }
}

