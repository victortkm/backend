package com.example.demo.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoUtil {

	private boolean result;

	private String code;

	private String msg;

	private String date;

	private Object data;

	public static BoUtil getDefaultFalseBo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Timestamp now = new Timestamp(System.currentTimeMillis());

		String time = df.format(now);

		return BoUtil.builder().result(false).code("").date(time).msg("failed!").data(null).build();
	}

	public static BoUtil getDefaultTrueBo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Timestamp now = new Timestamp(System.currentTimeMillis());

		String time = df.format(now);

		return BoUtil.builder().result(true).code("0000000").date(time).msg("Success").data(null)
				.build();
	}

	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		String s = "";
		try {
			s = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return s;
	}

}
