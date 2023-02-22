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

import com.example.demo.dao.sql.GroupProvider;
import com.example.demo.dao.sql.UserProvider;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.WorkflowDTO;

@Mapper
public interface GroupMapper {
	
	@Results(value = {
			@Result(property = "groupId", column = "demo_group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupDtlsId", column = "demo_group_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupName", column = "group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppStatus", column = "pending_approval_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppDtlId", column = "pending_approval_dtls_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = GroupProvider.class, method = "getGroupDetailsFromGroupId")
	GroupDTO getGroupDetails(Long id);

	@Results(value = {
			@Result(property = "groupId", column = "demo_group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupDtlsId", column = "demo_group_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupName", column = "group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = GroupProvider.class, method = "getGroupList")
	List<HashMap<String, Object>> getGroupList(GroupDTO dto);
	
	@SelectProvider(type = GroupProvider.class, method = "getGroupList")
	Integer getGroupListTotalCount(GroupDTO dto);

	@InsertProvider(type = GroupProvider.class, method = "insertGroupDtls")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS groupDtlsId" }, keyProperty = "groupDtlsId", before = false, resultType = Long.class)
	int insertGroupDtls(GroupDTO dto);

	@InsertProvider(type = GroupProvider.class, method = "insertGroup")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS groupId" }, keyProperty = "groupId", before = false, resultType = Long.class)
	int insertGroup(GroupDTO dto);

	@InsertProvider(type = GroupProvider.class, method = "updateGroup")
	int updateGroup(GroupDTO dto);

	@SelectProvider(type = GroupProvider.class, method = "getMstIdFromPendAppDtlId")
	Long getMstIdFromPendAppDtlId(Long id);
	
	@UpdateProvider(type = GroupProvider.class, method = "changeStatus")
	int changeStatus(WorkflowDTO dto);
	
}
