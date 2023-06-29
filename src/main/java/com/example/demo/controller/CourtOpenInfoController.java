package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.service.CourtOpenInfoService;

@Controller

@RequestMapping("/courtOpenInfo")
public class CourtOpenInfoController {
	@Autowired
	private CourtOpenInfoService courtOpenInfoService;

	@RequestMapping(value="/getInfo",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo){
		return courtOpenInfoService.getInfo(courtOpenInfo);
	}

	@RequestMapping(value="/getTodayinfo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Integer> getCourtInfo(
			@RequestParam("weekNumber") int weekNumber,
			@RequestParam("dayOfWeekInWeek") int dayOfWeekInWeek){
		return courtOpenInfoService.getTodayInfo(weekNumber,dayOfWeekInWeek);
	}
}
