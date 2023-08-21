package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CourtEvaluate;
import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.PaginationResult;
import com.example.demo.service.CourtInfoService;

@RestController
@RequestMapping("/courtinfo")
public class CourtInfoController {
	@Autowired
    private CourtInfoService courtInfoService;

	@RequestMapping(value="",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<CourtInfo> getCourts(){
		return (ArrayList<CourtInfo>) courtInfoService.getCourts();
	}

	@RequestMapping(value="/getcourtnames",method=RequestMethod.GET)
	@ResponseBody
	public PaginationResult<CourtInfo> getCourtNames(int PageNum,int PageSize){
		return (PaginationResult<CourtInfo>)courtInfoService.getCourtNames(PageNum,PageSize);
	}

	@RequestMapping(value="/getCourtImgs",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getCourtImgUrls(
			@RequestParam("courtId") int courtId){
		return courtInfoService.getCourtImgUrls(courtId);
	}

	@RequestMapping(value="/getAllCourtInfoById",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAllCourtInfoById(
			@RequestParam("courtId") int courtId){
		return courtInfoService.getAllCourtInfoById(courtId);
	}

	@RequestMapping(value="/setScroeAndComment",method=RequestMethod.POST)
	@ResponseBody
	public int setCourtEvaluate(@RequestBody CourtEvaluate courtEvaluate) {
		return courtInfoService.setCourtEvaluate(courtEvaluate);
	}
}
