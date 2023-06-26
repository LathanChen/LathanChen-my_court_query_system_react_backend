package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;

public interface CourtInfoService {

	ArrayList<CourtOpenInfo> getCourtInfo(CourtOpenInfo courtOpenInfo);
}
