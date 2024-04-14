package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.EventInfo;

public interface EventInfoMapper {
	ArrayList<EventInfo> getEventInfos(EventInfo eventInfo);

	List<Map<String,Object>> getAvailableEvents();

	List<Map<String,Object>> getAvailableEventsOrganizerNameAndMemberNumsByItemId(int eventItemId);
}
