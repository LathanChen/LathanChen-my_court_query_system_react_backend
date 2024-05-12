package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.EventInfo;
import com.example.demo.entity.ResponseResult;
import com.github.pagehelper.PageInfo;

public interface EventInfoService {
	ResponseResult getEventInfos(EventInfo eventInfo);

	ResponseResult getAvailableEvents();

	ResponseResult getAvailableEventsOrganizerNameAndMemberNumsByItemId(int eventItemId);

	ResponseResult getEventInfosByUserId();;
}
