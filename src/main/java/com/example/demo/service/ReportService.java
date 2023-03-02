package com.example.demo.service;

import java.io.OutputStream;

public interface ReportService {
	
	public void downloadUserReport(String brn, OutputStream fos);

}
