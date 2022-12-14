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
	
	public Long groupId;

	public Long functionId;
	
	public List<Long> functionIds;
	
	public String functionName;
	
	public String value;
	
	public Long isActive;
	
	public static FunctionDTO buildFromVo(FunctionVO vo) {
		FunctionDTO dto = FunctionDTO.builder().groupId(vo.getGroupId()).functionId(vo.getFunctionId()).functionIds(vo.getFunctionIds()).functionName(vo.getFunctionName()).value(vo.getValue()).build();
		return dto;
	}
}
