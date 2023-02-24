package com.example.demo.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	
	public Long userId;
	
	public String userName;
	
	public String firstName;
	
	public String lastName;
	
	public Long groupId;
	
	public Long userIdFrom;
	

}
