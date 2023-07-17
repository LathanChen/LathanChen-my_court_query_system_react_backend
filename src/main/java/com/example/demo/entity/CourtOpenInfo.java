package com.example.demo.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CourtOpenInfo {

//	场地的信息，对应数据库中的courtinfo表
	private CourtInfo courtInfo;

//	项目的信息，对应数据库中的iteminfo表
	private ItemInfo itemInfo;

//	表中的ID
	private int courtOpenInfoId;

//	项目的ID，对应iteminfo表中的编号
	private String courtOpenItemId;

//	场地的ID，对应courtinfo表中的编号
	private String courtOpenCourtId;

//	一周的第几天，1代表周一，以此类推，0为周日
	private String courtOpenWeekday;

//	一个月中的第几周
	private String courtOpenWeekNum;

//	时间段,上午、下午、晚上
	private String courtOpenTimeZone;

//	场地开放的具体时间段
	private String courtOpenTime;

//	数据的更新时间,使用SQL的时间戳
	private Timestamp courtOpenUpdateTime;

//	其他一些备注
	private String courtOpenNote;

}
