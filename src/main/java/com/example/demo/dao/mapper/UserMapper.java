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

import com.example.demo.dao.sql.UserProvider;
import com.example.demo.dto.UserDTO;

@Mapper
public interface UserMapper {

//	@SelectProvider(type = DemoProvider.class, method = "healthCheck")
	@Select("SELECT 1")
	int healthCheck();
	
	@Results(value = {
			@Result(property = "userId", column = "user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "isActive", column = "is_active", javaType = Long.class, jdbcType = JdbcType.BIGINT)
			})
	@SelectProvider(type = UserProvider.class, method = "getUserDetailsFromUserId")
	UserDTO getUserDetails(Long id);

	@Results(value = {
			@Result(property = "userId", column = "demo_user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			})
	@SelectProvider(type = UserProvider.class, method = "getUserList")
	List<HashMap<String, Object>> getUserList();

	@InsertProvider(type = UserProvider.class, method = "insertUserDtls")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS userDtlsId" }, keyProperty = "userDtlsId", before = false, resultType = Long.class)
	int insertUserDtls(UserDTO dto);

	@InsertProvider(type = UserProvider.class, method = "insertUser")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS userId" }, keyProperty = "userId", before = false, resultType = Long.class)
	int insertUser(UserDTO dto);
	
}
