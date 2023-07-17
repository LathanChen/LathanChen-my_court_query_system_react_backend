package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.ItemInfo;
import com.example.demo.mapper.CourtInfoMapper;
import com.example.demo.mapper.ItemInfoMapper;
import com.example.demo.service.CourtInfoService;
import com.example.demo.service.ItemInfoService;

@Service
public class CourtInfoServiceImpl implements  CourtInfoService{
	@Autowired
	private CourtInfoMapper courtInfoMapper;

	@Override
	public List<CourtInfo> getCourts(){
		return courtInfoMapper.getCourts()
;	}

}
