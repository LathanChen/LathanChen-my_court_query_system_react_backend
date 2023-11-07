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
	private boolean isCancel;

}
