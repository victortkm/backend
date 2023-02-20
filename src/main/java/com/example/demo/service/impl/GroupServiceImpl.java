package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.CommonConst;
import com.example.demo.dao.GroupDAO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.ListResDTO;
import com.example.demo.service.GroupService;
import com.example.demo.service.WorkflowService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupDAO groupDAO;

	@Autowired
	WorkflowService wflService;

	@Override
	public BoUtil getGroupDetails(Long id) {
		BoUtil boUtil = new BoUtil();
		
		try {	
			GroupDTO Group = groupDAO.getGroupDetails(id);
			log.info(Group.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(Group);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil getGroupList(GroupDTO dto) {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<HashMap<String, Object>> list = groupDAO.getGroupList(dto);
			
			ListResDTO res = new ListResDTO();
			res.setList(list);
			res.setPageNumber(dto.getPageNumber());
			
			dto.setTotalCount(true);
			Integer totalCount = groupDAO.getGroupListTotalCount(dto);
			
			res.setTotalCount(totalCount);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(res);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil insertGroup(GroupVO GroupVo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			GroupDTO dto = GroupDTO.buildFromVo(GroupVo);
			groupDAO.insertGroupDtls(dto);
			groupDAO.insertGroup(dto);
			log.info(dto.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getGroupDtlsId());
			vo.setDocNo(dto.getGroupDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_GROUP_MAINTENANCE);
			vo.setKeyValue(dto.getGroupDtlsId().toString());
			vo.setUserId(1l);
			vo.setChangeMode("New");
			wflService.init(vo);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil updateGroup(GroupVO groupVO) {
		BoUtil boUtil = new BoUtil();
		
		try {
			GroupDTO dto = GroupDTO.buildFromVo(groupVO);
			groupDAO.updateGroupDtls(dto);
			
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil changeStatus(GroupVO groupVO) {
		BoUtil boUtil = new BoUtil();
		
		try {
			GroupDTO dto = GroupDTO.buildFromVo(groupVO);
			groupDAO.insertGroupDtls(dto);
			groupDAO.insertGroup(dto);
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	
}
