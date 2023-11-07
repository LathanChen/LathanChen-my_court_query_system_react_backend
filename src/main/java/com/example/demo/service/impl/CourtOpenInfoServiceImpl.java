package com.example.demo.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.PaginationResult;
import com.example.demo.entity.ResponseResult;
import com.example.demo.mapper.CourtOpenInfoMapper;
import com.example.demo.service.CourtOpenInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CourtOpenInfoServiceImpl implements CourtOpenInfoService{

	public static final String BASKETBALL_ITEM_ID = "1";
	public static final String TABLE_TENNIS_ITEM_ID = "2";
	public static final String BADMINTON_ITEM_ID = "3";
	public static final String VOLLEYBALL_ITEM_ID = "4";

	@Autowired
	private CourtOpenInfoMapper courtOpenInfoMapper;

	@Override
	public ArrayList<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo) {
//	public PageInfo<CourtOpenInfo> getInfo(CourtOpenInfo courtOpenInfo) {
		// TODO 自動生成されたメソッド・スタブ
//		前端的MUI框架，DataGrid的页码计算不知道哪出了问题，ShowQueryData组件的paginationModel.page必须设置为0开始，所以在分页查询的时候只能给每个PageNum加上1
//		int PageNum = courtOpenInfo.getPageNum();
//		int PageSize = courtOpenInfo.getPageSize();
//		PageHelper.startPage(PageNum, PageSize);     // 使用PageHelper进行分页查询获得场地查询结果
		List<CourtOpenInfo> _courtList = courtOpenInfoMapper.getInfo(courtOpenInfo);
	    PageInfo<CourtOpenInfo> pageInfo = new PageInfo<>(_courtList);

//	    PaginationResult<CourtOpenInfo> CourtOpenInfoResult = new PaginationResult<>();
//	    CourtOpenInfoResult.setList(pageInfo.getList());
//	    System.out.println(pageInfo);
//	    System.out.println("-------------------");
//	    CourtOpenInfoResult.setTotal(pageInfo.getTotal());
//	    CourtOpenInfoResult.setPageNum(pageInfo.getPageNum());
//	    CourtOpenInfoResult.setPageSize(pageInfo.getPageSize());
//	    CourtOpenInfoResult.setPages(pageInfo.getPages());
//	    CourtOpenInfoResult.setCount(pageInfo.getList().size());
//	    System.out.println(CourtOpenInfoResult.getList());
//	    System.out.println("-------------------2");
	    ArrayList<CourtOpenInfo> courtList = new ArrayList<>(_courtList);
        return courtList;
	}

	@Override
//	在Java中，泛型类型参数不能是基本数据类型，例如int、char、boolean等。因此，尝试将Map<String, int>作为方法的返回类型时，编译器会报错。
	public Map<String,List> getTodayInfo(int weekNumber,int dayOfWeekInWeek) {
		// TODO 自動生成されたメソッド・スタブ
		CourtOpenInfo[] courtOpenInfos = courtOpenInfoMapper.getTodayInfo(weekNumber, dayOfWeekInWeek);

		LocalTime currentTime = LocalTime.now();
		// 定义时间格式化器，将时间格式化为小时和分钟
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

        // 格式化时间为数值类型
        int currentTimeInt = Integer.parseInt(currentTime.format(formatter));

//		int basketballNotStart = 0;
//		int basketballEnded = 0;
//		int tableTennisNotStart = 0;
//		int tableTennisEnded = 0;
//		int badmintonNotStart = 0;
//		int badmintonEnded = 0;
//		int volleyballNotStart = 0;
//		int volleyballEnded = 0;

		List<CourtOpenInfo> basketballNotStartList = new ArrayList<>();
		List<CourtOpenInfo> basketballEndedList = new ArrayList<>();
		List<CourtOpenInfo> tableTennisNotStartList = new ArrayList<>();
		List<CourtOpenInfo> tableTennisEndedList = new ArrayList<>();
		List<CourtOpenInfo> badmintonNotStartList = new ArrayList<>();
		List<CourtOpenInfo> badmintonEndedList = new ArrayList<>();
		List<CourtOpenInfo> volleyballNotStartList = new ArrayList<>();
		List<CourtOpenInfo> volleyballEndedList = new ArrayList<>();

		for (int a = 0;a < courtOpenInfos.length;a++) {
			CourtOpenInfo courtOpenInfo = courtOpenInfos[a];
//			先通过"-"分割字符串，再去除时间数据中的冒号，最后转化为int类型
			int openTime = Integer.parseInt(courtOpenInfo.getCourtOpenTime().split("-")[0].replace(":", ""));
			int endTime = Integer.parseInt(courtOpenInfo.getCourtOpenTime().split("-")[1].replace(":", ""));
			if (courtOpenInfo.getCourtOpenItemId().equals(BASKETBALL_ITEM_ID)) {
				if (openTime > currentTimeInt) {
					basketballNotStartList.add(courtOpenInfo);
				}
				if (endTime < currentTimeInt) {
					basketballEndedList.add(courtOpenInfo);
				}
			}
			if (courtOpenInfo.getCourtOpenItemId().equals(TABLE_TENNIS_ITEM_ID)) {
				if (openTime > currentTimeInt) {
					tableTennisNotStartList.add(courtOpenInfo);
				}
				if (endTime < currentTimeInt) {
					tableTennisEndedList.add(courtOpenInfo);
				}
			}
			if (courtOpenInfo.getCourtOpenItemId().equals(BADMINTON_ITEM_ID)) {
				if (openTime > currentTimeInt) {
					badmintonNotStartList.add(courtOpenInfo);
				}
				if (endTime < currentTimeInt) {
					badmintonEndedList.add(courtOpenInfo);
				}
			}
			if (courtOpenInfo.getCourtOpenItemId().equals(VOLLEYBALL_ITEM_ID)) {
				if (openTime > currentTimeInt) {
					volleyballNotStartList.add(courtOpenInfo);
				}
				if (endTime < currentTimeInt) {
					volleyballEndedList.add(courtOpenInfo);
				}
			}

		}

//		System.out.println(basketballNotStart);
//		System.out.println(basketballEnded);
//		System.out.println(tableTennisNotStart);
//		System.out.println(tableTennisEnded);
//		System.out.println(badmintonNotStart);
//		System.out.println(badmintonEnded);
//		System.out.println(volleyballNotStart);
//		System.out.println(volleyballEnded);
//		System.out.println(courtOpenInfos.length);
//		System.out.println(currentTimeInt);

		Map<String,List> todayEventMap = new HashMap<>();
		todayEventMap.put("basketballNotStart", basketballNotStartList);
		todayEventMap.put("basketballEnded", basketballEndedList);
		todayEventMap.put("tableTennisNotStart", tableTennisNotStartList);
		todayEventMap.put("tableTennisEnded", tableTennisEndedList);
		todayEventMap.put("badmintonNotStart", badmintonNotStartList);
		todayEventMap.put("badmintonEnded", badmintonEndedList);
		todayEventMap.put("volleyballNotStart", volleyballNotStartList);
		todayEventMap.put("volleyballEnded", volleyballEndedList);
		return todayEventMap;
	}

	@Override
	public ArrayList<CourtOpenInfo> getInfoByAdmin(CourtOpenInfo courtOpenInfo) {
		// TODO 自動生成されたメソッド・スタブ
		return courtOpenInfoMapper.getInfoByAdmin(courtOpenInfo);
	}

	@Override
	public ResponseResult setInfo(CourtOpenInfo courtOpenInfo) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			courtOpenInfo.setCourtOpenUpdateTime(new Timestamp(System.currentTimeMillis()));
			boolean setResult = courtOpenInfoMapper.setInfo(courtOpenInfo);
			System.out.println("111111111111111111111111");
			System.out.println(courtOpenInfo);
			if (setResult) {
				return new ResponseResult(200,"添加成功",true);
			}
			else {
				return new ResponseResult(500,"添加失败",false);
			}
		}
		catch (Exception e){
			return new ResponseResult(500,"添加失败",e);
		}

	}

	@Override
	public CourtOpenInfo getInfoById(int courtOpenItemId) {
		// TODO 自動生成されたメソッド・スタブ
		return courtOpenInfoMapper.getInfoById(courtOpenItemId);
	}

	@Override
	public boolean updateInfo(CourtOpenInfo courtOpenInfo) {
		// TODO 自動生成されたメソッド・スタブ
		courtOpenInfo.setCourtOpenUpdateTime(new Timestamp(System.currentTimeMillis()));
		if(courtOpenInfoMapper.updateInfo(courtOpenInfo) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean deleteInfo(List infoIdlist) {
		// TODO 自動生成されたメソッド・スタブ
		if(courtOpenInfoMapper.deleteInfo(infoIdlist) != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getCourtItemNames(int courtId) {
		// TODO 自動生成されたメソッド・スタブ
		return courtOpenInfoMapper.getCourtItemNames(courtId);
	}

}
