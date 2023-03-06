package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.*;
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
	public BoUtil getUserDetails(@RequestParam(value="userDtlsId", required=false) Long id) {
		BoUtil boUtil = new BoUtil();
		log.info("----- getUserDetails userDtlsId id:"+ id);
		
		boUtil = demoService.getUserDetails(id);
		
		return boUtil;
	}
	
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public BoUtil getUserList(
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) Long groupId,
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
		
		UserDTO dto = new UserDTO();
		dto.setUserName(userName);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setGroupId(groupId);
		dto.setPageNumber(nPage);
		dto.setPageSize(nPageSize);
		dto.setOffset(offset);
		dto.setSortKey(sortKey);
		
		
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.getUserList(dto);
		
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

	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public BoUtil deleteUser(@RequestBody UserVO userVo) {
		BoUtil boUtil = new BoUtil();
		boUtil = demoService.deleteUser(userVo);
		
		log.info("----- deleteUser userVo:"+ userVo);
		
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
