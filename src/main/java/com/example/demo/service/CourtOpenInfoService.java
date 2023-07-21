package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.ResponseResult;

public interface CourtOpenInfoService {

	ArrayList<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo);

	ArrayList<CourtOpenInfo> getInfoByAdmin(CourtOpenInfo courtOpenInfo);

	Map<String,Integer> getTodayInfo(int weekNumber,int dayOfWeekInWeek);

	ResponseResult setInfo(CourtOpenInfo courtOpenInfo);

	CourtOpenInfo getInfoById(int courtOpenItemId);

	boolean updateInfo(CourtOpenInfo courtOpenInfo);

	boolean deleteInfo(List infoIdlist);;
}
