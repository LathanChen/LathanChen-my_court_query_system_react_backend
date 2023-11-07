package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.EventEntryInfo;
import com.example.demo.entity.ResponseResult;
import com.example.demo.service.EventEntryInfoService;

@RestController
@RequestMapping("/eventEntryInfo")
public class EventEntryInfoController {
	@Autowired
    private EventEntryInfoService eventEntryInfoService;

	@RequestMapping(value="/getCountByEventID",method=RequestMethod.GET)
	@ResponseBody
	public int getCountByEventID(int eventID){
		return eventEntryInfoService.getCountByEventID(eventID);
	}

	@RequestMapping(value="/setInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult setInfo(@RequestBody EventEntryInfo eventEntryInfo){
//		System.out.print(courtOpenInfo);
		return eventEntryInfoService.setEventEntryInfo(eventEntryInfo);
	}

}
