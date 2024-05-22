package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.EventEntryInfo;
import com.example.demo.entity.ResponseResult;

public interface EventEntryInfoService {

	int getCountByEventID(int eventID);

	ResponseResult setEventEntryInfo(EventEntryInfo eventEntryInfo);

	ResponseResult getEventEntryInfosByUserId();

	ResponseResult getMemberNicknamesByEventID(int eventInfoId);
}
