package com.example.demo.vo;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupFunctionVO {
	
	public Long groupId;
	
	public List<Long> functionIds;
	
	public Long functionId;
	
	public String status;
}
