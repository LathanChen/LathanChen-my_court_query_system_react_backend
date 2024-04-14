package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.EventInfo;
import com.example.demo.entity.ResponseResult;
import com.example.demo.service.EventInfoService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/eventInfo")
public class EventInfoController {
	@Autowired
	private EventInfoService eventInfoService;

	@RequestMapping(value="/getInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResponseResult getEventInfos(EventInfo eventInfo) {
		System.out.println(eventInfo);
		return eventInfoService.getEventInfos(eventInfo);
	}

	@RequestMapping(value="/availableEvents",method=RequestMethod.GET)
	@ResponseBody
	public ResponseResult getAvailableEvents() {
		return eventInfoService.getAvailableEvents();
	}

	@RequestMapping(value="/availableEventsOrganizerNameAndMemberNumsByItemId",method=RequestMethod.GET)
	@ResponseBody
	public ResponseResult getAvailableEventsOrganizerNameAndMemberNumsByItemId(int eventItemId) {
		return eventInfoService.getAvailableEventsOrganizerNameAndMemberNumsByItemId(eventItemId);
	}
}
