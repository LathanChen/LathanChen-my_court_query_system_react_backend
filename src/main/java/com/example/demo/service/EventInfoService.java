package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.EventInfo;
import com.github.pagehelper.PageInfo;

public interface EventInfoService {
	PageInfo<EventInfo> getEventInfos(EventInfo eventInfo);
}
