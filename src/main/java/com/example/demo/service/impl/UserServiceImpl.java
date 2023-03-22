package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.CommonConst;
import com.example.demo.dao.GroupFunctionDAO;
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
	
	@Autowired
	GroupFunctionDAO groupFunctionDAO;
	
	@Override
	public BoUtil getUserDetails(Long id, boolean isPend) {
		BoUtil boUtil = new BoUtil();
		
		try {	
			UserDTO user = userDAO.getUserDetailsFromDtlsId(id, isPend);
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
			vo.setKeyValue(dto.getUserName());
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
			vo.setKeyValue(dto.getUserName());
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
	public BoUtil deleteUser(UserVO userVo) {
		
		BoUtil boUtil = new BoUtil();
		
		try {

			Long dtlId = userDAO.getUserDetails(userVo.getUserId()).getUserDtlsId();
			
			
			UserDTO dto = UserDTO.buildFromVo(userVo);
			dto.setUserDtlsId(dtlId);
			userDAO.deleteUser(dto);
			log.info(userVo.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dtlId);
			vo.setDocNo(dtlId.toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_USER_MAINTENANCE);
			vo.setKeyValue(dto.getUserName());
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
			Long mstId = userDAO.getMstIdFromPendAppDtlId(vo.getDocId());
			log.info("mst id: " + mstId + ", docid: " + vo.getDocId());
			
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
	
	@Override
	public BoUtil login(UserVO vo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			UserDTO dto = UserDTO.buildFromVo(vo);
			UserDTO loginObj = userDAO.login(dto);
			
			
			if(loginObj != null) {
				Long userId = loginObj.getUserId();
				Long groupId = loginObj.getGroupId();
				Long groupDtlsId = loginObj.getGroupDtlsId();
				
				HashMap<String,Object> res = new HashMap<String,Object>();
				res.put("userId", userId);
				res.put("groupId", groupId);
				res.put("groupDtlsId", groupDtlsId);
				
				log.info("User ID: " + userId + ", Group ID: " + groupId + ", Group dtls ID: " + groupDtlsId);
				
				if(groupId != null && groupId != 0l) {

					List<Long> functionIds = groupFunctionDAO.getGroupFunctionByGroupDtlsId(groupDtlsId);
					
					res.put("functionIds", functionIds);
				}
				
				boUtil = BoUtil.getDefaultTrueBo();
				boUtil.setData(res);
			} else {
				boUtil.setMsg("Invalid username or password");
				return boUtil;
			}
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	
}
