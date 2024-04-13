package com.example.demo.entity;

import java.util.List;

import lombok.Data;

@Data
//イベント開催者
public class Organizer {

//	開催者識別番号
	private int organizerId;

//	開催者名
	private String organizerName;

//	イベント種類
	private List<Integer> organizerItemIdList;


}
