package com.example.demo.service;

import com.example.demo.dto.GroupDTO;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.WorkflowVO;

public interface GroupService {
	
	public BoUtil getGroupDetails(Long id);
	
	public BoUtil getGroupList(GroupDTO dto);
	
	public BoUtil insertGroup(GroupVO groupVO);
	
	public BoUtil updateGroup(GroupVO groupVO);
	
	public BoUtil deleteGroup(GroupVO groupVO);
	
	public BoUtil changeStatus(WorkflowVO vo);
}
