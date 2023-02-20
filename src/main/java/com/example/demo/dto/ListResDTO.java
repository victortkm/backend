package com.example.demo.dto;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListResDTO {
	
	public Integer pageNumber;
	
	public Integer totalCount;
	
	public List<HashMap<String, Object>> list;
	
}
