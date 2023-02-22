package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.CommonConst;
import com.example.demo.dao.GroupDAO;
import com.example.demo.dao.WorkflowDAO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.ListResDTO;
import com.example.demo.dto.WorkflowDTO;
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
	
	@Autowired
	WorkflowDAO wflDAO;

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
			groupDAO.insertGroupDtls(dto);
			groupDAO.updateGroup(dto);
			log.info(dto.toString());
			
			// init workflow
			WorkflowVO vo = new WorkflowVO();
			vo.setDocId(dto.getGroupDtlsId());
			vo.setDocNo(dto.getGroupDtlsId().toString());
			vo.setTypeId(CommonConst.WFL_TYPE_ID_GROUP_MAINTENANCE);
			vo.setKeyValue(dto.getGroupDtlsId().toString());
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
			Long mstId = groupDAO.getMstIdFromPendAppDtlId(vo.getDocId());
			log.info("mst id: " + mstId);
			
//			query to get change mode
			String status = groupDAO.getGroupDetails(mstId).getPendAppStatus();
			
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
			
			groupDAO.changeStatus(dto);
			
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	
}
