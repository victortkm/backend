package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dto.UserDTO;

@Repository
public class UserDAO {

	@Autowired
	UserMapper demoMapper;
	
	public UserDTO getUserDetails(Long id) {
		return demoMapper.getUserDetails(id);
	}
	
	public List<HashMap<String, Object>> getUserList() {
		return demoMapper.getUserList();
	}
	
	public int insertUserDtls(UserDTO dto) {
		return demoMapper.insertUserDtls(dto);
	}
	
	public int insertUser(UserDTO dto) {
		return demoMapper.insertUser(dto);
	}
}
