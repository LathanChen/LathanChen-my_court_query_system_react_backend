package com.example.demo.entity;

import lombok.Data;

@Data
public class CourtInfo {

//	场地名
	private String courtName;

//	距离车站路程
	private float courtFromStationDistance;

//	场地编号
	private int courtId;

//	场地的具体地址
	private String courtAdress;

//	场地的电话
	private String courtTelNum;

//	车站信息
	private String courtStation;

//	场地的评分
	private float score;

//	场地的照片
	private String imgUrl;

}
