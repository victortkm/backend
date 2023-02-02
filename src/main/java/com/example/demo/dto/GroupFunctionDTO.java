package com.example.demo.dto;

import java.util.List;

import com.example.demo.vo.FunctionVO;
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
	
	public Long groupFunctionId;

	public Long groupId;

	public Long functionId;
	
	public List<Long> functionIds;
	
	public String status;
	
	public static GroupFunctionDTO buildFromVo(GroupFunctionVO vo) {
		GroupFunctionDTO dto = GroupFunctionDTO.builder()
				.groupId(vo.getGroupId())
				.functionId(vo.getFunctionId())
				.functionIds(vo.getFunctionIds())
				.status(vo.getStatus())
				.build();
		return dto;
	}
}
