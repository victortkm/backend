package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.GroupFunctionService;
import com.example.demo.util.*;
import com.example.demo.vo.GroupFunctionVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("grpfunc")
public class GroupFunctionResource {
    
    @Autowired
    GroupFunctionService demoService;
	
	@RequestMapping(value = "/getGroupFunctionDtls", method = RequestMethod.GET)
	public BoUtil getGroupFunctionDtls(@RequestParam(value="funcCatId", required=false) Long id) {
		BoUtil boUtil = new BoUtil();
		log.info("----- getGroupFunctionDtls dtls id:"+ id);
		
		boUtil = demoService.getGroupFunctionDtls(id);
		
		return boUtil;
	}
	
	@RequestMapping(value = "/getGroupFunctionListFromGroupId", method = RequestMethod.GET)
	public BoUtil getGroupFunctionListFromGroupId(@RequestParam(value="groupId", required=false) Long groupId) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getGroupFunctionListFromGroupId(groupId);
		
		log.info("----- getGroupFunctionListFromGroupId groupId:"+ groupId);
		
		return boUtil;
	}
    
	@RequestMapping(value = "/addGroupFunction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addGroupFunction(@RequestBody GroupFunctionVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertGroupFunction(vo);
		
		log.info("----- addGroupFunction vo:"+ vo);
		
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
