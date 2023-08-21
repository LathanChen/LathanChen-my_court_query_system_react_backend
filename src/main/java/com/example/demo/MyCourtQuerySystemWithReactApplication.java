package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@MapperScan("com.example.demo.mapper")
public class MyCourtQuerySystemWithReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCourtQuerySystemWithReactApplication.class, args);
	}

}
