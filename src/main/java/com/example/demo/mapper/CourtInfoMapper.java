package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.CourtEvaluate;
import com.example.demo.entity.CourtInfo;

public interface CourtInfoMapper {
	List<CourtInfo> getCourts();

	List<CourtInfo> getCourtNames();

	List<String> getCourtImgUrls(int courtId);

	CourtInfo getCourtInfoById(int courtId);

	Float getCourtAvgScoreById(int courtId);

	List<String> getAllCommentsById(int courtId);

	int setCourtEvaluate(CourtEvaluate courtEvaluate);
}