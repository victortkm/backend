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
import org.apache.ibatis.type.JdbcType;

import com.example.demo.dao.sql.GroupProvider;
import com.example.demo.dto.GroupDTO;

@Mapper
public interface GroupMapper {
	
	@Results(value = {
			@Result(property = "groupId", column = "group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupName", column = "group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "isActive", column = "is_active", javaType = Long.class, jdbcType = JdbcType.BIGINT)
			})
	@SelectProvider(type = GroupProvider.class, method = "getGroupDetailsFromGroupId")
	GroupDTO getGroupDetails(Long id);

	@Results(value = {
			@Result(property = "groupId", column = "demo_group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupName", column = "group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "isActive", column = "is_active", javaType = Long.class, jdbcType = JdbcType.BIGINT)
			})
	@SelectProvider(type = GroupProvider.class, method = "getGroupList")
	List<HashMap<String, Object>> getGroupList();

	@InsertProvider(type = GroupProvider.class, method = "insertGroupDtls")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS groupDtlsId" }, keyProperty = "groupDtlsId", before = false, resultType = Long.class)
	int insertGroupDtls(GroupDTO dto);

	@InsertProvider(type = GroupProvider.class, method = "insertGroup")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS groupId" }, keyProperty = "groupId", before = false, resultType = Long.class)
	int insertGroup(GroupDTO dto);
	
}
