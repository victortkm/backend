package com.example.demo.rest;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;
import com.example.demo.util.*;
import com.example.demo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("report")
public class ReportResource extends RestBase {

    @Autowired
    ReportService reportService;
    
	@RequestMapping(value = "/getReport", method = RequestMethod.GET)
	public void getUserDetails(@RequestParam(value="bizRegNo", required=false) String bizRegNo) {

		try {
			
			String fileName = "user report.pdf";
			OutputStream fos = null;
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "UTF-8"));
			
			fos = response.getOutputStream();
			
			reportService.downloadUserReport(bizRegNo, fos);
			
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}
