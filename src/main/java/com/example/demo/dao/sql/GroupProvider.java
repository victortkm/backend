package com.example.demo.dao.sql;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.WorkflowDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GroupProvider {
	
	public String healthCheck() {
		return new SQL() {
			{
				SELECT("1");
			}
		}.toString();
	}
	
	public String getGroupDetailsFromGroupId(Long id) {
		return new SQL() {
			{
				SELECT("g.demo_group_id, g.demo_group_dtls_id, d.group_name, g.pending_approval_status, g.pending_approval_dtls_id,  g.active_flag, DATE_FORMAT(g.created_time, '%Y-%m-%e %H:%i:%s') AS created_time, DATE_FORMAT(g.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time");
				FROM("demo_group g");
				LEFT_OUTER_JOIN("demo_group_dtls d ON g.demo_group_dtls_id = d.demo_group_dtls_id");
				WHERE("g.demo_group_id = #{id}");
			}
		}.toString();
	}
	
	public String getGroupDetailsFromDtlsId(Long id, boolean isPend) {
		return new SQL() {
			{
				SELECT("g.demo_group_id, g.demo_group_dtls_id, d.group_name, g.pending_approval_status, g.pending_approval_dtls_id,  g.active_flag, DATE_FORMAT(g.created_time, '%Y-%m-%e %H:%i:%s') AS created_time, DATE_FORMAT(g.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time");
				FROM("demo_group g");
				if(isPend) {
					LEFT_OUTER_JOIN("demo_group_dtls d ON g.pending_approval_dtls_id = d.demo_group_dtls_id");
					WHERE("g.pending_approval_dtls_id = #{id}");
				} else {
					LEFT_OUTER_JOIN("demo_group_dtls d ON g.demo_group_dtls_id = d.demo_group_dtls_id");
					WHERE("g.demo_group_dtls_id = #{id}");
				}
			}
		}.toString();
	}
	
	public String getGroupList(GroupDTO dto) {
		String s = new SQL() {
			{
				if(dto.isTotalCount()) {
					SELECT("COUNT(*)");
				} else {
					SELECT("g.demo_group_id, g.demo_group_dtls_id, d.group_name, g.active_flag, DATE_FORMAT(g.created_time, '%Y-%m-%e %H:%i:%s') AS created_time, DATE_FORMAT(g.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time");
				}
				FROM("demo_group g");
				LEFT_OUTER_JOIN("demo_group_dtls d ON g.demo_group_dtls_id = d.demo_group_dtls_id");
				WHERE("g.active_flag != '" + CommonConst.STATUS_INACTIVE + "'");
				
				if (StringUtils.isNotBlank(dto.getGroupName())) {
					AND();
					WHERE("d.group_name LIKE CONCAT('%', #{groupName}, '%') ");
				}
				
				/* MUST PUT ON LAST */
				if(!dto.isTotalCount()) {
					if (dto.getPageSize() != null && dto.getPageSize() > 0) {
						if(StringUtils.isNoneBlank(dto.getSortKey())) {
							ORDER_BY( dto.getSortKey().replaceAll(":", " ") + " LIMIT #{offset},#{pageSize}");	
						} else {
							ORDER_BY(" g.updated_time DESC, g.created_time DESC LIMIT #{offset},#{pageSize}");
						}
					}
				}
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String insertGroupDtls(GroupDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_group_dtls");
				VALUES("group_name", "#{groupName}");
			}
		}.toString();
	}

	public String insertGroup(GroupDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_group");
				VALUES("demo_group_dtls_id", "#{groupDtlsId}");
				VALUES("pending_approval_status", "'NEW'");
				VALUES("pending_approval_dtls_id", "#{groupDtlsId}");
				VALUES("created_by", "#{userId}");
				VALUES("updated_by", "#{userId}");
				VALUES("active_flag", "'p'");
			}
		}.toString();
	}

	public String updateGroup(GroupDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_group");
				SET("pending_approval_status = 'EDIT'");
				SET("pending_approval_dtls_id = #{groupDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE("demo_group_id = #{groupId} ");
			}
		}.toString();
		log.info(s);
		log.info(dto.toString());
		return s;
	}

	public String deleteGroup(GroupDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_group");
				SET("pending_approval_status = 'DELETE'");
				SET("pending_approval_dtls_id = #{groupDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE("demo_group_id = #{groupId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getMstIdFromPendAppDtlId(Long id) {
		return new SQL() {
			{
				SELECT("demo_group_id");
				FROM("demo_group");
				WHERE("pending_approval_dtls_id = #{id}");
			}
		}.toString();
	}

	public String changeStatus(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_group");
				if(dto.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
					SET("demo_group_dtls_id = #{docId}");
				}
				SET("pending_approval_status = null");
				SET("pending_approval_dtls_id = null");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				SET("active_flag = #{recordStatus}");
				WHERE("demo_group_id = #{mstId} ");
			}
		}.toString();
		return s;
	}
}
