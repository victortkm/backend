package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowVO {
	private Long jobId;
	private Long docId;
	private String docNo;
	private Long typeId;
	private Long userId;
	private String changeMode;
	private String keyValue;
	private String recordStatus;
}
