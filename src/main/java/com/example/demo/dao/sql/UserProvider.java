package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import com.example.demo.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserProvider {
	
	public String healthCheck() {
		return new SQL() {
			{
				SELECT("1");
			}
		}.toString();
	}
	
	public String getUserDetailsFromUserId(Long id) {
		return new SQL() {
			{
				SELECT("u.demo_user_id, d.user_name, d.is_active");
				FROM("demo_user u");
				LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				WHERE("demo_user_id = #{id} AND u.is_active = 1 AND d.is_active = 1");
			}
		}.toString();
	}
	
	public String getUserList() {
		String s = new SQL() {
			{
				SELECT("demo_user_id, user_name");
				FROM("demo_user u");
				LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				WHERE("u.is_active = 1 AND d.is_active = 1");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String insertUserDtls(UserDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_user_dtls");
				VALUES("user_name", "#{userName}");
				VALUES("first_name", "#{firstName}");
				VALUES("last_name", "#{lastName}");
				VALUES("demo_group_id", "#{groupId}");
			}
		}.toString();
	}

	public String insertUser(UserDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_user");
				VALUES("demo_user_dtls_id", "#{userDtlsId}");
			}
		}.toString();
	}
}
