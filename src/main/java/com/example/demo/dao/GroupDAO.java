package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.GroupMapper;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.WorkflowDTO;

@Repository
public class GroupDAO {

	@Autowired
	GroupMapper demoMapper;
	
	public GroupDTO getGroupDetails(Long id) {
		return demoMapper.getGroupDetails(id);
	}
	
	public GroupDTO getGroupDetailsFromDtlsId(Long id) {
		return demoMapper.getGroupDetailsFromDtlsId(id);
	}
	
	public List<HashMap<String, Object>> getGroupList(GroupDTO dto) {
		return demoMapper.getGroupList(dto);
	}
	
	public Integer getGroupListTotalCount(GroupDTO dto) {
		return demoMapper.getGroupListTotalCount(dto);
	}
	
	public int insertGroupDtls(GroupDTO dto) {
		return demoMapper.insertGroupDtls(dto);
	}
	
	public int insertGroup(GroupDTO dto) {
		return demoMapper.insertGroup(dto);
	}
	
	public int updateGroup(GroupDTO dto) {
		return demoMapper.updateGroup(dto);
	}
	
	public int deleteGroup(GroupDTO dto) {
		return demoMapper.deleteGroup(dto);
	}
	
	public Long getMstIdFromPendAppDtlId(Long id) {
		return demoMapper.getMstIdFromPendAppDtlId(id);
	}
	
	public int changeStatus(WorkflowDTO dto) {
		return demoMapper.changeStatus(dto);
	}
}
