package com.example.demo.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.dao.sql.GroupFunctionProvider;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.dto.WorkflowDTO;

@Mapper
public interface GroupFunctionMapper {

	@Results(value = {
			@Result(property = "grpFuncId", column = "demo_group_function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "pendAppStatus", column = "pending_approval_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppDtlId", column = "pending_approval_dtls_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "grpFuncDtlsId", column = "demo_group_function_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupId", column = "demo_group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "createdTime", column = "created_time", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedTime", column = "updated_time", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = GroupFunctionProvider.class, method = "getGroupFunctionDetailsFromGroupFunctionId")
	GroupFunctionDTO getGroupFunctionDetailsFromGroupFunctionId(Long id);

	@Results(value = {
			@Result(property = "functionId", column = "demo_function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT)
			})
	@SelectProvider(type = GroupFunctionProvider.class, method = "getGroupFunctionListFromGroupId")
	List<Long> getGroupFunctionListFromGroupId(Long groupId);

	@Results(value = {
			@Result(property = "groupFunctionId", column = "demo_group_function_id", javaType = Long.class, jdbcType = JdbcType.BIGINT)
			})
	@SelectProvider(type = GroupFunctionProvider.class, method = "getMstIdFromGroupId")
	List<Long> getMstIdFromGroupId(Long groupId);

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
	
	@UpdateProvider(type = GroupFunctionProvider.class, method = "deleteGrpFunc")
	int deleteGrpFunc(Long mstId);
	
}
