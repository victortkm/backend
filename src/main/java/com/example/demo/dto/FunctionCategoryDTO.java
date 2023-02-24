package com.example.demo.dto;

import com.example.demo.vo.FunctionCategoryVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FunctionCategoryDTO {

	public Long funcCatId;

	public Long funcCatDtlsId;
	
	public String categoryName;
	
	public String status;

	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer offset;
	
	private String sortKey;
	
	private boolean isTotalCount;
	
	public String pendAppStatus;
	
	public String pendAppDtlId;
	
	public Long userId;
	
	public static FunctionCategoryDTO buildFromVo(FunctionCategoryVO vo) {
		FunctionCategoryDTO dto = FunctionCategoryDTO.builder()
				.funcCatId(vo.getFuncCatId())
				.categoryName(vo.getCategoryName())
				.status(vo.getStatus())
				.userId(vo.getUserId())
				.build();
		return dto;
	}
}
