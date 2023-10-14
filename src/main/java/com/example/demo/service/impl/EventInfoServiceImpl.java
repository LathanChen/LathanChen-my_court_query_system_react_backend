package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.EventInfo;
import com.example.demo.mapper.EventInfoMapper;
import com.example.demo.service.EventInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EventInfoServiceImpl implements EventInfoService{

	@Autowired
	private EventInfoMapper eventInfoMapper;

	@Override
	public PageInfo<EventInfo> getEventInfos(EventInfo eventInfo) {
		// TODO 自動生成されたメソッド・スタブ
		int PageNum = eventInfo.getPageNum();
		int PageSize = eventInfo.getPageSize();

		PageHelper.startPage(PageNum, PageSize);     // 使用PageHelper进行分页查询获得场地查询结果
		List<EventInfo> eventList = eventInfoMapper.getEventInfos(eventInfo);
	    PageInfo<EventInfo> pageInfo = new PageInfo<>(eventList);

		return pageInfo;
	}

}
