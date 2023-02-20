package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.CommonConst;
import com.example.demo.dao.UserDAO;
import com.example.demo.dao.WorkflowDAO;
import com.example.demo.dto.ListResDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.WorkflowDTO;
import com.example.demo.service.UserService;
import com.example.demo.service.WorkflowService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.UserVO;
import com.example.demo.vo.WorkflowVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	@Autowired
	WorkflowService wflService;
	
	@Autowired
	WorkflowDAO wflDAO;
	
	@Override
	public BoUtil getUserDetails(Long id) {
		BoUtil boUtil = new BoUtil();
		
		try {	
			UserDTO user = userDAO.getUserDetails(id);
			log.info(user.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(user);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil getUserList(UserDTO dto) {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<HashMap<String, Object>> list = userDAO.getUserList(dto);
			
			ListResDTO res = new ListResDTO();
			res.setList(list);
			res.setPageNumber(dto.getPageNumber());
			
			dto.setTotalCount(true);
			Integer totalCount = userDAO.getUserListTotalCount(dto);
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
	public BoUtil insertUser(UserVO userVo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			UserDTO dto = UserDTO.buildFromVo(userVo);
			userDAO.insertUserDtls(dto);
			userDAO.insertUser(dto);
			log.info(dto.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getUserDtlsId());
			vo.setDocNo(dto.getUserDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_USER_MAINTENANCE);
			vo.setKeyValue(dto.getUserDtlsId().toString());
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
	public BoUtil updateUser(UserVO userVo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {
			UserDTO dto = UserDTO.buildFromVo(userVo);
			userDAO.insertUserDtls(dto);
			userDAO.updateUser(dto);
			log.info(dto.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getUserDtlsId());
			vo.setDocNo(dto.getUserDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_USER_MAINTENANCE);
			vo.setKeyValue(dto.getUserDtlsId().toString());
			vo.setUserId(1l);
			vo.setChangeMode("Edit");
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
			Long mstId = userDAO.getMstIdFromPendAppDtlId(vo.getDocId());
			log.info("mst id: " + mstId);
			
//			query to get change mode
			String status = userDAO.getUserDetails(mstId).getPendAppStatus();
			
//			update mst table
			dto.setMstId(mstId);
			
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
			
			userDAO.changeStatus(dto);
			
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	
}
