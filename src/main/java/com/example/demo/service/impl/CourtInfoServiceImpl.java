package com.example.demo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.mapper.CourtInfoMapper;
import com.example.demo.service.CourtInfoService;

@Service
public class CourtInfoServiceImpl implements CourtInfoService{
	@Autowired
	private CourtInfoMapper courtInfoMapper;

	@Override
	public ArrayList<CourtOpenInfo> getCourtInfo(CourtOpenInfo courtOpenInfo) {
		// TODO 自動生成されたメソッド・スタブ
		return courtInfoMapper.getCourtInfo(courtOpenInfo);
	}

}
