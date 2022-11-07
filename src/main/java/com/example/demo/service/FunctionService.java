package com.example.demo.service;

import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionVO;

public interface FunctionService {
	
	public BoUtil getFunctionDetails(Long id);
	
	public BoUtil getFunctionList(Long groupId);
	
	public BoUtil insertFunction(FunctionVO vo);
}
