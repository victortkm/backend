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

import com.example.demo.dao.sql.UserProvider;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.WorkflowDTO;

@Mapper
public interface UserMapper {

//	@SelectProvider(type = DemoProvider.class, method = "healthCheck")
	@Select("SELECT 1")
	int healthCheck();
	
	@Results(value = {
			@Result(property = "userId", column = "demo_user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "userDtlsId", column = "demo_user_dtls_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppStatus", column = "pending_approval_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "pendAppDtlId", column = "pending_approval_dtls_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "groupName", column = "group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "groupId", column = "demo_group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT)
			})
	@SelectProvider(type = UserProvider.class, method = "getUserDetailsFromUserId")
	UserDTO getUserDetails(Long id);

	@Results(value = {
			@Result(property = "userId", column = "demo_user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "groupId", column = "demo_group_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "groupName", column = "group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "active_flag", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = UserProvider.class, method = "getUserList")
	List<HashMap<String, Object>> getUserList(UserDTO dto);
	
	@SelectProvider(type = UserProvider.class, method = "getUserList")
	Integer getUserListTotalCount(UserDTO dto);

	@InsertProvider(type = UserProvider.class, method = "insertUserDtls")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS userDtlsId" }, keyProperty = "userDtlsId", before = false, resultType = Long.class)
	int insertUserDtls(UserDTO dto);

	@InsertProvider(type = UserProvider.class, method = "insertUser")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS userId" }, keyProperty = "userId", before = false, resultType = Long.class)
	int insertUser(UserDTO dto);

	@UpdateProvider(type = UserProvider.class, method = "updateUser")
	int updateUser(UserDTO dto);

	@SelectProvider(type = UserProvider.class, method = "getMstIdFromPendAppDtlId")
	Long getMstIdFromPendAppDtlId(Long id);
	
	@UpdateProvider(type = UserProvider.class, method = "changeStatus")
	int changeStatus(WorkflowDTO dto);

}
