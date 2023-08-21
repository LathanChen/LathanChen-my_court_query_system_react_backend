package com.example.demo.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CourtEvaluate {
//	表格中的唯一编号
	private int num;
//	分数
	private Float score;
//	评论
	private String courtComment;
//	对应的场地ID
	private int courtId;
//	信息的更新时间
	private Timestamp updatetime;
}

