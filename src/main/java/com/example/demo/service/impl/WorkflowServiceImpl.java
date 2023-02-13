package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WorkflowDAO;
import com.example.demo.dto.WorkflowDTO;
import com.example.demo.service.WorkflowService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WorkflowServiceImpl implements WorkflowService {
	
	@Autowired
	WorkflowDAO demoDAO;

	@Override
	public BoUtil getApprovalListing() {
		try {
			BoUtil boUtil = BoUtil.getDefaultFalseBo();

			HashMap<String, Object> list = demoDAO.getApprovalListing();
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(list);
			return boUtil;

		} catch (Exception e) {
			log.error("",e);
		}
		return null;
	}
	
	@Override
	public BoUtil init(WorkflowVO vo) {
		try {
			
			BoUtil boUtil = BoUtil.getDefaultFalseBo();
			
//			WorkflowDTO dto = new WorkflowDTO();
//			dto.setChangeMode("New");
//			dto.setDocId(dtlsId);
//			dto.setTypeId(typeId);
//			dto.setKeyValue(keyValue);
//			dto.setUserId(userId);
			WorkflowDTO dto = WorkflowDTO.buildFromVo(vo);
			dto.setChangeMode("New");
			log.info("Sending to workflow: "+ dto.toString());

			demoDAO.insert(dto);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
			
			return boUtil;

		} catch (Exception e) {
			log.error("",e);
		}
		return null;

	}

	@Override
	public BoUtil update(WorkflowVO vo) {
		try {

			BoUtil boUtil = BoUtil.getDefaultFalseBo();
			
			WorkflowDTO dto = WorkflowDTO.buildFromVo(vo);
			log.info("Sending to workflow: "+ dto.toString());

			demoDAO.update(dto);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
			
			
			return boUtil;

		} catch (Exception e) {
			log.error("",e);
		}
		return null;
	}
	
}
