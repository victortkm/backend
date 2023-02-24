package com.example.demo.dto;

import com.example.demo.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	public Long userId;
	
	public Long userDtlsId;
	
	public String userName;
	
	public String firstName;
	
	public String lastName;
	
	public Long groupId;
	
	public String groupName;
	
	public String status;
	
	public String pendAppStatus;
	
	public String pendAppDtlId;
	
	public Long userIdFrom;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer offset;
	
	private String sortKey;
	
	private boolean isTotalCount;
	
	public static UserDTO buildFromVo(UserVO vo) {
		UserDTO dto = UserDTO.builder()
				.userId(vo.getUserId())
				.userName(vo.getUserName())
				.firstName(vo.getFirstName())
				.lastName(vo.getLastName())
				.groupId(vo.getGroupId())
				.userIdFrom(vo.getUserIdFrom())
				.build();
		return dto;
	}
}
