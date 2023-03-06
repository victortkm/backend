package com.example.demo.vo;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupVO {
	
	public Long groupId;
	
	public String groupName;
	
	public List<Long> functionIds;
	
	public Long userId;

}
