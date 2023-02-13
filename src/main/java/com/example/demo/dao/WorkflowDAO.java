package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.WorkflowMapper;
import com.example.demo.dto.WorkflowDTO;

@Repository
public class WorkflowDAO {

	@Autowired
	WorkflowMapper demoMapper;
	
	public HashMap<String,Object> getApprovalListing() {
		return demoMapper.getApprovalListing();
	}
	
	public int insert(WorkflowDTO dto) {
		return demoMapper.insert(dto);
	}
	
	public void update(WorkflowDTO reqDTO) {
		demoMapper.update(reqDTO);
	}
}
