package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.PaginationResult;
import com.example.demo.entity.ResponseResult;

public interface CourtOpenInfoService {

	PaginationResult<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo);

	ArrayList<CourtOpenInfo> getInfoByAdmin(CourtOpenInfo courtOpenInfo);

	Map<String,List> getTodayInfo(int weekNumber,int dayOfWeekInWeek);

	ResponseResult setInfo(CourtOpenInfo courtOpenInfo);

	CourtOpenInfo getInfoById(int courtOpenItemId);

	boolean updateInfo(CourtOpenInfo courtOpenInfo);

	boolean deleteInfo(List infoIdlist);

	String getCourtItemNames(int courtId);
}
