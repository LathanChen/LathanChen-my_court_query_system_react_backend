package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.CourtEvaluate;
import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.PaginationResult;

public interface CourtInfoService {
	List<CourtInfo> getCourts();

	PaginationResult<CourtInfo> getCourtNames(int PageNum,int PageSize);

	List<String> getCourtImgUrls (int courtId);

	Map<String,Object> getAllCourtInfoById (int courtId);

	int setCourtEvaluate(CourtEvaluate courtEvaluate);
}