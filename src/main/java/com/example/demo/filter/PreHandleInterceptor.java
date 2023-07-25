//package com.example.demo.filter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
////部署用，增加处理预检请求（OPTIONS请求）的拦截器
////在跨域请求中，当浏览器检测到发送的请求是复杂请求（比如带有自定义的请求头、使用PUT、DELETE等非简单请求方法时），
////会先发送一个OPTIONS请求到目标服务器，询问服务器是否允许跨域请求。这个过程称为预检请求。
//@Component
//public class PreHandleInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//    	if("options".equalsIgnoreCase(request.getMethod())){
//            return true;
//        }
//
//    	return true;
//}
//}
