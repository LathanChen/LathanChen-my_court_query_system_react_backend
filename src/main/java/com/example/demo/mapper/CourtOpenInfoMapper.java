package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.demo.entity.CourtOpenInfo;

public interface CourtOpenInfoMapper {
	ArrayList<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo);

	CourtOpenInfo[] getTodayInfo(int weekNumber,int dayOfWeekInWeek);

	ArrayList<CourtOpenInfo> getInfoByAdmin(CourtOpenInfo courtOpenInfo);

	boolean setInfo(CourtOpenInfo courtOpenInfo);

	CourtOpenInfo getInfoById(int courtOpenItemId);

	int updateInfo(CourtOpenInfo courtOpenInfo);

	int deleteInfo(List infoIdlist);

	ArrayList<CourtOpenInfo> getItemCountByCourt();

	String getCourtItemNames(int courtId);

	List<Map<String,Object>> getAllCourtOpenInfoWithAllCourts();
}
