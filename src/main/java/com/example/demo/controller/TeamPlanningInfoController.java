package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.TeamPlanningInfo;
import com.example.demo.service.TeamPlanningInfoService;

@Controller

@RequestMapping("/teamPlanningInfo")
public class TeamPlanningInfoController {
	@Autowired
	private TeamPlanningInfoService teamPlanningInfoService;

	@RequestMapping(value="/getteamplanninginfo",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<TeamPlanningInfo> getCourtInfo(TeamPlanningInfo teamPlanningInfo){
		return teamPlanningInfoService.getTeamPlanningInfo(teamPlanningInfo);
	}
}
