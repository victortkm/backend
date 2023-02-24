package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.UserVO;
import com.example.demo.vo.WorkflowVO;

public interface UserService {
	
	public BoUtil getUserDetails(Long id);
	
	public BoUtil getUserList(UserDTO dto);
	
	public BoUtil insertUser(UserVO userVo);
	
	public BoUtil updateUser(UserVO userVo);
	
	public BoUtil deleteUser(UserVO userVo);
	
	public BoUtil changeStatus(WorkflowVO vo);
}
