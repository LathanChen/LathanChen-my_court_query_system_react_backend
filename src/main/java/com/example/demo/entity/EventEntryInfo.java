package com.example.demo.entity;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class EventEntryInfo {

//	报名中的活动的编号
	private int eventInfoId;

//	报名人的注册用户ID，如果是路人报名，则为空
	private Long userId;

//	报名人的邮箱，如果报名人已注册为系统用户，则从sys_user表中获取
	private String mailAddress;

//	报名人的昵称，如果报名人已注册为系统用户，则从sys_user表中获取
	private String nickName;

//	报名时间
	private Timestamp entryTime;

//	是否取消报名
//	SpringBoot默认使用的JSON 库Jackson会导致自动推断该属性的 JSON 属性名为 cancel 而不是 isCancel
	private boolean isCancel;

//	对应的活动信息
	private EventInfo eventInfo;

//	活动的类型信息
	private ItemInfo itemInfo;

//	活动主办方信息
	private Organizer organizer;

//	场地信息
	private CourtInfo courtInfo;

//	该条信息的ID
	private int infoId;

}
