package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FunctionDAO;
import com.example.demo.dao.GroupFunctionDAO;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.service.GroupFunctionService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupFunctionVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupFunctionServiceImpl implements GroupFunctionService {
	
	@Autowired
	GroupFunctionDAO dao;

	@Override
	public BoUtil insertGroupFunction(GroupFunctionVO vo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			GroupFunctionDTO dto = GroupFunctionDTO.buildFromVo(vo);
			
//			List<Long> list = dao.getGroupFunctionListByGroupId(vo.getGroupId());
//			
//			//	update old group functions to inactive
//			for(Long l: list) {
//				GroupFunctionDTO temp = new GroupFunctionDTO();
//				temp.setGroupFunctionId(l);
//				temp.setStatus("N");
//				
//				dao.updateGroupFunction(temp);
//			}
			
			//	insert new group functions to db
			for(Long funcId: dto.getFunctionIds()) {
				dto.setFunctionId(funcId);
				dto.setStatus("A");
				dao.insertGroupFunction(dto);
				dto.setFunctionId(0l);
			}
			
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
