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
	
	public Long groupId;
	
	public Long isActive;
	
	public static UserDTO buildFromVo(UserVO vo) {
		UserDTO dto = UserDTO.builder().userName(vo.getUserName()).groupId(vo.getGroupId()).build();
		return dto;
	}
}
