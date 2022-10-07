package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO demoDAO;

	@Override
	public BoUtil getUserDetails(Long id) {
		BoUtil boUtil = new BoUtil();
		
		try {	
			UserDTO user = demoDAO.getUserDetails(id);
			log.info(user.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(user);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil getUserList() {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<HashMap<String, Object>> list = demoDAO.getUserList();
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(list);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil insertUser(UserVO userVo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			UserDTO dto = UserDTO.buildFromVo(userVo);
			demoDAO.insertUserDtls(dto);
			demoDAO.insertUser(dto);
			log.info(dto.toString());
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	
}
