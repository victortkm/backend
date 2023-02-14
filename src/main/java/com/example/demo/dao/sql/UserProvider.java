package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.WorkflowDTO;
import com.example.demo.constant.CommonConst;

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
				SELECT("u.demo_user_id, d.demo_user_dtls_id, d.user_name, d.first_name, d.last_name, u.pending_approval_status, u.pending_approval_dtls_id, d.demo_group_id, d.active_flag");
				FROM("demo_user u");
				LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				WHERE("u.demo_user_id = #{id}");
			}
		}.toString();
	}
	
	public String getUserList() {
		String s = new SQL() {
			{
				SELECT("u.demo_user_id, d.user_name, u.active_flag");
				FROM("demo_user u");
				LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				WHERE("u.active_flag != '" + CommonConst.STATUS_INACTIVE + "'");
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
				VALUES("active_flag", "'y'");
			}
		}.toString();
	}

	public String insertUser(UserDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_user");
				VALUES("demo_user_dtls_id", "#{userDtlsId}");
				VALUES("pending_approval_status", "'NEW'");
				VALUES("pending_approval_dtls_id", "#{userDtlsId}");
				VALUES("active_flag", "'p'");
			}
		}.toString();
	}

	public String updateUser(UserDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_user");
				SET("pending_approval_status = 'EDIT'");
				SET("pending_approval_dtls_id = #{userDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE("demo_user_id = #{userId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getMstIdFromPendAppDtlId(Long id) {
		return new SQL() {
			{
				SELECT("demo_user_id");
				FROM("demo_user");
				WHERE("pending_approval_dtls_id = #{id}");
			}
		}.toString();
	}

	public String changeStatus(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_user");
				if(dto.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
					SET("demo_user_dtls_id = #{docId}");
				}
				SET("pending_approval_status = null");
				SET("pending_approval_dtls_id = null");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				SET("active_flag = #{recordStatus}");
				WHERE("demo_user_id = #{mstId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}
}
