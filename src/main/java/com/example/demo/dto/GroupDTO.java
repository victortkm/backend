package com.example.demo.dto;

import com.example.demo.vo.GroupVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
	
	public Long groupId;
	
	public Long groupDtlsId;
	
	public String groupName;
	
	public String status;
	
	public static GroupDTO buildFromVo(GroupVO vo) {
		GroupDTO dto = GroupDTO.builder().groupName(vo.getGroupName()).groupId(vo.getGroupId()).build();
		return dto;
	}
}
