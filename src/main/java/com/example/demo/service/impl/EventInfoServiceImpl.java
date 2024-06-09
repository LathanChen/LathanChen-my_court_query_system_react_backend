package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(EventInfoService.class);

//	通过构造函数进行依赖注入
//	优点：
//	不可变性：使用 final 关键字声明依赖，保证了依赖不会被再次赋值。
//	依赖清晰：通过构造函数明确表明类的依赖，便于理解和测试。
//	避免循环依赖：构造函数注入有助于在编译时发现循环依赖的问题。
	private final UserServiceImpl userServiceImpl;

//	通过字段进行依赖注入
//	优点：
//	简便：代码更简洁，易于快速编写。
//	灵活性：适合快速原型开发和小规模项目。
//
//	缺点：
//	难以发现依赖：依赖关系在类的内部，可能不易于立即识别。
//	不支持不可变性：不能使用 final 关键字，可能导致依赖在对象生命周期中被改变。
//	测试困难：直接在字段上注入可能使得测试更加困难，因为需要使用反射或 Spring 特定工具来注入依赖。
	@Autowired
	private EventInfoMapper eventInfoMapper;

	@Autowired
	private EventEntryInfoMapper eventEntryInfoMapper;

	@Autowired
	public EventInfoServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@Override
	public ResponseResult getEventInfos(EventInfo eventInfo) {
		// TODO 自動生成されたメソッド・スタブ
		List<EventInfo> _eventList = eventInfoMapper.getEventInfos(eventInfo);

//		使用Stream流式运算取出查询结果中的所有eventInfoId
		List<Integer> eventIdList;
		if (_eventList.size() > 0) {
			eventIdList = _eventList.stream().map(EventInfo::getEventInfoId).collect(Collectors.toList());
			System.out.println(eventIdList);
		} else {
			ResponseResult responseResult = new ResponseResult(404, "没有查询到数据", null);
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
					if (_eventInfo.getEventInfoId() == _eventEntryInfo.getEventInfoId()) {
//						将该活动信息中活动发起人填写的已报名人数和活动信息报名表中现有的报名人数相加，获得实际最新的报名人数
						_eventInfo.setEventEnrollment(_eventInfo.getEventEnrollment() + 1);
//						如果本活动报名信息的报名人员中存在当前登录的用户
						if (_eventEntryInfo.getUserId() == userId) {
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
					if (_eventInfo.getEventInfoId() == _eventEntryInfo.getEventInfoId()) {
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

		ResponseResult<List> responseResult = new ResponseResult(200, "查询成功", eventList);
		return responseResult;
	}

	@Override
	public ResponseResult getAvailableEvents() {
		List availableEventsList = eventInfoMapper.getAvailableEvents();
		return new ResponseResult(200, "查询成功", availableEventsList);
	}

	@Override
	public ResponseResult getAvailableEventsOrganizerNameAndMemberNumsByItemId(int eventItemId) {
		List vailableEventsOrganizerNameAndMemberNumByItemIdList = eventInfoMapper
				.getAvailableEventsOrganizerNameAndMemberNumsByItemId(eventItemId);
		return new ResponseResult(200, "查询成功", vailableEventsOrganizerNameAndMemberNumByItemIdList);
	}

	@Override
	public ResponseResult getEventInfosByUserId() {
		long _userId = userServiceImpl.fecthUserInfoCommon().getId();
		int userId = (int) _userId;
		List eventInfoList = eventInfoMapper.getEventInfosByUserId(userId);
		if (eventInfoList != null && !eventInfoList.isEmpty()) {
			return new ResponseResult<List>(200, "查询成功", eventInfoList);
		} else {
			return new ResponseResult<List>(204, "未能查询到数据！");
		}
	}

	@Override
	public ResponseResult deleteEventInfoByEventInfoId(int eventInfoId) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			if (eventInfoMapper.deleteEventInfoByEventInfoId(eventInfoId) > 0) {
				return new ResponseResult(200, "删除成功");
			} else {
				return new ResponseResult(404, "未找到要删除的数据！");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("删除活动信息时出错" + "活动信息id：" + eventInfoId + "错误信息：" + e.getMessage());
			return new ResponseResult<List>(500, e.getMessage());
		}
	}

	@Override
	public ResponseResult updateEventInfo(EventInfo eventInfo) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			if (eventInfoMapper.updateEventInfo(eventInfo) > 0) {
				return new ResponseResult(200, "更新成功");
			} else {
				return new ResponseResult(404, "未找到要更新的数据！");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新活动信息时出错" + "活动信息id：" + eventInfo.getEventInfoId() + "错误信息：" + e.getMessage());
			return new ResponseResult<List>(500, e.getMessage());
		}
	}

}
