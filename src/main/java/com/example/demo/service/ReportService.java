package com.example.demo.service;

import java.io.OutputStream;

import com.example.demo.util.BoUtil;

public interface ReportService {
	
	public void downloadUserReport(String brn, OutputStream fos);

}
