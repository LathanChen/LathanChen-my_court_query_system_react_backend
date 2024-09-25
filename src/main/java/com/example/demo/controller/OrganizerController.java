package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.ResponseResult;
import com.example.demo.service.ItemInfoService;
import com.example.demo.service.OrganizerService;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
	@Autowired
    private OrganizerService organizerService;

	@RequestMapping(value="",method=RequestMethod.GET)
	@ResponseBody
	public ResponseResult getOrganizerInfoByUserId() {
		return organizerService.getOrganizerInfoByUserId();
	}

}
