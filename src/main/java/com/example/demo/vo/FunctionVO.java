package com.example.demo.vo;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionVO {
	
	public Long groupId;

	public Long functionId;
	
	public List<Long> functionIds;
	
	public String functionName;
	
	public String value;

}
