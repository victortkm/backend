package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.GroupFunctionMapper;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.dto.WorkflowDTO;

@Repository
public class GroupFunctionDAO {

	@Autowired
	GroupFunctionMapper demoMapper;
	
	public GroupFunctionDTO getGroupFunctionDetailsFromGroupFunctionId(Long groupId) {
		return demoMapper.getGroupFunctionDetailsFromGroupFunctionId(groupId);
	}
	
	public GroupFunctionDTO getGroupFunctionByDtlsId(Long id) {
		return demoMapper.getGroupFunctionByDtlsId(id);
	}
	
	public List<Long> getGroupFunctionListFromGroupId(Long groupId) {
		return demoMapper.getGroupFunctionListFromGroupId(groupId);
	}
	
	public List<Long> getMstIdFromGroupId(Long groupId) {
		return demoMapper.getMstIdFromGroupId(groupId);
	}
	
	public int insertGroupFunctionDtls(GroupFunctionDTO dto) {
		return demoMapper.insertGroupFunctionDtls(dto);
	}
	
	public int insertGroupFunctionDtlsRecord(GroupFunctionDTO dto) {
		return demoMapper.insertGroupFunctionDtlsRecord(dto);
	}
	
	public int insertGroupFunction(GroupFunctionDTO dto) {
		return demoMapper.insertGroupFunction(dto);
	}
	
	public Long getMstIdFromPendAppDtlId(Long id) {
		return demoMapper.getMstIdFromPendAppDtlId(id);
	}
	
	public int changeStatus(WorkflowDTO dto) {
		return demoMapper.changeStatus(dto);
	}
	
	public int deleteGrpFunc(Long mstId) {
		return demoMapper.deleteGrpFunc(mstId);
	}
	
	public List<Long> getGroupFunctionByGroupDtlsId(Long groupId) {
		return demoMapper.getGroupFunctionByGroupDtlsId(groupId);
	}
	
}
