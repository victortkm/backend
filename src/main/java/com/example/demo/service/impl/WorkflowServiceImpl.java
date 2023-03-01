package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.CommonConst;
import com.example.demo.dao.WorkflowDAO;
import com.example.demo.dto.ListResDTO;
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

			List<HashMap<String, Object>> list = demoDAO.getApprovalListing();
			ListResDTO res = new ListResDTO();
			res.setList(list);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(res);
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
			dto.setRecordStatus(CommonConst.WORKFLOW_ACTIVE_FLAG_NO);
			log.info("Workflow Update: "+ dto.toString());

			demoDAO.update(dto);
			
			insertJobMvmt(vo);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
			
			
			return boUtil;

		} catch (Exception e) {
			log.error("",e);
		}
		return null;
	}
	
	@Override
	public BoUtil insertJobMvmt(WorkflowVO vo) {
		try {
			
			BoUtil boUtil = BoUtil.getDefaultFalseBo();
			
			WorkflowDTO dto = WorkflowDTO.buildFromVo(vo);
			log.info("Adding to movement table: "+ dto.toString());

			demoDAO.insertJobMvmt(dto);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
			
			return boUtil;

		} catch (Exception e) {
			log.error("",e);
		}
		return null;

	}
	
}
