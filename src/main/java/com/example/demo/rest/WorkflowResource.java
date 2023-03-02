package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.WorkflowDTO;
import com.example.demo.service.WorkflowService;
import com.example.demo.util.*;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("workflow")
public class WorkflowResource {

    @Autowired
    WorkflowService demoService;
    
	@RequestMapping(value = "/getApprovalListing", method = RequestMethod.GET)
	public BoUtil getApprovalListing(
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
		WorkflowDTO dto = new WorkflowDTO();
		dto.setPageNumber(nPage);
		dto.setPageSize(nPageSize);
		dto.setOffset(offset);
		dto.setSortKey(sortKey);
		
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getApprovalListing(dto);
		
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
