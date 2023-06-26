package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.TeamPlanningInfo;

public interface TeamPlanningInfoService {

	ArrayList<TeamPlanningInfo> getTeamPlanningInfo(TeamPlanningInfo TeamPlanningInfo);
}
