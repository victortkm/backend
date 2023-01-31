package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FunctionService;
import com.example.demo.util.*;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("function")
public class FunctionResource {
    
    @Autowired
    FunctionService demoService;
	
//	@RequestMapping(value = "/getGroupDetails", method = RequestMethod.GET)
//	public BoUtil getGroupDetails(@RequestParam(value="userId", required=false) Long id) {
//		BoUtil boUtil = new BoUtil();
//		log.info("----- getGroupDetails user id:"+ id);
//		
//		boUtil = demoService.getGroupDetails(id);
//		
//		return boUtil;
//	}
	
	@RequestMapping(value = "/getFunctionList", method = RequestMethod.GET)
	public BoUtil getGroupList(@RequestParam(value="userId", required=false) Long groupId) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getFunctionList(groupId);
		
		return boUtil;
	}

	@RequestMapping(value = "/addFunction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addGroup(@RequestBody FunctionVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertFunction(vo);
		
		log.info("----- getGroupDetails FunctionVO:"+ vo);
		
		return boUtil;
	}
	
}
