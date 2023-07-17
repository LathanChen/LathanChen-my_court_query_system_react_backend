package com.example.demo.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.CourtInfo;
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

}
