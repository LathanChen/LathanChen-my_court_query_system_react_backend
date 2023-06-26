package com.example.demo.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.TeamPlanningInfo;
import com.example.demo.mapper.TeamPlanningInfoMapper;
import com.example.demo.service.TeamPlanningInfoService;

@Service
public class TeamPlanningInfoServiceImpl implements TeamPlanningInfoService {
	@Autowired
	private TeamPlanningInfoMapper teamPlanningInfoMapper;

	@Override
	public ArrayList<TeamPlanningInfo> getTeamPlanningInfo(TeamPlanningInfo TeamPlanningInfo) {
		// TODO 自動生成されたメソッド・スタブ
		return teamPlanningInfoMapper.getTeamPlanningInfo(TeamPlanningInfo);
	}

}
