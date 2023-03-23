package com.example.demo.rest;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("report")
public class ReportResource extends RestBase {

    @Autowired
    ReportService reportService;
    
	@RequestMapping(value = "/getUserListingReport", method = RequestMethod.GET)
	public void getUserListingReport(
			@RequestParam(value="userName", required=false) String userName,
			@RequestParam(value="firstName", required=false) String firstName,
			@RequestParam(value="lastName", required=false) String lastName,
			@RequestParam(value="groupId", required=false) Long groupId
			) {

		try {
			
			String fileName = "user report.pdf";
			OutputStream fos = null;
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "UTF-8"));
			
			fos = response.getOutputStream();
			
			reportService.downloadUserReport(userName, firstName, lastName, groupId, fos);
			
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@RequestMapping(value = "/getGroupListingReport", method = RequestMethod.GET)
	public void getGroupListingReport(
			@RequestParam(value="groupName", required=false) String groupName
			) {

		try {
			
			String fileName = "user report.pdf";
			OutputStream fos = null;
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "UTF-8"));
			
			fos = response.getOutputStream();
			
			reportService.downloadGroupReport(groupName, fos);
			
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
