package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.EventInfo;
import com.example.demo.entity.ResponseResult;
import com.github.pagehelper.PageInfo;

public interface EventInfoService {
	ResponseResult getEventInfos(EventInfo eventInfo);
}
