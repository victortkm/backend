package com.example.demo.dao.sql;

import org.apache.commons.lang3.StringUtils;
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
		String s = new SQL() {
			{
				SELECT("u.demo_user_id, d.demo_user_dtls_id, d.user_name, d.first_name, d.last_name, d.demo_group_id, gd.group_name, u.pending_approval_status,"
						+ "u.pending_approval_dtls_id, d.demo_group_id, u.active_flag, DATE_FORMAT(u.created_time, '%Y-%m-%e %H:%i:%s') AS created_time,"
						+ "DATE_FORMAT(u.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time");
				FROM("demo_user u");
				LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				LEFT_OUTER_JOIN("demo_group g ON g.demo_group_id = d.demo_group_id");
				LEFT_OUTER_JOIN("demo_group_dtls gd ON gd.demo_group_dtls_id = g.demo_group_dtls_id");
				WHERE("u.demo_user_id = #{id}");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getUserDetailsFromDtlsId(Long id, boolean isPend) {
		String s = new SQL() {
			{
				SELECT("u.demo_user_id, d.demo_user_dtls_id, d.user_name, d.first_name, d.last_name, d.demo_group_id, gd.group_name, u.pending_approval_status,"
						+ "u.pending_approval_dtls_id, d.demo_group_id, u.active_flag, DATE_FORMAT(u.created_time, '%Y-%m-%e %H:%i:%s') AS created_time,"
						+ "DATE_FORMAT(u.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time");
				FROM("demo_user u");
				if(isPend) {
					LEFT_OUTER_JOIN("demo_user_dtls d ON u.pending_approval_dtls_id = d.demo_user_dtls_id");
				} else {
					LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				}
				LEFT_OUTER_JOIN("demo_group g ON g.demo_group_id = d.demo_group_id");
				LEFT_OUTER_JOIN("demo_group_dtls gd ON gd.demo_group_dtls_id = g.demo_group_dtls_id");
				if(isPend) {
					WHERE("u.pending_approval_dtls_id = #{id}");
				} else {
					WHERE("u.demo_user_dtls_id = #{id}");
				}
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getUserList(UserDTO dto) {
		String s = new SQL() {
			{
				if(dto.isTotalCount()) {
					SELECT("COUNT(*)");
				} else {
					SELECT("u.demo_user_id, d.demo_user_dtls_id, d.user_name, d.first_name, d.last_name, d.demo_group_id, gd.group_name,"
							+ "DATE_FORMAT(u.created_time, '%Y-%m-%e %H:%i:%s') AS created_time, DATE_FORMAT(u.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time, u.active_flag");
				}
				FROM("demo_user u");
				LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				LEFT_OUTER_JOIN("demo_group g ON g.demo_group_id = d.demo_group_id");
				LEFT_OUTER_JOIN("demo_group_dtls gd ON gd.demo_group_dtls_id = g.demo_group_dtls_id");
				WHERE("u.active_flag != '" + CommonConst.STATUS_INACTIVE + "'");
				
				if (StringUtils.isNotBlank(dto.getUserName())) {
					AND();
					WHERE("d.user_name LIKE CONCAT('%', #{userName}, '%') ");
				}
				
				if (StringUtils.isNotBlank(dto.getFirstName())) {
					AND();
					WHERE("d.first_name LIKE CONCAT('%', #{firstName}, '%') ");
				}
				
				if (StringUtils.isNotBlank(dto.getLastName())) {
					AND();
					WHERE("d.last_name LIKE CONCAT('%', #{lastName}, '%') ");
				}	
				
				if (dto.getGroupId() != null) {
					AND();
					WHERE("d.demo_group_id = #{groupId}");
				}
				
				/* MUST PUT ON LAST */
				if(!dto.isTotalCount()) {
					if (dto.getPageSize() != null && dto.getPageSize() > 0) {
						if(StringUtils.isNoneBlank(dto.getSortKey())) {
							ORDER_BY( dto.getSortKey().replaceAll(":", " ") + " LIMIT #{offset},#{pageSize}");	
						} else {
							ORDER_BY(" u.updated_time DESC, u.created_time DESC LIMIT #{offset},#{pageSize}");
						}
					}
				}
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
				VALUES("password", "#{password}");
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
				VALUES("created_by", "#{userIdFrom}");
				VALUES("updated_by", "#{userIdFrom}");
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
				SET("updated_by = #{userIdFrom}");
				WHERE("demo_user_id = #{userId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String deleteUser(UserDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_user");
				SET("pending_approval_status = 'DELETE'");
				SET("pending_approval_dtls_id = #{userDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userIdFrom}");
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
	
	public String login(UserDTO dto) {
		return new SQL() {
			{
				SELECT("u.demo_user_id, d.user_name, d.first_name, d.last_name, d.demo_group_id, g.demo_group_dtls_id");
				FROM("demo_user u");
				LEFT_OUTER_JOIN("demo_user_dtls d ON u.demo_user_dtls_id = d.demo_user_dtls_id");
				LEFT_OUTER_JOIN("demo_group g ON d.demo_group_id = g.demo_group_id");
				WHERE("user_name = #{userName} AND password = #{password}");
			}
		}.toString();
	}
}
