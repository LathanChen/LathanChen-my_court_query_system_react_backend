package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ItemInfo;
import com.example.demo.service.ItemInfoService;

@RestController
@RequestMapping("/iteminfo")
public class ItemInfoController {
	@Autowired
    private ItemInfoService itemInfoService;

	@RequestMapping(value="",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<ItemInfo> getItems(){
		return (ArrayList<ItemInfo>) itemInfoService.getItems();
	}

}
