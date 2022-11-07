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
				SELECT("u.demo_function_id, d.function_name, d.is_active");
				FROM("demo_function u");
				LEFT_OUTER_JOIN("demo_function_dtls d ON u.demo_function_dtls_id = d.demo_function_dtls_id");
				WHERE("demo_function_id = #{id} AND u.is_active = 1 AND d.is_active = 1");
			}
		}.toString();
	}
	
	public String getFunctionList() {
		String s = new SQL() {
			{
				SELECT("demo_function_id, function_name, is_active");
				FROM("demo_function_dtls");
				WHERE("is_active = 1");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String insertFunctionDtls(FunctionDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function_dtls");
				VALUES("function_name", "#{functionName}");
				VALUES("demo_function_id", "#{functionId}");
			}
		}.toString();
	}

	public String insertFunction(FunctionDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function");
				VALUES("demo_use_dtls_id", "#{functionDtlsId}");
			}
		}.toString();
	}
}
