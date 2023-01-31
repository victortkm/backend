package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.activemq.QueueProducer;
import com.example.demo.service.GroupService;
import com.example.demo.util.*;
import com.example.demo.vo.GroupVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("group")
public class GroupResource {
    
    @Autowired
    GroupService demoService;
	
	@RequestMapping(value = "/getGroupDetails", method = RequestMethod.GET)
	public BoUtil getGroupDetails(@RequestParam(value="userId", required=false) Long id) {
		BoUtil boUtil = new BoUtil();
		log.info("----- getGroupDetails user id:"+ id);
		
		boUtil = demoService.getGroupDetails(id);
		
		return boUtil;
	}
	
	@RequestMapping(value = "/getGroupList", method = RequestMethod.GET)
	public BoUtil getGroupList() {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getGroupList();
		
		return boUtil;
	}

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addGroup(@RequestBody GroupVO groupVO) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertGroup(groupVO);
		
		log.info("----- getGroupDetails groupVO:"+ groupVO);
		
		return boUtil;
	}
	
}
