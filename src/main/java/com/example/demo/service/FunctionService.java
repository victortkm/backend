package com.example.demo.service;

import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupFunctionVO;

public interface FunctionService {
	
	public BoUtil getFunctionDetails(Long id);
	
	public BoUtil getFunctionList(Long groupId);
	
	public BoUtil insertFunction(FunctionVO vo);
	
	public BoUtil getFunctionCategoryList();
	
	public BoUtil insertCategoryFunction(FunctionCategoryVO vo);
	
	public BoUtil deleteFunctionCategory(FunctionCategoryVO vo);
	
	public BoUtil insertGroupFunction(GroupFunctionVO vo);
}
