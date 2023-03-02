package com.example.demo.dto;

import com.example.demo.vo.WorkflowVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDTO {
	
	private Long jobId;
	private Long docId;
	private String docNo;
	private Long typeId;
	private Long userId;
	private String changeMode;
	private String keyValue;
	private String recordStatus;
	private String actionCode;
	private Long mstId;

	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer offset;
	
	private String sortKey;
	
	private boolean isTotalCount;
	
	public static WorkflowDTO buildFromVo(WorkflowVO vo) {
		WorkflowDTO dto = WorkflowDTO.builder()
				.jobId(vo.getJobId())
				.docId(vo.getDocId())
				.docNo(vo.getDocNo())
				.typeId(vo.getTypeId())
				.userId(vo.getUserId())
				.changeMode(vo.getChangeMode())
				.keyValue(vo.getKeyValue())
				.recordStatus(vo.getRecordStatus())
				.actionCode(vo.getActionCode())
				.userId(vo.getUserId())
				.build();
		return dto;
	}
}
