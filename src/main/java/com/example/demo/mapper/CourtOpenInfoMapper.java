package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.Map;
import com.example.demo.entity.CourtOpenInfo;

public interface CourtOpenInfoMapper {
	ArrayList<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo);

	CourtOpenInfo[] getTodayInfo(int weekNumber,int dayOfWeekInWeek);

	ArrayList<CourtOpenInfo> getInfoByAdmin(CourtOpenInfo courtOpenInfo);

	boolean setInfo(CourtOpenInfo courtOpenInfo);
}
