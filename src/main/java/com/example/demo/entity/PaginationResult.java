package com.example.demo.entity;

import java.util.List;

import lombok.Data;

@Data
//用于传递分页结果的容器类
public class PaginationResult<T> {
	private List<T> list;       // 查询结果列表
	private int count;       	// 查询结果列表明细条数
    private long total;         // 总记录数
    private int pageNum;        // 当前页码
    private int pageSize;       // 每页记录数
    private int pages;          // 总页数
}
