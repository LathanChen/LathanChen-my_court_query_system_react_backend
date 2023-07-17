package com.example.demo.service.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.mapper.CourtOpenInfoMapper;
import com.example.demo.service.CourtOpenInfoService;

@Service
public class CourtOpenInfoServiceImpl implements CourtOpenInfoService{
	@Autowired
	private CourtOpenInfoMapper courtOpenInfoMapper;

	@Override
	public ArrayList<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo) {
		// TODO 自動生成されたメソッド・スタブ
		return courtOpenInfoMapper.getInfo(courtOpenInfo);
	}

	@Override
//	在Java中，泛型类型参数不能是基本数据类型，例如int、char、boolean等。因此，尝试将Map<String, int>作为方法的返回类型时，编译器会报错。
	public Map<String,Integer> getTodayInfo(int weekNumber,int dayOfWeekInWeek) {
		// TODO 自動生成されたメソッド・スタブ
		CourtOpenInfo[] courtOpenInfos = courtOpenInfoMapper.getTodayInfo(weekNumber, dayOfWeekInWeek);

		LocalTime currentTime = LocalTime.now();
		// 定义时间格式化器，将时间格式化为小时和分钟
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

        // 格式化时间为数值类型
        int currentTimeInt = Integer.parseInt(currentTime.format(formatter));

		int basketballNotStart = 0;
		int basketballEnded = 0;
		int tableTennisNotStart = 0;
		int tableTennisEnded = 0;
		int badmintonNotStart = 0;
		int badmintonEnded = 0;
		int volleyballNotStart = 0;
		int volleyballEnded = 0;

		for (int a = 0;a < courtOpenInfos.length;a++) {
			CourtOpenInfo courtOpenInfo = courtOpenInfos[a];
//			先通过"-"分割字符串，再去除时间数据中的冒号，最后转化为int类型
			int openTime = Integer.parseInt(courtOpenInfo.getCourtOpenTime().split("-")[0].replace(":", ""));
			int endTime = Integer.parseInt(courtOpenInfo.getCourtOpenTime().split("-")[1].replace(":", ""));
			if (courtOpenInfo.getCourtOpenItemId().equals("1")) {
				if (openTime > currentTimeInt) {
					basketballNotStart++;
				}
				if (endTime < currentTimeInt) {
					basketballEnded++;
				}
			}
			if (courtOpenInfo.getCourtOpenItemId().equals("2")) {
				if (openTime > currentTimeInt) {
					tableTennisNotStart++;
				}
				if (endTime < currentTimeInt) {
					tableTennisEnded++;
				}
			}
			if (courtOpenInfo.getCourtOpenItemId().equals("3")) {
				if (openTime > currentTimeInt) {
					badmintonNotStart++;
				}
				if (endTime < currentTimeInt) {
					badmintonEnded++;
				}
			}
			if (courtOpenInfo.getCourtOpenItemId().equals("4")) {
				if (openTime > currentTimeInt) {
					volleyballNotStart++;
				}
				if (endTime < currentTimeInt) {
					volleyballEnded++;
				}
			}

		}

//		System.out.println(basketballNotStart);
//		System.out.println(basketballEnded);
//		System.out.println(tableTennisNotStart);
//		System.out.println(tableTennisEnded);
//		System.out.println(badmintonNotStart);
//		System.out.println(badmintonEnded);
//		System.out.println(volleyballNotStart);
//		System.out.println(volleyballEnded);
//		System.out.println(courtOpenInfos.length);
//		System.out.println(currentTimeInt);

		Map<String,Integer> todayEventMap = new HashMap<>();
		todayEventMap.put("basketballNotStart", basketballNotStart);
		todayEventMap.put("basketballEnded", basketballEnded);
		todayEventMap.put("tableTennisNotStart", tableTennisNotStart);
		todayEventMap.put("tableTennisEnded", tableTennisEnded);
		todayEventMap.put("badmintonNotStart", badmintonNotStart);
		todayEventMap.put("badmintonEnded", badmintonEnded);
		todayEventMap.put("volleyballNotStart", volleyballNotStart);
		todayEventMap.put("volleyballEnded", volleyballEnded);
		return todayEventMap;
	}

	@Override
	public ArrayList<CourtOpenInfo> getInfoByAdmin(CourtOpenInfo courtOpenInfo) {
		// TODO 自動生成されたメソッド・スタブ
		return courtOpenInfoMapper.getInfoByAdmin(courtOpenInfo);
	}

}
