package com.example.demo.service;

import com.example.demo.util.BoUtil;
import com.example.demo.vo.UserVO;

public interface UserService {
	
	public BoUtil getUserDetails(Long id);
	
	public BoUtil getUserList();
	
	public BoUtil insertUser(UserVO userVo);
}
