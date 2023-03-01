package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.CommonConst;
import com.example.demo.dao.FunctionDAO;
import com.example.demo.dao.GroupFunctionDAO;
import com.example.demo.dao.WorkflowDAO;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.dto.WorkflowDTO;
import com.example.demo.service.GroupFunctionService;
import com.example.demo.service.WorkflowService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupFunctionVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupFunctionServiceImpl implements GroupFunctionService {
	
	@Autowired
	GroupFunctionDAO dao;
	
	@Autowired
	WorkflowService wflService;

	@Autowired
	WorkflowDAO wflDAO;
	
	@Override
	public BoUtil getGroupFunctionListFromGroupId(Long groupId) {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<Long> list = dao.getGroupFunctionListFromGroupId(groupId);
			HashMap<String, Object> res = new HashMap<String,Object>();
			res.put("functionIds", list);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(res);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
			
	}

	@Override
	public BoUtil insertGroupFunction(GroupFunctionVO vo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			GroupFunctionDTO dto = GroupFunctionDTO.buildFromVo(vo);
			
//			insert dtls to db
			dao.insertGroupFunctionDtls(dto);
				
//				insert new group functions to db
			for(Long funcId: dto.getFunctionIds()) {
				dto.setFunctionId(funcId);
				dao.insertGroupFunctionDtlsRecord(dto);
			}
			
			dao.insertGroupFunction(dto);
			log.info(dto.toString());
			
			// init workflow
			WorkflowVO workflowVO = new WorkflowVO();
			workflowVO.setDocId(dto.getGrpFuncDtlsId());
			workflowVO.setDocNo(dto.getGrpFuncDtlsId().toString());
			workflowVO.setTypeId(CommonConst.WFL_TYPE_ID_GROUP_FUNCTION_MAINTENANCE);
			workflowVO.setKeyValue(dto.getGrpFuncDtlsId().toString());
			workflowVO.setUserId(1l);
			workflowVO.setChangeMode(CommonConst.CHANGE_MODE_NEW);
			wflService.init(workflowVO);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}
	
	@Override
	public BoUtil changeStatus(WorkflowVO vo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {
			Long docId = wflDAO.getDocIdFromJobId(vo.getJobId());
			vo.setDocId(docId);
			
//			Update workflow job status
			wflService.update(vo);
			
//			Update detail status
			WorkflowDTO dto = WorkflowDTO.buildFromVo(vo);
			
//			query to get mst id
			Long mstId = dao.getMstIdFromPendAppDtlId(vo.getDocId());
			
//			query to get change mode
			GroupFunctionDTO dtls = dao.getGroupFunctionDetailsFromGroupFunctionId(mstId);
			String status = dtls.getPendAppStatus();
			Long groupId = dtls.getGroupId();
			
			log.info("mst id: " + mstId + ", docid: " + vo.getDocId() + ", status: " + status);
			
//			update mst table
			dto.setMstId(mstId);
			
			if(vo.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
				if(status.equals(CommonConst.CHANGE_MODE_NEW)) {
					dto.setRecordStatus(CommonConst.STATUS_ACTIVE);
					
					List<Long>mstList = dao.getMstIdFromGroupId(groupId);

//					set current to inactive
					for(Long mst: mstList) {
						dao.deleteGrpFunc(mst);
					}
				}
			} else {
				if(status.equals(CommonConst.CHANGE_MODE_NEW)) {
					dto.setRecordStatus(CommonConst.STATUS_INACTIVE);
				}
			}
			
			dao.changeStatus(dto);
			
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	
}
