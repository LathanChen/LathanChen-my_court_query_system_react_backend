package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.EventEntryInfo;

public interface EventEntryInfoMapper {

	List<EventEntryInfo> getCountByEventIDs(List<Integer> ids);

	boolean setEventEntryInfo(EventEntryInfo eventEntryInfo);

	ArrayList<EventEntryInfo> getEventEntryInfosByUserId(int userId);

	List<EventEntryInfo> getMemberNicknamesByEventID(int eventInfoId);
}
