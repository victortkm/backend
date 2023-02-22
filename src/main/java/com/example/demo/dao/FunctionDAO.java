package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.FunctionMapper;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.dto.WorkflowDTO;

@Repository
public class FunctionDAO {

	@Autowired
	FunctionMapper demoMapper;
	
//	Function
	
	public FunctionDTO getFunctionDetails(Long id) {
		return demoMapper.getFunctionDetails(id);
	}
	
	public List<HashMap<String, Object>> getFunctionList(FunctionDTO dto) {
		return demoMapper.getFunctionList(dto);
	}
	
	public Integer getFunctionListTotalCount(FunctionDTO dto) {
		return demoMapper.getFunctionListTotalCount(dto);
	}
	
	public int insertFunctionDtls(FunctionDTO dto) {
		return demoMapper.insertFunctionDtls(dto);
	}
	
	public int insertFunction(FunctionDTO dto) {
		return demoMapper.insertFunction(dto);
	}
	
	public int updateFunction(FunctionDTO dto) {
		return demoMapper.updateFunction(dto);
	}
	
	public Long getMstIdFromPendAppDtlId(Long id) {
		return demoMapper.getMstIdFromPendAppDtlId(id);
	}
	
	public int changeStatus(WorkflowDTO dto) {
		return demoMapper.changeStatus(dto);
	}
	
//	Function Category
	public List<HashMap<String, Object>> getFunctionCategoryList() {
		return demoMapper.getFunctionCategoryList();
	}
	
	public int updateFunctionCategory(FunctionCategoryDTO dto) {
		return demoMapper.updateFunctionCategory(dto);
	}
	
	public int insertFunctionCategory(FunctionCategoryDTO dto) {
		return demoMapper.insertFunctionCategory(dto);
	}
	
}
