package com.example.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import com.example.demo.dto.GroupDTO;
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
				SELECT("u.demo_group_id, d.group_name, d.is_active");
				FROM("demo_group u");
				LEFT_OUTER_JOIN("demo_group_dtls d ON u.demo_group_dtls_id = d.demo_group_dtls_id");
				WHERE("demo_group_id = #{id} AND u.is_active = 1 AND d.is_active = 1");
			}
		}.toString();
	}
	
	public String getGroupList() {
		String s = new SQL() {
			{
				SELECT("demo_group_id, group_name, is_active");
				FROM("demo_group_dtls");
				WHERE("is_active = 1");
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
				VALUES("demo_group_id", "#{groupId}");
			}
		}.toString();
	}

	public String insertGroup(GroupDTO dto) {
		return new SQL() {
			{
				INSERT_INTO("demo_group");
				VALUES("demo_use_dtls_id", "#{groupDtlsId}");
			}
		}.toString();
	}
}
