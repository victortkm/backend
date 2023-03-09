package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.GroupDTO;
import com.example.demo.service.GroupService;
import com.example.demo.util.*;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("group")
public class GroupResource {
    
    @Autowired
    GroupService demoService;
	
	@RequestMapping(value = "/getGroupDetails", method = RequestMethod.GET)
	public BoUtil getGroupDetails(@RequestParam(value="groupDtlsId", required=false) Long id, @RequestParam(value="isPend", required=false) boolean isPend) {
		BoUtil boUtil = new BoUtil();
		log.info("----- getGroupDetails groupDtlsId id:"+ id);
		
		boUtil = demoService.getGroupDetails(id, isPend);
		
		return boUtil;
	}
	
	@RequestMapping(value = "/getGroupList", method = RequestMethod.GET)
	public BoUtil getGroupList(
			@RequestParam(required = false) String groupName,
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
		
		GroupDTO dto = new GroupDTO();
		dto.setGroupName(groupName);
		dto.setPageNumber(nPage);
		dto.setPageSize(nPageSize);
		dto.setOffset(offset);
		dto.setSortKey(sortKey);
		
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getGroupList(dto);
		log.info("----- getGroupList");
		
		return boUtil;
	}

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil addGroup(@RequestBody GroupVO groupVO) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.insertGroup(groupVO);
		
		log.info("----- addGroup groupVO:"+ groupVO);
		
		return boUtil;
	}

	@RequestMapping(value = "/updateGroup", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil updateGroup(@RequestBody GroupVO groupVO) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.updateGroup(groupVO);
		
		log.info("----- updateGroup groupVO:"+ groupVO);
		
		return boUtil;
	}

	@RequestMapping(value = "/deleteGroup", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil deleteGroup(@RequestBody GroupVO groupVO) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.deleteGroup(groupVO);
		
		log.info("----- deleteGroup groupVO:"+ groupVO);
		
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
