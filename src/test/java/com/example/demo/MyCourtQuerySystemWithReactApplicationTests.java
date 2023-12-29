package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.ItemInfoMapper;
import com.example.demo.service.CourtInfoService;
import com.example.demo.service.impl.CourtInfoServiceImpl;

@SpringBootTest
class MyCourtQuerySystemWithReactApplicationTests {

	@Autowired
    private CourtInfoService courtInfoService; // 使用接口而不是实现类

	@Test
	void contextLoads() {
	}

	@Test
	void checkgetAllCourtInfoById() {
		assertEquals(true,courtInfoService.checkSameCourtName("中央本町学習センター"));
	}

}
