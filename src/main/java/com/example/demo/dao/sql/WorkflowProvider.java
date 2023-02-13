package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.WorkflowDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkflowProvider {
	
	public String getApprovalListing() {
		String s = new SQL() {
			{
				SELECT("j.job_id, j.doc_id, t.code, j.change_mode, j.key_value, j.update_by, j.update_time");
				FROM("wfl_job_hdr j");
				LEFT_OUTER_JOIN("wfl_type_hdr t ON j.type_id = t.type_id");
				WHERE("j.active_flag = 'y'");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String insert(WorkflowDTO dto) {
		String s = new SQL() {
			{			
				INSERT_INTO("wfl_job_hdr");
				VALUES("`doc_id`", "#{docId}");
				VALUES("`doc_no`", "#{docNo}");
				VALUES("`type_id`", "#{typeId}");
				VALUES("post_user_id", "#{userId}");
				VALUES("change_mode", "#{changeMode}");
				VALUES("key_value", "#{keyValue}");
				VALUES("post_date", "now()");
				VALUES("create_by", "#{userId}");
				VALUES("create_time", "now()");
				VALUES("update_by", "#{userId}");
				VALUES("update_time", "now()");
			}
		}.toString();
		return s;
	}

	public String update(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("wfl_job_hdr");

				if(dto.getRecordStatus() != null) {
					SET("record_status = #{recordStatus}");
				}

				SET("update_time = NOW()");
				SET("update_by = #{userId}");
				WHERE(" job_id = #{jobId} ");
			}
		}.toString();
		return s;
	}
}
