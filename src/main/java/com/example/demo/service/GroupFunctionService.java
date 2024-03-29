package com.example.demo.service;

import com.example.demo.util.BoUtil;
import com.example.demo.vo.GroupFunctionVO;
import com.example.demo.vo.WorkflowVO;

public interface GroupFunctionService {
	
	public BoUtil getGroupFunctionDtls(Long id);
	
	public BoUtil getGroupFunctionListFromGroupId(Long groupId);
	
	public BoUtil insertGroupFunction(GroupFunctionVO vo);
	
	public BoUtil changeStatus(WorkflowVO vo);
}
