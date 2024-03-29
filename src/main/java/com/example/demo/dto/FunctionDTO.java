package com.example.demo.dto;

import java.util.List;

import com.example.demo.vo.FunctionVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FunctionDTO {
	
	public Long userId;
	
	public Long groupId;

	public Long functionId;

	public Long functionDtlsId;
	
	public List<Long> functionIds;
	
	public String functionName;

	public Long funcCatId;

	public String functionCatName;
	
	public String status;

	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer offset;
	
	private String sortKey;
	
	private boolean isTotalCount;
	
	public String pendAppStatus;
	
	public String pendAppDtlId;
	
	public String createdTime;
	
	public String updatedTime;
	
	public static FunctionDTO buildFromVo(FunctionVO vo) {
		FunctionDTO dto = FunctionDTO.builder()
				.groupId(vo.getGroupId())
				.functionId(vo.getFunctionId())
				.functionIds(vo.getFunctionIds())
				.functionName(vo.getFunctionName())
				.status(vo.getStatus())
				.userId(vo.getUserId())
				.funcCatId(vo.getFuncCatId())
				.build();
		return dto;
	}
}
