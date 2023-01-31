package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import com.example.demo.dto.FunctionDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionProvider {
	
	public String healthCheck() {
		return new SQL() {
			{
				SELECT("1");
			}
		}.toString();
	}
	
	public String getFunctionDetailsFromFunctionId(Long id) {
		return new SQL() {
			{
				SELECT("demo_function_id, function_name, is_active");
				FROM("demo_function");
				WHERE("demo_function_id = #{id} AND is_active = 1");
			}
		}.toString();
	}
	
	public String getFunctionList() {
		String s = new SQL() {
			{
				SELECT("demo_function_id, function_name");
				FROM("demo_function");
				WHERE("is_active = 1");
			}
		}.toString();
		log.info(s);
		return s;
	}

//	public String insertFunctionDtls(FunctionDTO dto) {
//		return new SQL() {
//			{
//				INSERT_INTO("demo_function_dtls");
//				VALUES("function_name", "#{functionName}");
//				VALUES("demo_function_id", "#{functionId}");
//			}
//		}.toString();
//	}

	public String insertFunction(FunctionDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function");
				VALUES("function_name", "#{functionName}");
//				VALUES("demo_use_dtls_id", "#{functionDtlsId}");
			}
		}.toString();
	}
}
