package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GroupDAO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.service.GroupService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.GroupVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupDAO groupDAO;

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
	public BoUtil getGroupList() {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<GroupDTO> list = groupDAO.getGroupList();
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(list);
			
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
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	
}
