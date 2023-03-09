package com.example.demo.dao.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.dao.sql.FunctionProvider;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.WorkflowDTO;

@Mapper
public interface FunctionMapper {
	
//	Function
	
	@Results(value = {
			@Result(property = "functionId", column = "function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionDtlsId", column = "demo_function_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionName", column = "function_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppStatus", column = "pending_approval_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppDtlId", column = "pending_approval_dtls_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionDetails")
	FunctionDTO getFunctionDetails(Long id);

	@Results(value = {
			@Result(property = "functionId", column = "function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionDtlsId", column = "demo_function_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionName", column = "function_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppStatus", column = "pending_approval_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppDtlId", column = "pending_approval_dtls_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionDetailsByDtlsId")
	FunctionDTO getFunctionDetailsByDtlsId(Long id, boolean isPend);

	@Results(value = {
			@Result(property = "functionId", column = "demo_function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionDtlsId", column = "demo_function_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionName", column = "function_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionList")
	List<HashMap<String, Object>> getFunctionList(FunctionDTO dto);
	
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionList")
	Integer getFunctionListTotalCount(FunctionDTO dto);

	@InsertProvider(type = FunctionProvider.class, method = "insertFunctionDtls")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS functionDtlsId" }, keyProperty = "functionDtlsId", before = false, resultType = Long.class)
	int insertFunctionDtls(FunctionDTO dto);

	@InsertProvider(type = FunctionProvider.class, method = "insertFunction")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS functionId" }, keyProperty = "functionId", before = false, resultType = Long.class)
	int insertFunction(FunctionDTO dto);

	@UpdateProvider(type = FunctionProvider.class, method = "updateFunction")
	int updateFunction(FunctionDTO dto);

	@UpdateProvider(type = FunctionProvider.class, method = "deleteFunction")
	int deleteFunction(FunctionDTO dto);

	@SelectProvider(type = FunctionProvider.class, method = "getMstIdFromPendAppDtlId")
	Long getMstIdFromPendAppDtlId(Long id);
	
	@UpdateProvider(type = FunctionProvider.class, method = "changeStatus")
	int changeStatus(WorkflowDTO dto);

//	Function Category
	
	@Results(value = {
			@Result(property = "funcCatId", column = "demo_function_category_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "funcCatDtlsId", column = "demo_function_category_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "categoryName", column = "function_category_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppStatus", column = "pending_approval_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppDtlId", column = "pending_approval_dtls_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionCategoryDetails")
	FunctionCategoryDTO getFunctionCategoryDetails(Long id);
	
	@Results(value = {
			@Result(property = "funcCatId", column = "demo_function_category_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "funcCatDtlsId", column = "demo_function_category_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "categoryName", column = "function_category_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppStatus", column = "pending_approval_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppDtlId", column = "pending_approval_dtls_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFuncCatDtlsByDtlsId")
	FunctionCategoryDTO getFuncCatDtlsByDtlsId(Long id, boolean isPend);
	
	@Results(value = {
			@Result(property = "funcCatId", column = "demo_function_category_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "funcCatDtlsId", column = "demo_function_category_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "categoryName", column = "function_category_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionCategoryList")
	List<HashMap<String, Object>> getFunctionCategoryList(FunctionCategoryDTO dto);
	
	@Results(value = {
			@Result(property = "functionId", column = "demo_function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionDtlsId", column = "demo_function_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionName", column = "function_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			})
	@SelectProvider(type = FunctionProvider.class, method = "getFuncFromFuncCatId")
	List<HashMap<String, Object>> getFuncFromFuncCatId(Long id);
	
	@SelectProvider(type = FunctionProvider.class, method = "getFunctionCategoryList")
	Integer getFunctionCategoryListTotalCount(FunctionCategoryDTO dto);

	@InsertProvider(type = FunctionProvider.class, method = "insertFunctionCategory")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS funcCatId" }, keyProperty = "funcCatId", before = false, resultType = Long.class)
	int insertFunctionCategory(FunctionCategoryDTO dto);

	@InsertProvider(type = FunctionProvider.class, method = "insertFunctionCategoryDtls")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS funcCatDtlsId" }, keyProperty = "funcCatDtlsId", before = false, resultType = Long.class)
	int insertFunctionCategoryDtls(FunctionCategoryDTO dto);

	@UpdateProvider(type = FunctionProvider.class, method = "updateFunctionCategory")
	int updateFunctionCategory(FunctionCategoryDTO dto);

	@UpdateProvider(type = FunctionProvider.class, method = "deleteFunctionCategory")
	int deleteFunctionCategory(FunctionCategoryDTO dto);

	@SelectProvider(type = FunctionProvider.class, method = "getCatMstIdFromPendAppDtlId")
	Long getCatMstIdFromPendAppDtlId(Long id);
	
	@UpdateProvider(type = FunctionProvider.class, method = "catChangeStatus")
	int catChangeStatus(WorkflowDTO dto);
	
}
