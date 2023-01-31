package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.FunctionMapper;
import com.example.demo.dto.FunctionDTO;

@Repository
public class FunctionDAO {

	@Autowired
	FunctionMapper demoMapper;
	
	public FunctionDTO getFunctionDetails(Long id) {
		return demoMapper.getFunctionDetails(id);
	}
	
	public List<HashMap<String, Object>> getFunctionList(Long groupId) {
		return demoMapper.getFunctionList(groupId);
	}
	
//	public int insertFunctionDtls(FunctionDTO dto) {
//		return demoMapper.insertFunctionDtls(dto);
//	}
	
	public int insertFunction(FunctionDTO dto) {
		return demoMapper.insertFunction(dto);
	}
}
