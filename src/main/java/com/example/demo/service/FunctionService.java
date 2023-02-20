package com.example.demo.service;

import com.example.demo.dto.FunctionDTO;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;

public interface FunctionService {
	
	public BoUtil getFunctionDetails(Long id);
	
	public BoUtil getFunctionList(FunctionDTO dto);
	
	public BoUtil insertFunction(FunctionVO vo);
	
	public BoUtil getFunctionCategoryList();
	
	public BoUtil insertCategoryFunction(FunctionCategoryVO vo);
	
	public BoUtil deleteFunctionCategory(FunctionCategoryVO vo);
}
