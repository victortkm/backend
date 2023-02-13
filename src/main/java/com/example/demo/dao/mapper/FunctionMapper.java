package com.example.demo.dao.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.dao.sql.FunctionProvider;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupFunctionDTO;

@Mapper
public interface FunctionMapper {
	
	@Results(value = {
			@Result(property = "functionId", column = "function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionName", column = "function_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionDetailsFromFunctionId")
	FunctionDTO getFunctionDetails(Long id);

	@Results(value = {
			@Result(property = "functionId", column = "demo_function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionName", column = "function_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionList")
	List<HashMap<String, Object>> getFunctionList(Long groupId);

//	@InsertProvider(type = FunctionProvider.class, method = "insertFunctionDtls")
//	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS functionDtlsId" }, keyProperty = "functionDtlsId", before = false, resultType = Long.class)
//	int insertFunctionDtls(FunctionDTO dto);

	@InsertProvider(type = FunctionProvider.class, method = "insertFunction")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS functionId" }, keyProperty = "functionId", before = false, resultType = Long.class)
	int insertFunction(FunctionDTO dto);

	@Results(value = {
			@Result(property = "funcCatId", column = "demo_function_category_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "categoryName", column = "function_category_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionCategoryList")
	List<HashMap<String, Object>> getFunctionCategoryList();

	@InsertProvider(type = FunctionProvider.class, method = "insertFunctionCategory")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS funcCatId" }, keyProperty = "funcCatId", before = false, resultType = Long.class)
	int insertFunctionCategory(FunctionCategoryDTO dto);

	@UpdateProvider(type = FunctionProvider.class, method = "updateFunctionCategory")
	int updateFunctionCategory(FunctionCategoryDTO dto);
	
}
