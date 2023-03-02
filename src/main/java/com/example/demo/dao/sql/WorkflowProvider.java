package com.example.demo.dao.sql;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import com.example.demo.dto.WorkflowDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkflowProvider {
	
	public String getApprovalListing(WorkflowDTO dto) {
		String s = new SQL() {
			{
				if(dto.isTotalCount()) {
					SELECT("COUNT(*)");
				} else {
					SELECT("j.job_id, j.doc_id, t.code, j.change_mode, j.key_value, j.updated_by, j.updated_time");
				}
				FROM("wfl_job_hdr j");
				LEFT_OUTER_JOIN("wfl_type_hdr t ON j.type_id = t.type_id");
				WHERE("j.active_flag = 'y'");
				
				/* MUST PUT ON LAST */
				if(!dto.isTotalCount()) {
					if (dto.getPageSize() != null && dto.getPageSize() > 0) {
						if(StringUtils.isNoneBlank(dto.getSortKey())) {
							ORDER_BY( dto.getSortKey().replaceAll(":", " ") + " LIMIT #{offset},#{pageSize}");	
						} else {
							ORDER_BY(" j.updated_time DESC, j.created_time DESC LIMIT #{offset},#{pageSize}");
						}
					}
				}
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
