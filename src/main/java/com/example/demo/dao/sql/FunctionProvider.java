package com.example.demo.dao.sql;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.WorkflowDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionProvider {
	
	public String getFunctionDetails(Long id) {
		return new SQL() {
			{
				SELECT("f.demo_function_id, f.demo_function_dtls_id, d.function_name, f.pending_approval_status, f.pending_approval_dtls_id, d.active_flag,"
						+ "DATE_FORMAT(f.created_time, '%Y-%m-%e %H:%i:%s') AS created_time, DATE_FORMAT(f.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time, f.active_flag");
				FROM("demo_function f");
				LEFT_OUTER_JOIN("demo_function_dtls d ON f.demo_function_dtls_id = d.demo_function_dtls_id");
				WHERE("f.demo_function_id = #{id}");
			}
		}.toString();
	}
	
	public String getFunctionList(FunctionDTO dto) {
		String s = new SQL() {
			{
				if(dto.isTotalCount()) {
					SELECT("COUNT(*)");
				} else {
					SELECT("f.demo_function_id, f.demo_function_dtls_id, d.function_name, DATE_FORMAT(f.created_time, '%Y-%m-%e %H:%i:%s') AS created_time,"
							+ "DATE_FORMAT(f.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time, f.active_flag");
				}
				FROM("demo_function f");
				LEFT_OUTER_JOIN("demo_function_dtls d ON f.demo_function_dtls_id = d.demo_function_dtls_id");
				WHERE("f.active_flag != '" + CommonConst.STATUS_INACTIVE + "'");

				/* MUST PUT ON LAST */
				if(!dto.isTotalCount()) {
					if (dto.getPageSize() != null && dto.getPageSize() > 0) {
						if(StringUtils.isNoneBlank(dto.getSortKey())) {
							ORDER_BY( dto.getSortKey().replaceAll(":", " ") + " LIMIT #{offset},#{pageSize}");	
						} else {
							ORDER_BY(" f.updated_time DESC, f.created_time DESC LIMIT #{offset},#{pageSize}");
						}
					}
				}
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String insertFunctionDtls(FunctionDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function_dtls");
				VALUES("function_name", "#{functionName}");
				VALUES("demo_function_category_id", "#{functionCatId}");
			}
		}.toString();
	}

	public String insertFunction(FunctionDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function");
				VALUES("demo_function_dtls_id", "#{functionDtlsId}");
				VALUES("pending_approval_status", "'NEW'");
				VALUES("pending_approval_dtls_id", "#{functionDtlsId}");
				VALUES("active_flag", "'p'");
			}
		}.toString();
	}

	public String updateFunction(FunctionDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_function");
				SET("pending_approval_status = 'EDIT'");
				SET("pending_approval_dtls_id = #{functionDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE("demo_function_id = #{functionId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String deleteFunction(FunctionDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_function");
				SET("pending_approval_status = 'DELETE'");
				SET("pending_approval_dtls_id = #{functionDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE("demo_function_id = #{functionId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getMstIdFromPendAppDtlId(Long id) {
		String s = new SQL() {
			{
				SELECT("demo_function_id");
				FROM("demo_function");
				WHERE("pending_approval_dtls_id = #{id}");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String changeStatus(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_function");
				if(dto.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
					SET("demo_function_dtls_id = #{docId}");
				}
				SET("pending_approval_status = null");
				SET("pending_approval_dtls_id = null");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				SET("active_flag = #{recordStatus}");
				WHERE("demo_function_id = #{mstId} ");
			}
		}.toString();
		return s;
	}
	
	public String getFunctionCategoryDetails(Long id) {
		String s = new SQL() {
			{
				SELECT("fc.demo_function_category_id, fc.demo_function_category_dtls_id, d.function_category_name, fc.pending_approval_status, fc.pending_approval_dtls_id,"
						+ "d.active_flag, DATE_FORMAT(fc.created_time, '%Y-%m-%e %H:%i:%s') AS created_time, DATE_FORMAT(fc.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time"
						+ ", fc.active_flag");
				FROM("demo_function_category fc");
				LEFT_OUTER_JOIN("demo_function_category_dtls d ON fc.demo_function_category_dtls_id = d.demo_function_category_dtls_id");
				WHERE("fc.demo_function_category_id = #{id}");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getFunctionCategoryList(FunctionCategoryDTO dto) {
		String s = new SQL() {
			{
				if(dto.isTotalCount()) {
					SELECT("COUNT(*)");
				} else {
					SELECT("fc.demo_function_category_id, fc.demo_function_category_dtls_id, d.function_category_name, DATE_FORMAT(fc.created_time, '%Y-%m-%e %H:%i:%s') AS created_time,"
							+ "DATE_FORMAT(fc.updated_time, '%Y-%m-%e %H:%i:%s') AS updated_time, fc.active_flag");
				}
				FROM("demo_function_category fc");
				LEFT_OUTER_JOIN("demo_function_category_dtls d ON fc.demo_function_category_dtls_id = d.demo_function_category_dtls_id");
				WHERE("fc.active_flag != '" + CommonConst.STATUS_INACTIVE + "'");

				/* MUST PUT ON LAST */
				if(!dto.isTotalCount()) {
					if (dto.getPageSize() != null && dto.getPageSize() > 0) {
						if(StringUtils.isNoneBlank(dto.getSortKey())) {
							ORDER_BY( dto.getSortKey().replaceAll(":", " ") + " LIMIT #{offset},#{pageSize}");	
						} else {
							ORDER_BY(" fc.updated_time DESC, fc.created_time DESC LIMIT #{offset},#{pageSize}");
						}
					}
				}
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String insertFunctionCategory(FunctionCategoryDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function_category");
				VALUES("demo_function_category_dtls_id", "#{funcCatDtlsId}");
				VALUES("pending_approval_status", "'NEW'");
				VALUES("pending_approval_dtls_id", "#{funcCatDtlsId}");
				VALUES("active_flag", "'p'");
			}
		}.toString();
	}

	public String insertFunctionCategoryDtls(FunctionCategoryDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function_category_dtls");
				VALUES("function_category_name", "#{categoryName}");
			}
		}.toString();
	}

	public String updateFunctionCategory(FunctionCategoryDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_function_category");
				SET("pending_approval_status = '" + CommonConst.CHANGE_MODE_EDIT +"'");
				SET("pending_approval_dtls_id = #{funcCatDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE("demo_function_category_id = #{funcCatId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String deleteFunctionCategory(FunctionCategoryDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_function_category");
				SET("pending_approval_status = 'DELETE'");
				SET("pending_approval_dtls_id = #{funcCatDtlsId}");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				WHERE("demo_function_category_id = #{funcCatId} ");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String getCatMstIdFromPendAppDtlId(Long id) {
		String s = new SQL() {
			{
				SELECT("demo_function_category_id");
				FROM("demo_function_category");
				WHERE("pending_approval_dtls_id = #{id}");
			}
		}.toString();
		log.info(s);
		return s;
	}

	public String catChangeStatus(WorkflowDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_function_category");
				if(dto.getActionCode().equals(CommonConst.WORKFLOW_APPROVE)) {
					SET("demo_function_category_dtls_id = #{docId}");
				}
				SET("pending_approval_status = null");
				SET("pending_approval_dtls_id = null");
				SET("updated_time = NOW()");
				SET("updated_by = #{userId}");
				SET("active_flag = #{recordStatus}");
				WHERE("demo_function_category_id = #{mstId} ");
			}
		}.toString();
		return s;
	}
}
