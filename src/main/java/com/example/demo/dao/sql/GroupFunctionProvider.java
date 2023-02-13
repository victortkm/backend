package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupFunctionDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GroupFunctionProvider {
	
//	public String healthCheck() {
//		return new SQL() {
//			{
//				SELECT("1");
//			}
//		}.toString();
//	}
//	
//	public String getFunctionDetailsFromFunctionId(Long id) {
//		return new SQL() {
//			{
//				SELECT("f.demo_function_id, d.function_name, d.active_flag");
//				FROM("demo_function f");
//				LEFT_OUTER_JOIN("demo_group_dtls d ON f.demo_group_dtls_id = d.demo_group_dtls_id");
//				WHERE("demo_function_id = #{id} AND active_flag = 'y'");
//			}
//		}.toString();
//	}
//	
//	public String getFunctionList() {
//		String s = new SQL() {
//			{
//				SELECT("demo_function_id, function_name");
//				FROM("demo_function");
//				WHERE("active_flag = 'y'");
//			}
//		}.toString();
//		log.info(s);
//		return s;
//	}
//
////	public String insertFunctionDtls(FunctionDTO dto) {
////		return new SQL() {
////			{
////				INSERT_INTO("demo_function_dtls");
////				VALUES("function_name", "#{functionName}");
////				VALUES("demo_function_id", "#{functionId}");
////			}
////		}.toString();
////	}
//
//	public String insertFunction(FunctionDTO dto) {
//		return new SQL() {
//			{
//				INSERT_INTO("demo_function");
//				VALUES("function_name", "#{functionName}");
//			}
//		}.toString();
//	}
//	
//	public String insertGroupFunction(GroupFunctionDTO dto) {
//		return new SQL() {
//			{
//				INSERT_INTO("demo_group_function_dtls");
//				VALUES("demo_function_id", "#{functionId}");
//				VALUES("demo_group_id", "#{groupId}");
//				VALUES("status", "#{status}");
//			}
//		}.toString();
//	}
//	
//	public String getFunctionCategoryList() {
//		return new SQL() {
//			{
//				SELECT("demo_function_category_id, function_category_name");
//				FROM("demo_function_category");
//				WHERE("status = 'A'");
//			}
//		}.toString();
//	}
//
//	public String updateFunctionCategory(FunctionCategoryDTO dto) {
//		String s = new SQL() {
//			{
//				UPDATE("demo_function_category");
//				
//				if(dto.getCategoryName() != null) {
//					SET("function_category_name = #{categoryName}");
//				}
//				if(dto.getStatus() != null) {
//					SET("status = #{status}");
//				}
//				SET("updated_by = '1'");
//				SET("updated_time = NOW()");
//				WHERE("demo_function_category_id = #{funcCatId}");
//			}
//		}.toString();
//		log.info(s);
//		return s;
//	}
//	
//	public String insertFunctionCategory(FunctionCategoryDTO dto) {
//		return new SQL() {
//			{
//				INSERT_INTO("demo_function_category");
//				VALUES("function_category_name", "#{categoryName}");
//				VALUES("status", "#{status}");
//			}
//		}.toString();
//	}
//	
//	public String getGroupFunctionListByGroupId(Long id) {
//		String s = new SQL() {
//			{
//				SELECT("demo_group_function_dtls_id");
//				FROM("demo_group_function_dtls");
//				WHERE("demo_group_id = #{id} AND status = 'A'");
//			}
//		}.toString();
//		log.info(s);
//		return s;
//	}

	public String updateGroupFunction(GroupFunctionDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_group_function_dtls");
				
				if(dto.getGroupId() != null) {
					SET("demo_group_id = #{groupId}");
				}
				if(dto.getFunctionId() != null) {
					SET("demo_function_id = #{functionId}");
				}
				if(dto.getStatus() != null) {
					SET("status = #{status}");
				}
				SET("updated_by = '1'");
				SET("updated_time = NOW()");
				WHERE("demo_group_function_dtls_id = #{groupFunctionId}");
			}
		}.toString();
		log.info(s);
		return s;
	}
}
