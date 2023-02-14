package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.util.*;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.UserVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UserResource {

    @Autowired
    UserService demoService;
    
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.GET)
	public BoUtil getUserDetails(@RequestParam(value="userId", required=false) Long id) {
		BoUtil boUtil = new BoUtil();
		log.info("----- getUserDetails user id:"+ id);
		
		boUtil = demoService.getUserDetails(id);
		
		return boUtil;
	}
	
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public BoUtil getUserList() {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getUserList();
		
		return boUtil;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addUser(@RequestBody UserVO userVo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertUser(userVo);
		
		log.info("----- getUserDetails userVo:"+ userVo);
		
		return boUtil;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil updateUser(@RequestBody UserVO userVo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.updateUser(userVo);
		
		log.info("----- updateGroup userVo:"+ userVo);
		
		return boUtil;
	}

	@RequestMapping(value = "/changeStatus", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil changeStatus(@RequestBody WorkflowVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.changeStatus(vo);
		
		log.info("----- changeStatus userVo:"+ vo);
		
		return boUtil;
	}
	
}
