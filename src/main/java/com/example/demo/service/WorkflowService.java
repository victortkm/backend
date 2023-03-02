package com.example.demo.service;

import com.example.demo.dto.WorkflowDTO;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.WorkflowVO;

public interface WorkflowService {
	
	public BoUtil getApprovalListing(WorkflowDTO dto);
	
	public BoUtil init(WorkflowVO vo);
	
	public BoUtil update(WorkflowVO vo);
	
	public BoUtil insertJobMvmt(WorkflowVO vo);

}
