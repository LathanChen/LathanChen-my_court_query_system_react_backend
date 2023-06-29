package com.example.demo.service;

import java.util.ArrayList;
import java.util.Map;
import com.example.demo.entity.CourtOpenInfo;

public interface CourtOpenInfoService {

	ArrayList<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo);

	Map<String,Integer> getTodayInfo(int weekNumber,int dayOfWeekInWeek);
}
