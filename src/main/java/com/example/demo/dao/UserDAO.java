package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.WorkflowDTO;

@Repository
public class UserDAO {

	@Autowired
	UserMapper demoMapper;
	
	public UserDTO getUserDetails(Long id) {
		return demoMapper.getUserDetails(id);
	}
	
	public List<HashMap<String, Object>> getUserList(UserDTO dto) {
		return demoMapper.getUserList(dto);
	}
	
	public Integer getUserListTotalCount(UserDTO dto) {
		return demoMapper.getUserListTotalCount(dto);
	}
	
	public int insertUserDtls(UserDTO dto) {
		return demoMapper.insertUserDtls(dto);
	}
	
	public int insertUser(UserDTO dto) {
		return demoMapper.insertUser(dto);
	}
	
	public int updateUser(UserDTO dto) {
		return demoMapper.updateUser(dto);
	}
	
	public int deleteUser(UserDTO dto) {
		return demoMapper.deleteUser(dto);
	}
	
	public Long getMstIdFromPendAppDtlId(Long id) {
		return demoMapper.getMstIdFromPendAppDtlId(id);
	}
	
	public int changeStatus(WorkflowDTO dto) {
		return demoMapper.changeStatus(dto);
	}
}
