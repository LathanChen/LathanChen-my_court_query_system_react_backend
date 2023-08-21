package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CourtEvaluate;
import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.PaginationResult;
import com.example.demo.mapper.CourtInfoMapper;
import com.example.demo.mapper.CourtOpenInfoMapper;
import com.example.demo.mapper.ItemInfoMapper;
import com.example.demo.service.CourtInfoService;
import com.example.demo.service.ItemInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CourtInfoServiceImpl implements  CourtInfoService{
	@Autowired
	private CourtInfoMapper courtInfoMapper;

	@Autowired
	private CourtOpenInfoMapper courtOpenInfoMapper;

	@Override
	public List<CourtInfo> getCourts(){
		return courtInfoMapper.getCourts()
;	}

	@Override
	public PaginationResult<CourtInfo> getCourtNames(int PageNum,int PageSize) {
		PageHelper.startPage(PageNum, PageSize);     // 使用PageHelper进行分页查询获得场地查询结果
		List<CourtInfo> courtNamesList = courtInfoMapper.getCourtNames();
	    PageInfo<CourtInfo> pageInfo = new PageInfo<>(courtNamesList);

	    PaginationResult<CourtInfo> courtNamesResult = new PaginationResult<>();
	    courtNamesResult.setList(pageInfo.getList());
	    System.out.println(pageInfo.getList());
	    System.out.println("-------------------");
	    courtNamesResult.setTotal(pageInfo.getTotal());
	    courtNamesResult.setPageNum(pageInfo.getPageNum());
	    courtNamesResult.setPageSize(pageInfo.getPageSize());
	    courtNamesResult.setPages(pageInfo.getPages());
	    courtNamesResult.setCount(pageInfo.getList().size());
        return courtNamesResult;
	}

	@Override
	public List<String> getCourtImgUrls(int courtId) {
		// TODO 自動生成されたメソッド・スタブ
		return courtInfoMapper.getCourtImgUrls(courtId);
	}

	@Override
	public Map<String,Object> getAllCourtInfoById(int courtId) {
		// TODO 自動生成されたメソッド・スタブ
		CourtInfo courtInfo = courtInfoMapper.getCourtInfoById(courtId);
		List<String> comments = courtInfoMapper.getAllCommentsById(courtId);
		String courtItemNames = courtOpenInfoMapper.getCourtItemNames(courtId);
		Float courtAvgScore = courtInfoMapper.getCourtAvgScoreById(courtId);
		Map<String,Object> allCourtInfo = new HashMap<>();
		allCourtInfo.put("courtInfo",courtInfo);
		allCourtInfo.put("comments",comments);
		allCourtInfo.put("courtItemNames",courtItemNames);
		allCourtInfo.put("courtAvgScore",courtAvgScore);
		return allCourtInfo;
	}

	@Override
	public int setCourtEvaluate(CourtEvaluate courtEvaluate) {
		// TODO 自動生成されたメソッド・スタブ
		courtEvaluate.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		return courtInfoMapper.setCourtEvaluate(courtEvaluate);
	}

}
