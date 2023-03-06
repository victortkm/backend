package com.example.demo.dto;

import java.util.List;

import com.example.demo.vo.GroupFunctionVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupFunctionDTO {
	
	public Long grpFuncId;
	
	public Long grpFuncDtlsId;
	
	public Long grpFuncDtlsRecId;

	public Long groupId;

	public Long groupDtlsId;

	public Long functionId;
	
	public List<Long> functionIds;
	
	public String status;
	
	public String pendAppStatus;
	
	public String pendAppDtlId;
	
	public Long userId;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer offset;
	
	private String sortKey;
	
	private boolean isTotalCount;
	
	public String createdTime;
	
	public String updatedTime;
	
	public static GroupFunctionDTO buildFromVo(GroupFunctionVO vo) {
		GroupFunctionDTO dto = GroupFunctionDTO.builder()
				.grpFuncId(vo.getGrpFuncId())
				.grpFuncDtlsId(vo.getGrpFuncDtlsId())
				.grpFuncDtlsRecId(vo.getGrpFuncDtlsRecId())
				.groupId(vo.getGroupId())
				.functionId(vo.getFunctionId())
				.functionIds(vo.getFunctionIds())
				.status(vo.getStatus())
				.userId(vo.getUserId())
				.build();
		return dto;
	}
}
