package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemInfo;
import com.example.demo.mapper.ItemInfoMapper;
import com.example.demo.service.ItemInfoService;

@Service
public class ItemInfoServiceImpl implements  ItemInfoService{
	@Autowired
	private ItemInfoMapper itemInfoMapper;

	public List<ItemInfo> getItems(){
		return itemInfoMapper.getItems();
	}
}
