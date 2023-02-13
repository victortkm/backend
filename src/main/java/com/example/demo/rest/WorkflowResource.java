package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.service.WorkflowService;
import com.example.demo.util.*;
import com.example.demo.vo.UserVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("workflow")
public class WorkflowResource {

    @Autowired
    WorkflowService demoService;
    
//	@RequestMapping(value = "/getUserDetails", method = RequestMethod.GET)
//	public BoUtil getUserDetails(@RequestParam(value="userId", required=false) Long id) {
//		BoUtil boUtil = new BoUtil();
//		log.info("----- getUserDetails user id:"+ id);
//		
//		boUtil = demoService.getUserDetails(id);
//		
//		return boUtil;
//	}
    
	@RequestMapping(value = "/getApprovalListing", method = RequestMethod.GET)
	public BoUtil getApprovalListing() {
		BoUtil boUtil = new BoUtil();
		
		boUtil = demoService.getApprovalListing();
		
		return boUtil;
	}

	@RequestMapping(value = "/initTask", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil initTask(@RequestBody WorkflowVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.init(vo);
		
		log.info("----- getUserDetails userVo:"+ vo);
		
		return boUtil;
	}

	@RequestMapping(value = "/updateTask", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addUser(@RequestBody WorkflowVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.update(vo);
		
		log.info("----- getUserDetails userVo:"+ vo);
		
		return boUtil;
	}
	
}
