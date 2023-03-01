package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.CommonConst;
import com.example.demo.dao.FunctionDAO;
import com.example.demo.dao.WorkflowDAO;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.ListResDTO;
import com.example.demo.dto.WorkflowDTO;
import com.example.demo.service.FunctionService;
import com.example.demo.service.WorkflowService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	FunctionDAO functionDAO;

	@Autowired
	WorkflowService wflService;

	@Autowired
	WorkflowDAO wflDAO;
	
//	Function
	
	@Override
	public BoUtil getFunctionDetails(Long id) {
		BoUtil boUtil = new BoUtil();
		
		try {	
			FunctionDTO Function = functionDAO.getFunctionDetails(id);
			log.info(Function.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(Function);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil getFunctionList(FunctionDTO dto) {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<HashMap<String, Object>> list = functionDAO.getFunctionList(dto);
			
			ListResDTO res = new ListResDTO();
			res.setList(list);
			res.setPageNumber(dto.getPageNumber());
			
			dto.setTotalCount(true);
			Integer totalCount = functionDAO.getFunctionListTotalCount(dto);
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
	public BoUtil insertFunction(FunctionVO funcVo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionDTO dto = FunctionDTO.buildFromVo(funcVo);
			functionDAO.insertFunctionDtls(dto);
			functionDAO.insertFunction(dto);
			log.info(dto.toString());

			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getFunctionDtlsId());
			vo.setDocNo(dto.getFunctionDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_FUNCTION_MAINTENANCE);
			vo.setKeyValue(dto.getFunctionDtlsId().toString());
			vo.setUserId(1l);
			vo.setChangeMode(CommonConst.CHANGE_MODE_NEW);
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
	public BoUtil updateFunction(FunctionVO functionVo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionDTO dto = FunctionDTO.buildFromVo(functionVo);
			functionDAO.insertFunctionDtls(dto);
			functionDAO.updateFunction(dto);
			log.info(dto.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getFunctionDtlsId());
			vo.setDocNo(dto.getFunctionDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_FUNCTION_MAINTENANCE);
			vo.setKeyValue(dto.getFunctionDtlsId().toString());
			vo.setUserId(1l);
			vo.setChangeMode(CommonConst.CHANGE_MODE_EDIT);
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
	public BoUtil deleteFunction(FunctionVO functionVo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {

			Long dtlId = functionDAO.getFunctionDetails(functionVo.getFunctionId()).getFunctionDtlsId();
			
			FunctionDTO dto = FunctionDTO.buildFromVo(functionVo);
			dto.setFunctionDtlsId(dtlId);
			functionDAO.deleteFunction(dto);
			log.info(functionVo.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dtlId);
			vo.setDocNo(dtlId.toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_FUNCTION_MAINTENANCE);
			vo.setKeyValue(dtlId.toString());
			vo.setUserId(1l);
			vo.setChangeMode(CommonConst.CHANGE_MODE_DELETE);
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
			log.info("dtl id: " + vo.getDocId());
			Long mstId = functionDAO.getMstIdFromPendAppDtlId(vo.getDocId());
			log.info("mst id: " + mstId);
			
//			query to get change mode
			String status = functionDAO.getFunctionDetails(mstId).getPendAppStatus();
			
//			update mst table
			dto.setMstId(mstId);
			
			log.info("status: "+ status);
			
			if(vo.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
				if(status.equals(CommonConst.CHANGE_MODE_DELETE)) {
					dto.setRecordStatus(CommonConst.STATUS_INACTIVE);
				} else if(status.equals(CommonConst.CHANGE_MODE_EDIT)) {
					dto.setRecordStatus(CommonConst.STATUS_ACTIVE);
				} else if(status.equals(CommonConst.CHANGE_MODE_NEW)) {
					dto.setRecordStatus(CommonConst.STATUS_ACTIVE);
				}
			} else {
				if(status.equals(CommonConst.CHANGE_MODE_NEW)) {
					dto.setRecordStatus(CommonConst.STATUS_INACTIVE);
				} else {
					dto.setRecordStatus(CommonConst.STATUS_ACTIVE);
				}
			}
			
			functionDAO.changeStatus(dto);
			
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}
	
//	Function Category
	@Override
	public BoUtil getFunctionCategoryList(FunctionCategoryDTO dto) {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<HashMap<String, Object>> list = functionDAO.getFunctionCategoryList(dto);
			
			ListResDTO res = new ListResDTO();
			res.setList(list);
			res.setPageNumber(dto.getPageNumber());
			
			dto.setTotalCount(true);
			Integer totalCount = functionDAO.getFunctionCategoryListTotalCount(dto);
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
	public BoUtil getFuncCatWithFuncList() {
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionCategoryDTO dto = new FunctionCategoryDTO();
			
			List<HashMap<String, Object>> list = functionDAO.getFunctionCategoryList(dto);
			
			for(HashMap<String, Object> obj: list) {
				Long funcCatId = (Long) obj.get("funcCatId");
				log.info("funcCatId: " + funcCatId + ", obj: " + obj);
				List<HashMap<String, Object>> funcList = functionDAO.getFuncFromFuncCatId(funcCatId);
				obj.put("funcList", funcList);
			}
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(list);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil insertCategoryFunction(FunctionCategoryVO catVo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionCategoryDTO dto = FunctionCategoryDTO.buildFromVo(catVo);
			functionDAO.insertFunctionCategoryDtls(dto);
			functionDAO.insertFunctionCategory(dto);
			log.info(dto.toString());

			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getFuncCatDtlsId());
			vo.setDocNo(dto.getFuncCatDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_FUNCTION_CATEGORY_MAINTENANCE);
			vo.setKeyValue(dto.getFuncCatDtlsId().toString());
			vo.setUserId(dto.getUserId());
			vo.setChangeMode(CommonConst.CHANGE_MODE_NEW);
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
	public BoUtil updateFunctionCategory(FunctionCategoryVO catVo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionCategoryDTO dto = FunctionCategoryDTO.buildFromVo(catVo);
			functionDAO.insertFunctionCategoryDtls(dto);
			functionDAO.updateFunctionCategory(dto);
			log.info(dto.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getFuncCatDtlsId());
			vo.setDocNo(dto.getFuncCatDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_FUNCTION_CATEGORY_MAINTENANCE);
			vo.setKeyValue(dto.getFuncCatDtlsId().toString());
			vo.setUserId(1l);
			vo.setChangeMode(CommonConst.CHANGE_MODE_EDIT);
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
	public BoUtil deleteFunctionCategory(FunctionCategoryVO catVo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {

			Long dtlId = functionDAO.getFunctionCategoryDetails(catVo.getFuncCatId()).getFuncCatDtlsId();
			
			FunctionCategoryDTO dto = FunctionCategoryDTO.buildFromVo(catVo);
			dto.setFuncCatDtlsId(dtlId);
			functionDAO.deleteFunctionCategory(dto);
			log.info(catVo.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dtlId);
			vo.setDocNo(dtlId.toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_FUNCTION_CATEGORY_MAINTENANCE);
			vo.setKeyValue(dtlId.toString());
			vo.setUserId(1l);
			vo.setChangeMode(CommonConst.CHANGE_MODE_DELETE);
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
	public BoUtil catChangeStatus(WorkflowVO vo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {
			Long docId = wflDAO.getDocIdFromJobId(vo.getJobId());
			vo.setDocId(docId);
			
//			Update workflow job status
			wflService.update(vo);
			
//			Update detail status
			WorkflowDTO dto = WorkflowDTO.buildFromVo(vo);
			
//			query to get mst id
			log.info("dtl id: " + vo.getDocId());
			Long mstId = functionDAO.getCatMstIdFromPendAppDtlId(vo.getDocId());
			log.info("mst id: " + mstId);
			
//			query to get change mode
			String status = functionDAO.getFunctionCategoryDetails(mstId).getPendAppStatus();
			
//			update mst table
			dto.setMstId(mstId);
			
			log.info("status: "+ status);
			
			if(vo.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
				if(status.equals(CommonConst.CHANGE_MODE_DELETE)) {
					dto.setRecordStatus(CommonConst.STATUS_INACTIVE);
				} else if(status.equals(CommonConst.CHANGE_MODE_EDIT)) {
					dto.setRecordStatus(CommonConst.STATUS_ACTIVE);
				} else if(status.equals(CommonConst.CHANGE_MODE_NEW)) {
					dto.setRecordStatus(CommonConst.STATUS_ACTIVE);
				}
			} else {
				if(status.equals(CommonConst.CHANGE_MODE_NEW)) {
					dto.setRecordStatus(CommonConst.STATUS_INACTIVE);
				} else {
					dto.setRecordStatus(CommonConst.STATUS_ACTIVE);
				}
			}
			
			functionDAO.catChangeStatus(dto);
			
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}
	
}
