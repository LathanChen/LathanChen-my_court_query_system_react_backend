package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.service.CourtInfoService;

@Controller

@RequestMapping("/courtinfo")
public class CourtInfoController {
	@Autowired
	private CourtInfoService courtInfoService;

	@RequestMapping(value="/getcourtinfo",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<CourtOpenInfo> getCourtInfo(CourtOpenInfo courtOpenInfo){
		return courtInfoService.getCourtInfo(courtOpenInfo);
	}
}
