package com.example.demo.mapper;

import java.util.ArrayList;

import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;

public interface CourtInfoMapper {
	ArrayList<CourtOpenInfo> getCourtInfo(CourtOpenInfo courtOpenInfo);
}
