package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.CourtEvaluate;
import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.PaginationResult;
import com.example.demo.entity.ResponseResult;

public interface CourtInfoService {
	List<CourtInfo> getCourts();

	PaginationResult<CourtInfo> getCourtNames(int PageNum,int PageSize);

	List<String> getCourtImgUrls (int courtId);

	Map<String,Object> getAllCourtInfoById (int courtId);

	int setCourtEvaluate(CourtEvaluate courtEvaluate);

	boolean checkSameCourtName(String courtName);

	boolean setCourtInfoAndImgs(Map<String,Object> urlAndNames);

	List<CourtInfo> getCourtList();

	boolean deleteCourtInfo(int courtId);

	Map<String,Object> getCourtInfoAndImgsById(int courtId);

	boolean updateCourtInfoAndImgs(Map<String,Object> courtInfoAndImgs);
}
