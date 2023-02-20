package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.GroupMapper;
import com.example.demo.dto.GroupDTO;

@Repository
public class GroupDAO {

	@Autowired
	GroupMapper demoMapper;
	
	public GroupDTO getGroupDetails(Long id) {
		return demoMapper.getGroupDetails(id);
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
	
	public int updateGroupDtls(GroupDTO dto) {
		return demoMapper.updateGroupDtls(dto);
	}
	
	public int changeStatus(GroupDTO dto) {
		return demoMapper.changeStatus(dto);
	}
}
