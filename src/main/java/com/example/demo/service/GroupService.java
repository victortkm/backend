package com.example.demo.service;

import com.example.demo.util.BoUtil;
import com.example.demo.vo.GroupVO;

public interface GroupService {
	
	public BoUtil getGroupDetails(Long id);
	
	public BoUtil getGroupList();
	
	public BoUtil insertGroup(GroupVO groupVO);
	
	public BoUtil updateGroup(GroupVO groupVO);
	
	public BoUtil changeStatus(GroupVO groupVO);
}
