package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

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

	int checkSameCourtName(String courtName);

	int setCourtInfo(CourtInfo courtInfo);

	int setCourtInfoUrls(Map<String,Object> urlAndNames);

	List<CourtInfo> getCourtList();

	boolean deleteCourtInfo(int courtId);

	boolean deleteCourtImgs(int courtId);

	int updateCourtInfo(CourtInfo courtInfo);
}