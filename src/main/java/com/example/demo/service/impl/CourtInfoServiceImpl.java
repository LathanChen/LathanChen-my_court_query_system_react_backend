package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.fasterxml.jackson.databind.ObjectMapper;
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

	@Override
	public boolean checkSameCourtName(String courtName) {
		// TODO 自動生成されたメソッド・スタブ
		boolean flg = false;
		if ((courtInfoMapper.checkSameCourtName(courtName) >= 1)) {
			flg = true;
		}
		else {
		}
		return flg;
	}

	@Override
	public boolean setCourtInfoAndImgs(Map<String, Object> urlAndNames) {
		// TODO 自動生成されたメソッド・スタブ
//		List<String> courtNames= (List)urlAndNames.get("courtNames");
//		List<String> courtInfoUrls= (List)urlAndNames.get("courtInfoUrls");
		boolean flg = true;
		ObjectMapper mapper = new ObjectMapper();
//		springboot在解析json时将类型转为了linkedHashmap了，而不是CourtInfo实体类
        // 使用ObjectMapper将LinkedHashMap转换为CourtInfo对象
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) urlAndNames.get("courtInfos");
        CourtInfo courtInfo = mapper.convertValue(map, CourtInfo.class);
		if (courtInfoMapper.setCourtInfo(courtInfo) == 1) {
			urlAndNames.put("courtId", courtInfo.getCourtId());
			if (courtInfoMapper.setCourtInfoUrls(urlAndNames) >= 0) {
			}
			else {
				flg = false;
			}
		}
		else {
			flg = false;
		}

		return flg;
	}

	@Override
	public List<CourtInfo> getCourtList() {
		// TODO 自動生成されたメソッド・スタブ
		return courtInfoMapper.getCourtList();
	}

	@Override
	public boolean deleteCourtInfo(int courtId) {
		// TODO 自動生成されたメソッド・スタブ
		return courtInfoMapper.deleteCourtInfo(courtId);
	}

	@Override
	public Map<String, Object> getCourtInfoAndImgsById(int courtId) {
		// TODO 自動生成されたメソッド・スタブ
		Map<String,Object> CourtInfoWithImgs = new HashMap<>();
		CourtInfo courtinfo = courtInfoMapper.getCourtInfoById(courtId);
		List<String> imgList = courtInfoMapper.getCourtImgUrls(courtId);
		CourtInfoWithImgs.put("courtInfos", courtinfo);
		CourtInfoWithImgs.put("fileList", imgList);
		return CourtInfoWithImgs;
	}

	@Override
	public boolean updateCourtInfoAndImgs(Map<String, Object> courtInfoAndImgs) {
		// TODO 自動生成されたメソッド・スタブ
		boolean flg = true;
		ObjectMapper mapper = new ObjectMapper();
//		springboot在解析json时将类型转为了linkedHashmap了，而不是CourtInfo实体类
        // 使用ObjectMapper将LinkedHashMap转换为CourtInfo对象
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) courtInfoAndImgs.get("courtInfos");
        CourtInfo courtInfo = mapper.convertValue(map, CourtInfo.class);
		if (courtInfoMapper.updateCourtInfo(courtInfo) == 1 && ((List)courtInfoAndImgs.get("courtInfoUrls")).size() > 0) {
			courtInfoMapper.deleteCourtImgs(courtInfo.getCourtId());
			courtInfoAndImgs.put("courtId", courtInfo.getCourtId());
			if (courtInfoMapper.setCourtInfoUrls(courtInfoAndImgs) >= 0) {
				System.out.println("1111111111111111111");
			}
			else {
				flg = false;
			}
		}
		else {
			if (courtInfoMapper.updateCourtInfo(courtInfo) == 1) {
				flg = true;
			}
			else {
				flg = false;
			}

		}

		return flg;
	}



}
