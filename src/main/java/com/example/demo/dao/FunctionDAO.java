package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.FunctionMapper;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
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
	
	public int deleteFunction(FunctionDTO dto) {
		return demoMapper.deleteFunction(dto);
	}
	
	public Long getMstIdFromPendAppDtlId(Long id) {
		return demoMapper.getMstIdFromPendAppDtlId(id);
	}
	
	public int changeStatus(WorkflowDTO dto) {
		return demoMapper.changeStatus(dto);
	}
	
//	Function Category
	
	public FunctionCategoryDTO getFunctionCategoryDetails(Long id) {
		return demoMapper.getFunctionCategoryDetails(id);
	}
	
	public List<HashMap<String, Object>> getFunctionCategoryList(FunctionCategoryDTO dto) {
		return demoMapper.getFunctionCategoryList(dto);
	}
	
	public Integer getFunctionCategoryListTotalCount(FunctionCategoryDTO dto) {
		return demoMapper.getFunctionCategoryListTotalCount(dto);
	}
	
	public int insertFunctionCategory(FunctionCategoryDTO dto) {
		return demoMapper.insertFunctionCategory(dto);
	}
	
	public int insertFunctionCategoryDtls(FunctionCategoryDTO dto) {
		return demoMapper.insertFunctionCategoryDtls(dto);
	}
	
	public int updateFunctionCategory(FunctionCategoryDTO dto) {
		return demoMapper.updateFunctionCategory(dto);
	}
	
	public int deleteFunctionCategory(FunctionCategoryDTO dto) {
		return demoMapper.deleteFunctionCategory(dto);
	}
	
	public Long getCatMstIdFromPendAppDtlId(Long id) {
		return demoMapper.getCatMstIdFromPendAppDtlId(id);
	}
	
	public int catChangeStatus(WorkflowDTO dto) {
		return demoMapper.catChangeStatus(dto);
	}
}
