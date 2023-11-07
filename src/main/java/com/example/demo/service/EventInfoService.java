package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.EventInfo;
import com.github.pagehelper.PageInfo;

public interface EventInfoService {
	ArrayList<EventInfo> getEventInfos(EventInfo eventInfo);
}
