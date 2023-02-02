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
	
	public String categoryName;
	
	public String status;
	
	public static FunctionCategoryDTO buildFromVo(FunctionCategoryVO vo) {
		FunctionCategoryDTO dto = FunctionCategoryDTO.builder()
				.funcCatId(vo.getFuncCatId())
				.categoryName(vo.getCategoryName())
				.status(vo.getStatus())
				.build();
		return dto;
	}
}
