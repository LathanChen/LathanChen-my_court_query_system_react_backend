package com.example.demo.entity;


import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TeamPlanningInfo {
	private int planningInfoId;
	private int teamId;
	private int teamItemId;
	private int planningDate;
	private String planningTime;
	private Timestamp updateTime;
	private int courtId;
	private TeamInfo teamInfo;
	private CourtInfo courtInfo;
}
