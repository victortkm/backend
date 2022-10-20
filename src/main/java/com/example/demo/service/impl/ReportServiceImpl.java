package com.example.demo.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DemoApplication;
import com.example.demo.service.ReportService;
import com.example.demo.vo.ReportRequestVO;
import com.example.demo.birt.model.BIRTReport;
import com.example.demo.birt.service.ReportRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	@Qualifier("birt")
	private ReportRunner reportRunner;

	@Autowired
	private DemoApplication.FilePathConfig config;

	@Override
	public void downloadUserReport(String brn, OutputStream fos) {

		byte[] reportBytes;
		ResponseEntity<byte[]> responseEntity;
		String inputDir = config.getInputPath() + File.separator + "demo_user_report.rptdesign";
		log.info("Template path: " + inputDir);
		File folder = new File(inputDir);
		log.info("Template file absolute path: " + folder.getAbsolutePath());
		if (!folder.exists()) {
			log.info("template not found ");
			return;
		}

		String name = org.springframework.util.StringUtils.stripFilenameExtension(folder.getName());

		try {
			ReportRequestVO reportRequest = new ReportRequestVO();
			reportRequest.setReportName(name);
			reportRequest.setReportParameters("?bizRegNo=" + brn);
			reportRequest.setExportType("pdf");

			reportBytes = new BIRTReport(reportRequest.getReportName(), "",
					reportRunner, reportRequest.getExportType()).runReport().getReportContent().toByteArray();

			fos.write(reportBytes);
			log.info("### File size generated: " + reportBytes.length + "bytes");
			fos.close();

		} catch (Exception e) {
			responseEntity = new ResponseEntity<byte[]>(HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
}
