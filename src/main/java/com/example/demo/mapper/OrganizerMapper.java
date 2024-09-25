package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.Organizer;

public interface OrganizerMapper  {
	List<Organizer> getOrganizerInfoByUserId(int userId);
}

