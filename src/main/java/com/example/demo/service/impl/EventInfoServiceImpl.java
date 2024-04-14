package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.EventEntryInfo;
import com.example.demo.entity.EventInfo;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.ResponseResult;
import com.example.demo.mapper.EventEntryInfoMapper;
import com.example.demo.mapper.EventInfoMapper;
import com.example.demo.service.EventInfoService;

@Service
@Transactional
public class EventInfoServiceImpl implements EventInfoService {

	@Autowired
	private EventInfoMapper eventInfoMapper;

	@Autowired
	private EventEntryInfoMapper eventEntryInfoMapper;

	@Override
	public ResponseResult getEventInfos(EventInfo eventInfo) {
		// TODO 自動生成されたメソッド・スタブ
		List<EventInfo> _eventList = eventInfoMapper.getEventInfos(eventInfo);

//		使用Stream流式运算取出查询结果中的所有eventInfoId
		List<Integer> eventIdList;
		if (_eventList.size() > 0) {
			eventIdList = _eventList.stream().map(EventInfo::getEventInfoId).collect(Collectors.toList());
			System.out.println(eventIdList);
		}
		else {
			ResponseResult responseResult = new ResponseResult(404,"没有查询到数据",null);
			return responseResult;
		}

//		Todo 查询每个ID在报名情况表单里的数量，即实际报名人数

//		获取报名情况表中，每个活动的报名用户的id
		List<EventEntryInfo> eventIdAndEntryNums = eventEntryInfoMapper.getCountByEventIDs(eventIdList);

//		Map<Integer,Integer> entryCountMap = eventIdAndEntryNums.stream()
//			    .collect(Collectors.toMap(EventEntryInfo::getEventInfoId, EventEntryInfo::getEventEntryNums));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("77777777777777777");
//		System.out.println("77777777777777777");
		System.out.println(authentication);
		// 判断当前用户是否匿名用户，即是否是已登录用户
		// authentication instanceof AnonymousAuthenticationToken表达式为真，则代表未登录
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// 获取用户详细信息
//			System.out.println("77777777777777777");
//			System.out.println("77777777777777777");
			LoginUser loginUser = (LoginUser) authentication.getPrincipal();
			System.out.println(loginUser);
			Long userId = loginUser.getUser().getId();
			_eventList.forEach(_eventInfo -> {
				eventIdAndEntryNums.forEach(_eventEntryInfo -> {
//					如果当前活动信息id和活动信息报名表中的id相同
					if(_eventInfo.getEventInfoId() == _eventEntryInfo.getEventInfoId()) {
//						将该活动信息中活动发起人填写的已报名人数和活动信息报名表中现有的报名人数相加，获得实际最新的报名人数
						_eventInfo.setEventEnrollment(_eventInfo.getEventEnrollment() + 1);
//						如果本活动报名信息的报名人员中存在当前登录的用户
						if(_eventEntryInfo.getUserId() == userId) {
//							向前端传递已报名信息，按钮设置为不可用
							_eventInfo.setRegistered(true);
						}
					}
				});
			});
		}

//		当前用户认证未通过或是路人报名的情况
		else {
			_eventList.forEach(_eventInfo -> {
				eventIdAndEntryNums.forEach(_eventEntryInfo -> {
//					如果当前活动信息id和活动信息报名表中的id相同
					if(_eventInfo.getEventInfoId() == _eventEntryInfo.getEventInfoId()) {
//						将该活动信息中活动发起人填写的已报名人数和活动信息报名表中现有的报名人数相加，获得实际最新的报名人数
						_eventInfo.setEventEnrollment(_eventInfo.getEventEnrollment() + 1);
					}
				});
			});
		}
//		System.out.println("11111111111111111");
//		System.out.println("11111111111111111");
//		System.out.println("11111111111111111");
		System.out.println(_eventList);
		ArrayList<EventInfo> eventList = new ArrayList<>(_eventList);

		ResponseResult<List> responseResult = new ResponseResult(200,"查询成功",eventList);
		return responseResult;
	}

	@Override
	public ResponseResult getAvailableEvents() {
		List availableEventsList = eventInfoMapper.getAvailableEvents();
		return new ResponseResult(200,"查询成功",availableEventsList);
	}

	@Override
	public ResponseResult getAvailableEventsOrganizerNameAndMemberNumsByItemId(int eventItemId) {
		List vailableEventsOrganizerNameAndMemberNumByItemIdList = eventInfoMapper.getAvailableEventsOrganizerNameAndMemberNumsByItemId(eventItemId);
		return new ResponseResult(200,"查询成功",vailableEventsOrganizerNameAndMemberNumByItemIdList);
	}

}
