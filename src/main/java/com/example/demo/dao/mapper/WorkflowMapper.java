package com.example.demo.dao.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.dao.sql.WorkflowProvider;
import com.example.demo.dto.WorkflowDTO;

@Mapper
public interface WorkflowMapper {
	
	@SelectProvider(type = WorkflowProvider.class, method = "getApprovalListing")
	@Results(value = {
			@Result(property = "jobId", column = "job_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "docId", column = "doc_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "docType", column = "code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "changeMode", column = "change_mode", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "keyValue", column = "key_value", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "updatedDate", column = "update_time", javaType = String.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "createdBy", column = "updated_by", javaType = Long.class, jdbcType = JdbcType.BIGINT)})
	List<HashMap<String, Object>> getApprovalListing();
	
	@SelectProvider(type = WorkflowProvider.class, method = "getDocIdFromJobId")
	Long getDocIdFromJobId(Long id);

	@InsertProvider(type = WorkflowProvider.class, method = "insert")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID() AS userId" }, keyProperty = "userId", before = false, resultType = Long.class)
	int insert(WorkflowDTO dto);
	
	@UpdateProvider(type = WorkflowProvider.class, method = "update")
	void update(WorkflowDTO reqDTO);

	@InsertProvider(type = WorkflowProvider.class, method = "insertJobMvmt")
	int insertJobMvmt(WorkflowDTO dto);
}
