package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.EventEntryInfo;

public interface EventEntryInfoMapper {

	List<EventEntryInfo> getCountByEventIDs(List<Integer> ids);

	boolean setEventEntryInfo(EventEntryInfo eventEntryInfo);
}
