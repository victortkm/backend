package com.example.demo.service;

import java.io.OutputStream;

public interface ReportService {
	
	public void downloadUserReport(String userName, String firstName, String lastName, Long groupId, OutputStream fos);
	
	public void downloadGroupReport(String groupName, OutputStream fos);

}
