package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.PaginationResult;
import com.example.demo.entity.ResponseResult;
import com.example.demo.service.CourtOpenInfoService;
import com.github.pagehelper.PageInfo;

@Controller
//to do:指定每个页面的访问权限

@RequestMapping("/courtOpenInfo")
public class CourtOpenInfoController {
	@Autowired
	private CourtOpenInfoService courtOpenInfoService;

	@RequestMapping(value="/getInfo",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo){
		System.out.println("6666666666666666666");
		System.out.println(courtOpenInfoService.getInfo(courtOpenInfo));
		System.out.println("7777777777777777777777");
		return courtOpenInfoService.getInfo(courtOpenInfo);
	}

	@RequestMapping(value="/setInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult setInfo(@RequestBody CourtOpenInfo courtOpenInfo){
		System.out.print(courtOpenInfo);
		return courtOpenInfoService.setInfo(courtOpenInfo);
	}

	@RequestMapping(value="/getInfoByAdmin",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<CourtOpenInfo> getInfoByAdmin(CourtOpenInfo courtOpenInfo){
//		System.out.print(courtOpenInfo);
		return courtOpenInfoService.getInfoByAdmin(courtOpenInfo);
	}

	@RequestMapping(value="/getTodayinfo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,List> getCourtInfo(
			@RequestParam("weekNumber") int weekNumber,
			@RequestParam("dayOfWeekInWeek") int dayOfWeekInWeek){
		return courtOpenInfoService.getTodayInfo(weekNumber,dayOfWeekInWeek);
	}

	@RequestMapping(value="/getinfobyid/{courtOpenItemId}",method=RequestMethod.GET)
	@ResponseBody
	public CourtOpenInfo getCourtInfoById(
			@PathVariable int courtOpenItemId){
		System.out.println(courtOpenItemId);
		return courtOpenInfoService.getInfoById(courtOpenItemId);
	}

	@RequestMapping(value="/updateinfo",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateInfo(@RequestBody CourtOpenInfo courtOpenInfo){
		return courtOpenInfoService.updateInfo(courtOpenInfo);
	}

	@RequestMapping(value="/deleteinfo",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteInfo(@RequestBody List<Integer> infoIdlist){
		System.out.println(infoIdlist);
		return courtOpenInfoService.deleteInfo(infoIdlist);
	}

//	@RequestMapping(value="/getCourtItemNames",method=RequestMethod.GET)
//	@ResponseBody
//	public String getCourtItemNames(@RequestParam("courtId") int courtId){
//		return courtOpenInfoService.getCourtItemNames(courtId);
//	}

}
