package com.example.demo.birt.model;

import com.example.demo.birt.service.ReportRunner;

public class BIRTReport extends Report {

    public BIRTReport(String name, String reportParameters, ReportRunner reportRunner, String exportType) {
        super(name, reportParameters, reportRunner, exportType);
    }

    @Override
    public Report runReport() {
        this.reportContent = reportRunner.runReport(this);
        return this;
    }
}