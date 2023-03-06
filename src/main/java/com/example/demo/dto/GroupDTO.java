package com.example.demo.dto;

import java.util.List;

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
	
	public Long userId;
	
	public Long groupId;
	
	public Long groupDtlsId;
	
	public List<Long> functionIds;
	
	public String groupName;
	
	public String status;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer offset;
	
	private String sortKey;
	
	public boolean isTotalCount;
	
	public String pendAppStatus;
	
	public String pendAppDtlId;
	
	public String createdTime;
	
	public String updatedTime;
	
	public static GroupDTO buildFromVo(GroupVO vo) {
		GroupDTO dto = GroupDTO.builder()
				.groupName(vo.getGroupName())
				.groupId(vo.getGroupId())
				.userId(vo.getUserId())
				.build();
		return dto;
	}
}
