package com.example.demo.service;

import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.WorkflowVO;

public interface FunctionService {
	
//	Function
	public BoUtil getFunctionDetails(Long id, boolean isPend);
	
	public BoUtil getFunctionList(FunctionDTO dto);
	
	public BoUtil insertFunction(FunctionVO vo);
	
	public BoUtil updateFunction(FunctionVO vo);
	
	public BoUtil deleteFunction(FunctionVO vo);
	
	public BoUtil changeStatus(WorkflowVO vo);
	
//	Function Category
	public BoUtil getFunctionCategoryDetails(Long id, boolean isPend);
	
	public BoUtil getFunctionCategoryList(FunctionCategoryDTO dto);
	
	public BoUtil getFuncCatWithFuncList();
	
	public BoUtil insertCategoryFunction(FunctionCategoryVO vo);
	
	public BoUtil updateFunctionCategory(FunctionCategoryVO vo);
	
	public BoUtil deleteFunctionCategory(FunctionCategoryVO vo);
	
	public BoUtil catChangeStatus(WorkflowVO vo);
}
