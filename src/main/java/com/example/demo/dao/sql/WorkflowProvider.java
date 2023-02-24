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
				SELECT("j.job_id, j.doc_id, t.code, j.change_mode, j.key_value, j.updated_by, j.updated_time");
				FROM("wfl_job_hdr j");
				LEFT_OUTER_JOIN("wfl_type_hdr t ON j.type_id = t.type_id");
				WHERE("j.active_flag = 'y'");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getDocIdFromJobId(Long id) {
		String s = new SQL() {
			{
				SELECT("doc_id");
				FROM("wfl_job_hdr");
				WHERE("job_id = #{id}");
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
				VALUES("created_by", "#{userId}");
				VALUES("created_time", "now()");
				VALUES("updated_by", "#{userId}");
				VALUES("updated_time", "now()");
			}
		}.toString();
		return s;
	}

	public String update(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("wfl_job_hdr");

				if(dto.getRecordStatus() != null) {
					SET("active_flag = #{recordStatus}");
				}

				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE(" job_id = #{jobId} ");
			}
		}.toString();
		return s;
	}
	
	public String insertJobMvmt(WorkflowDTO dto) {
		String s = new SQL() {
			{			
				INSERT_INTO("wfl_job_movement_dtls");
				VALUES("job_id", "#{jobId}");
				VALUES("from_post_user_id", "1");
				VALUES("action_code", "#{actionCode}");
				VALUES("created_by", "#{userId}");
				VALUES("created_time", "now()");
				VALUES("updated_by", "#{userId}");
				VALUES("updated_time", "now()");
			}
		}.toString();
		return s;
	}
}
