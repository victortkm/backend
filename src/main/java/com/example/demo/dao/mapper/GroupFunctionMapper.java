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

import com.example.demo.dao.sql.GroupFunctionProvider;
import com.example.demo.dao.sql.UserProvider;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.WorkflowDTO;

@Mapper
public interface GroupFunctionMapper {

	@Results(value = {
			@Result(property = "userId", column = "demo_user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "groupId", column = "demo_group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupName", column = "group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = GroupFunctionProvider.class, method = "getGroupFunctionDetailsFromGroupFunctionId")
	GroupFunctionDTO getGroupFunctionDetailsFromGroupFunctionId(Long id);

	@Results(value = {
			@Result(property = "functionId", column = "demo_function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "functionName", column = "function_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "categoryName", column = "function_category_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = GroupFunctionProvider.class, method = "getGroupFunctionListFromGroupId")
	List<HashMap<String, Object>> getGroupFunctionListFromGroupId(Long groupId);

	@InsertProvider(type = GroupFunctionProvider.class, method = "insertGroupFunctionDtls")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS grpFuncDtlsId" }, keyProperty = "grpFuncDtlsId", before = false, resultType = Long.class)
	int insertGroupFunctionDtls(GroupFunctionDTO dto);

	@InsertProvider(type = GroupFunctionProvider.class, method = "insertGroupFunctionDtlsRecord")
	int insertGroupFunctionDtlsRecord(GroupFunctionDTO dto);

	@InsertProvider(type = GroupFunctionProvider.class, method = "insertGroupFunction")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS grpFuncId" }, keyProperty = "grpFuncId", before = false, resultType = Long.class)
	int insertGroupFunction(GroupFunctionDTO dto);

	@SelectProvider(type = GroupFunctionProvider.class, method = "getMstIdFromPendAppDtlId")
	Long getMstIdFromPendAppDtlId(Long id);
	
	@UpdateProvider(type = GroupFunctionProvider.class, method = "changeStatus")
	int changeStatus(WorkflowDTO dto);
	
}
