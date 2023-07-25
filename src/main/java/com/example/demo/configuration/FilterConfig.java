//package com.example.demo.configuration;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.example.demo.filter.CorsFliter;
//
////部属用
//public class FilterConfig {
//	@Bean
//    public FilterRegistrationBean<CorsFliter> corsFilter() {
//        FilterRegistrationBean<CorsFliter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CorsFliter());
//        registrationBean.addUrlPatterns("/*"); // 设置过滤器的拦截路径，这里设置为拦截所有路径
//        registrationBean.setOrder(1); // 设置过滤器的执行顺序，数字越小越先执行
//        return registrationBean;
//    }
//}
