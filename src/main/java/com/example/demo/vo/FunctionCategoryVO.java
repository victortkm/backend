package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionCategoryVO {

	public Long funcCatId;
	
	public String categoryName;
	
	public String status;
	
	public Long userId;

}
