package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class HelloTest {

	@GetMapping("/hello")
	@ResponseBody
//	@PreAuthorize("hasAuthority('test')")
	public String HelloTest() {
//		return "Hello react!";
		return "Hello Angular!";
	}
}
