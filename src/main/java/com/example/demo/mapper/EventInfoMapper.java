package com.example.demo.mapper;

import java.util.ArrayList;

import com.example.demo.entity.EventInfo;
import com.github.pagehelper.PageInfo;

public interface EventInfoMapper {
	ArrayList<EventInfo> getEventInfos(EventInfo eventInfo);
}
