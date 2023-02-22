package com.example.demo.dao.sql;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.example.demo.constant.CommonConst;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.WorkflowDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionProvider {
	
	public String healthCheck() {
		return new SQL() {
			{
				SELECT("1");
			}
		}.toString();
	}
	
	public String getFunctionDetails(Long id) {
		return new SQL() {
			{
				SELECT("f.demo_function_id, d.function_name, g.pending_approval_status, g.pending_approval_dtls_id, d.active_flag");
				FROM("demo_function f");
				LEFT_OUTER_JOIN("demo_user_dtls d ON f.demo_user_dtls_id = d.demo_user_dtls_id");
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
					SELECT("f.demo_function_id, f.demo_function_dtls_id, d.function_name");
				}
				FROM("demo_function f");
				LEFT_OUTER_JOIN("demo_function_dtls d ON f.demo_function_dtls_id = d.demo_function_dtls_id");
				WHERE("f.active_flag = 'y'");

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
			}
		}.toString();
	}

	public String insertFunction(FunctionDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function_dtls");
				VALUES("function_name", "#{functionName}");
				VALUES("pending_approval_status", "'NEW'");
				VALUES("pending_approval_dtls_id", "#{groupDtlsId}");
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
	
	public String getFunctionCategoryList() {
		return new SQL() {
			{
				SELECT("demo_function_category_id, function_category_name");
				FROM("demo_function_category");
				WHERE("status = 'A'");
			}
		}.toString();
	}

	public String updateFunctionCategory(FunctionCategoryDTO dto) {
		String s = new SQL() {
			{
				UPDATE("demo_function_category");
				
				if(dto.getCategoryName() != null) {
					SET("function_category_name = #{categoryName}");
				}
				if(dto.getStatus() != null) {
					SET("status = #{status}");
				}
				SET("updated_by = '1'");
				SET("updated_time = NOW()");
				WHERE("demo_function_category_id = #{funcCatId}");
			}
		}.toString();
		log.info(s);
		return s;
	}
	
	public String insertFunctionCategory(FunctionCategoryDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_function_category");
				VALUES("function_category_name", "#{categoryName}");
				VALUES("status", "#{status}");
			}
		}.toString();
	}
}
