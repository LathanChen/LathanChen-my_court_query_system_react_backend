package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//如果只需要简单地允许跨域请求，那么在controller上使用@CrossOrigin注解即可；如果您需要更多的跨域请求配置，则可以编写一个实现了WebMvcConfigurer接口的配置类。
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      // 设置允许跨域的路径，也就是允许哪些地址向后端发送跨域请求
        registry.addMapping("/**")
                // 设置允许跨域请求的域名，也就是前端项目的地址
                .allowedOriginPatterns("http://localhost:3000")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}