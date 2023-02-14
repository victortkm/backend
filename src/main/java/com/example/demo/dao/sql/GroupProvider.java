package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;
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
	
	public String getGroupList() {
		String s = new SQL() {
			{
				SELECT("g.demo_group_id, g.demo_group_dtls_id, d.group_name");
				FROM("demo_group g");
				LEFT_OUTER_JOIN("demo_group_dtls d ON g.demo_group_dtls_id = d.demo_group_dtls_id");
				WHERE("g.active_flag = 'y' AND d.active_flag = 'y'");
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
				VALUES("pending_approval_status", "NEW");
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

	public String changeStatus(GroupDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_group");
				SET("demo_group_dtls_id = {groupDtlsId}");
				SET("pending_approval_status = null");
				SET("demo_group_dtpending_approval_dtls_idls_id = null");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE(" demo_group_id = #{groupId} ");
			}
		}.toString();
		return s;
	}
}
