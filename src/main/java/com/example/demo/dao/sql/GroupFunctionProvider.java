package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.dto.WorkflowDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GroupFunctionProvider {
	
	public String getGroupFunctionDetailsFromGroupFunctionId(Long id) {
		String s = new SQL() {
			{
				SELECT("gf.demo_group_function_id, gf.pending_approval_status, gf.pending_approval_dtls_id, d.demo_group_function_dtls_id, d.demo_group_id");
				FROM("demo_group_function gf");
				LEFT_OUTER_JOIN("demo_group_function_dtls d ON gf.demo_group_function_dtls_id = d.demo_group_function_dtls_id");
				WHERE("gf.active_flag != '" + CommonConst.STATUS_INACTIVE + "' AND gf.demo_group_function_id = " + id );
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getGroupFunctionListFromGroupId(Long groupId) {
		String s = new SQL() {
			{
				SELECT("r.demo_function_id");
				FROM("demo_group_function gf");
				LEFT_OUTER_JOIN("demo_group_function_dtls d ON gf.demo_group_function_dtls_id = d.demo_group_function_dtls_id");
				LEFT_OUTER_JOIN("demo_group_function_dtls_records r ON r.demo_group_function_dtls_id = d.demo_group_function_dtls_id");
				LEFT_OUTER_JOIN("demo_function f ON f.demo_function_id = r.demo_function_id");
				LEFT_OUTER_JOIN("demo_function_dtls fd ON fd.demo_function_dtls_id = f.demo_function_dtls_id");
				LEFT_OUTER_JOIN("demo_function_category fc ON fc.demo_function_category_id = fd.demo_function_category_id");
				LEFT_OUTER_JOIN("demo_function_category_dtls fcd ON fcd.demo_function_category_dtls_id = fc.demo_function_category_dtls_id");
				WHERE("gf.active_flag != '" + CommonConst.STATUS_INACTIVE + "' AND d.demo_group_id = " + groupId +
						" AND d.active_flag != '" + CommonConst.STATUS_INACTIVE + "'");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getMstIdFromGroupId(Long id) {
		String s = new SQL() {
			{
				SELECT("gf.demo_group_function_id");
				FROM("demo_group_function gf");
				LEFT_OUTER_JOIN("demo_group_function_dtls d ON gf.demo_group_function_dtls_id = d.demo_group_function_dtls_id");
				WHERE("gf.active_flag != '" + CommonConst.STATUS_INACTIVE + "' AND d.demo_group_id = " + id );
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String insertGroupFunctionDtls(GroupFunctionDTO dto) {
		String s = new SQL() {
			{
				INSERT_INTO("demo_group_function_dtls");
				VALUES("demo_group_id", "#{groupId}");
				VALUES("created_by", "#{userId}");
				VALUES("updated_by", "#{userId}");
				VALUES("active_flag", "'y'");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String insertGroupFunctionDtlsRecord(GroupFunctionDTO dto) {
		String s = new SQL() {
			{
				INSERT_INTO("demo_group_function_dtls_records");
				VALUES("demo_group_function_dtls_id", "#{grpFuncDtlsId}");
				VALUES("demo_function_id", "#{functionId}");
				VALUES("created_by", "#{userId}");
				VALUES("updated_by", "#{userId}");
				VALUES("active_flag", "'y'");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String insertGroupFunction(GroupFunctionDTO dto) {
		String s = new SQL() {
			{
				INSERT_INTO("demo_group_function");
				VALUES("demo_group_function_dtls_id", "#{grpFuncDtlsId}");
				VALUES("pending_approval_status", "'NEW'");
				VALUES("pending_approval_dtls_id", "#{grpFuncDtlsId}");
				VALUES("created_by", "#{userId}");
				VALUES("updated_by", "#{userId}");
				VALUES("active_flag", "'p'");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String updateGroupFunction(GroupFunctionDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_group_function_dtls");
				
				if(dto.getGroupId() != null) {
					SET("demo_group_id = #{groupId}");
				}
				if(dto.getFunctionId() != null) {
					SET("demo_function_id = #{functionId}");
				}
				if(dto.getStatus() != null) {
					SET("active_flag = #{status}");
				}
				SET("updated_by = '1'");
				SET("updated_time = NOW()");
				WHERE("demo_group_function_dtls_id = #{groupFunctionId}");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getMstIdFromPendAppDtlId(Long id) {
		return new SQL() {
			{
				SELECT("demo_group_function_id");
				FROM("demo_group_function");
				WHERE("pending_approval_dtls_id = #{id}");
			}
		}.toString();
	}

	public String changeStatus(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_group_function");
				if(dto.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
					SET("demo_group_function_dtls_id = #{docId}");
				}
				SET("pending_approval_status = null");
				SET("pending_approval_dtls_id = null");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				SET("active_flag = #{recordStatus}");
				WHERE("demo_group_function_id = #{mstId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String deleteGrpFunc(Long mstId) {
		String s = new SQL() {
			{
				UPDATE("demo_group_function");
				SET("updated_time = NOW()");
				SET("updated_by = 1");
				SET("active_flag = 'n'");
				WHERE("demo_group_function_id = #{mstId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}
}
