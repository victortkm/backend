package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.service.FunctionService;
import com.example.demo.util.*;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupFunctionVO;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("function")
public class FunctionResource {
    
    @Autowired
    FunctionService demoService;
	
	@RequestMapping(value = "/getFunctionList", method = RequestMethod.GET)
	public BoUtil getGroupList(
//			@RequestParam(value="userId", required=false) Long groupId,
			@RequestParam(required = false) String pageNumber, 
			@RequestParam(required = false) String pageSize,
			@RequestParam(required = false) String sortKey) {
		
		int nPage = 1;
		int nPageSize = CommonConst.DEFAULT_PAGE_SIZE;
		if (pageNumber != null && !pageNumber.isEmpty()) {
			try {
				nPage = Integer.parseInt(pageNumber);
			} catch (Exception e) {
			}
		}
		if (pageSize != null && !pageSize.isEmpty()) {
			try {
				nPageSize = Integer.parseInt(pageSize);
			} catch (Exception e) {
			}
		}
		int offset = (nPage - 1) * nPageSize;
		FunctionDTO dto = new FunctionDTO();
		dto.setPageNumber(nPage);
		dto.setPageSize(nPageSize);
		dto.setOffset(offset);
		dto.setSortKey(sortKey);
		
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getFunctionList(dto);
		
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

	@RequestMapping(value = "/changeStatus", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil changeStatus(@RequestBody WorkflowVO vo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.changeStatus(vo);
		
		log.info("----- changeStatus userVo:"+ vo);
		
		return boUtil;
	}
	
}
