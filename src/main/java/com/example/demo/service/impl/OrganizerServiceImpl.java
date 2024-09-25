package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.Organizer;
import com.example.demo.entity.ResponseResult;
import com.example.demo.mapper.ItemInfoMapper;
import com.example.demo.mapper.OrganizerMapper;
import com.example.demo.service.ItemInfoService;
import com.example.demo.service.OrganizerService;

@Service
public class OrganizerServiceImpl implements OrganizerService{
	private final UserServiceImpl userServiceImpl;

	private final OrganizerMapper organizerMapper;

	@Autowired
	public OrganizerServiceImpl(UserServiceImpl userServiceImpl,OrganizerMapper organizerMapper) {
		this.userServiceImpl = userServiceImpl;
		this.organizerMapper = organizerMapper;
	}

	@Override
	public ResponseResult getOrganizerInfoByUserId() {

		long _userId = userServiceImpl.fecthUserInfoCommon().getId();
		int userId = (int) _userId;

		List organizerInfoResultList = organizerMapper.getOrganizerInfoByUserId(userId);

		if (!organizerInfoResultList.isEmpty()) {
			return new ResponseResult(200,"查询成功！",organizerInfoResultList);
		}
		else {
			return new ResponseResult(404,"没有查询到信息！");
		}
	}
}
