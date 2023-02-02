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
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupFunctionVO;
import com.example.demo.vo.GroupVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("function")
public class FunctionResource {
    
    @Autowired
    FunctionService demoService;
	
	@RequestMapping(value = "/getFunctionList", method = RequestMethod.GET)
	public BoUtil getGroupList(@RequestParam(value="userId", required=false) Long groupId) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getFunctionList(groupId);
		
		return boUtil;
	}

	@RequestMapping(value = "/addFunction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addFunction(@RequestBody FunctionVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertFunction(vo);
		
		log.info("----- addFunction vo:"+ vo);
		
		return boUtil;
	}
	
	@RequestMapping(value = "/getFunctionCategoryList", method = RequestMethod.GET)
	public BoUtil getFunctionCategoryList() {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getFunctionCategoryList();
		
		return boUtil;
	}

	@RequestMapping(value = "/addFunctionCategory", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addFunctionCategory(@RequestBody FunctionCategoryVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertCategoryFunction(vo);
		
		log.info("----- addFunctionCategory vo:"+ vo);
		
		return boUtil;
	}

	@RequestMapping(value = "/deleteFunctionCategory", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil deleteFunctionCategory(@RequestBody FunctionCategoryVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.deleteFunctionCategory(vo);
		
		log.info("----- deleteFunctionCategory vo:"+ vo);
		
		return boUtil;
	}

	@RequestMapping(value = "/addGroupFunction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addGroupFunction(@RequestBody GroupFunctionVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertGroupFunction(vo);
		
		log.info("----- addGroupFunction vo:"+ vo);
		
		return boUtil;
	}
	
}
