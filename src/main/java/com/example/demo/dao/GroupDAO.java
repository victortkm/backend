package com.example.demo.dao;

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
	
	public List<GroupDTO> getGroupList() {
		return demoMapper.getGroupList();
	}
	
	public int insertGroupDtls(GroupDTO dto) {
		return demoMapper.insertGroupDtls(dto);
	}
	
	public int insertGroup(GroupDTO dto) {
		return demoMapper.insertGroup(dto);
	}
}
