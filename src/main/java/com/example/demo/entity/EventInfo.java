package com.example.demo.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EventInfo {

//	场地的信息，对应数据库中的courtinfo表
	private CourtInfo courtInfo;

//	项目的信息，对应数据库中的iteminfo表
	private ItemInfo itemInfo;

//	表中的ID
	private int eventInfoId;

//	项目的ID，对应iteminfo表中的编号
	private String eventItemId;

//	场地的ID，对应courtinfo表中的编号
	private String eventCourtId;

//	活动的具体日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate eventOpenDay;

//	时间段,上午、下午、晚上
	private String eventOpenTimeZone;

//	场地开放的具体时间段
	private String eventOpenTime;

//	数据的更新时间,使用SQL的时间戳
	private Timestamp eventUpdateTime;

//	其他一些备注
	private String eventNote;

//	活动当前的报名人数
	private int eventEnrollment;

//	活动的报名人数上限
	private int eventMaxEnrollment;

//	活动费用（男性）
	private int eventMaleCost;

//	活动费用（女性）
	private int eventFemaleCost;

//  batch追加，相同条件（如场地相同）下的明细数量
//	private int eventInfos;
//
//  分页追加，页码
	private int PageNum;

//	分页追加，每页明细的数量
	private int PageSize;

//	追加,用于确认当前登录用户是否已报名该活动
	private boolean isRegistered;
}
