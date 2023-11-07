package com.example.demo.mapper;

import java.util.ArrayList;

import com.example.demo.entity.EventInfo;

public interface EventInfoMapper {
	ArrayList<EventInfo> getEventInfos(EventInfo eventInfo);
}
