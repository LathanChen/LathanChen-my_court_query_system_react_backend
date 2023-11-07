package com.example.demo.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EventEntryInfo;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.ResponseResult;
import com.example.demo.mapper.EventEntryInfoMapper;
import com.example.demo.mapper.EventInfoMapper;
import com.example.demo.service.EventEntryInfoService;

@Service
public class EventEntryInfoServiceImpl implements EventEntryInfoService {

	@Autowired
	private EventEntryInfoMapper eventEntryInfoMapper;

	@Override
	public int getCountByEventID(int eventID) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public ResponseResult setEventEntryInfo(EventEntryInfo eventEntryInfo) {
		// TODO 自動生成されたメソッド・スタブ
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// authentication instanceof AnonymousAuthenticationToken表达式为真，则代表未登录
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// 获取用户详细信息
			LoginUser loginUser = (LoginUser) authentication.getPrincipal();
			eventEntryInfo.setUserId(loginUser.getUser().getId());
			eventEntryInfo.setNickName(loginUser.getUser().getNickName());
			eventEntryInfo.setEntryTime(new Timestamp(System.currentTimeMillis()));
//			向数据库写入数据
			try {
				boolean insertResult = eventEntryInfoMapper.setEventEntryInfo(eventEntryInfo);
				if (insertResult) {
					return new ResponseResult(200, "添加成功", true);
				} else {
					return new ResponseResult(500, "添加失败", false);
				}
			} catch (Exception e) {
				return new ResponseResult(500, "添加失败", e);
			}
		}
		else {
			try {
//				用户ID设置为-1，代表路人
				eventEntryInfo.setUserId(-1L);
				boolean insertResult = eventEntryInfoMapper.setEventEntryInfo(eventEntryInfo);
				if (insertResult) {
					return new ResponseResult(200, "添加成功", true);
				} else {
					return new ResponseResult(500, "添加失败", false);
				}
			} catch (Exception e) {
				return new ResponseResult(500, "添加失败", e);
			}
		}
	}
}
