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
				SELECT("g.demo_group_id, g.demo_group_dtls_id, d.group_name, d.active_flag");
				FROM("demo_group g");
				LEFT_OUTER_JOIN("demo_group_dtls d ON g.demo_group_dtls_id = d.demo_group_dtls_id");
				WHERE("g.demo_group_id = #{id} AND g.active_flag = 'y' AND d.active_flag = 'y'");
			}
		}.toString();
	}
	
	public String getGroupList(GroupDTO dto) {
		String s = new SQL() {
			{
				if(dto.isTotalCount()) {
					SELECT("COUNT(*)");
				} else {
					SELECT("g.demo_group_id, g.demo_group_dtls_id, d.group_name");
				}
				FROM("demo_group g");
				LEFT_OUTER_JOIN("demo_group_dtls d ON g.demo_group_dtls_id = d.demo_group_dtls_id");
				WHERE("g.active_flag = 'y' AND d.active_flag = 'y'");
				
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
			}
		}.toString();
	}

	public String updateGroupDtls(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("wfl_job_hdr");

				if(dto.getRecordStatus() != null) {
					SET("record_status = #{recordStatus}");
				}

				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE(" job_id = #{jobId} ");
			}
		}.toString();
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
