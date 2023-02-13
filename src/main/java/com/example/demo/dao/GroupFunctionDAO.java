package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.GroupFunctionMapper;
import com.example.demo.dto.GroupFunctionDTO;

@Repository
public class GroupFunctionDAO {

	@Autowired
	GroupFunctionMapper demoMapper;
	
//	public FunctionDTO getFunctionDetails(Long id) {
//		return demoMapper.getFunctionDetails(id);
//	}
//	
//	public List<HashMap<String, Object>> getFunctionList(Long groupId) {
//		return demoMapper.getFunctionList(groupId);
//	}
//	
//	public int insertFunctionDtls(FunctionDTO dto) {
//		return demoMapper.insertFunctionDtls(dto);
//	}
//	
//	public int insertFunction(FunctionDTO dto) {
//		return demoMapper.insertFunction(dto);
//	}
//	
//	public List<HashMap<String, Object>> getFunctionCategoryList() {
//		return demoMapper.getFunctionCategoryList();
//	}
//	
//	public int updateFunctionCategory(FunctionCategoryDTO dto) {
//		return demoMapper.updateFunctionCategory(dto);
//	}
//	
//	public int insertFunctionCategory(FunctionCategoryDTO dto) {
//		return demoMapper.insertFunctionCategory(dto);
//	}
//	
	public int insertGroupFunction(GroupFunctionDTO dto) {
		return demoMapper.insertGroupFunction(dto);
	}
	
//	public List<Long> getGroupFunctionListByGroupId(Long id) {
//		return demoMapper.getGroupFunctionListByGroupId(id);
//	}
//	
//	public int updateGroupFunction(GroupFunctionDTO dto) {
//		return demoMapper.updateGroupFunction(dto);
//	}
	
}
