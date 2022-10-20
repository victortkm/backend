package com.example.demo.birt.model;

import java.io.ByteArrayOutputStream;

import com.example.demo.birt.service.ReportRunner;

public abstract class Report {

    protected String name;
    protected String parameters;
    protected ByteArrayOutputStream reportContent;
    protected ReportRunner reportRunner;
    protected String exportType;

    public Report(String name, String parameters, ReportRunner reportRunner, String exportType) {
        this.name = name;
        this.parameters = parameters;
        this.reportRunner = reportRunner;
        this.exportType = exportType;
    }

    /**
     * This is the processing method for a Report. Once the report is ran it
     * populates an internal field with a ByteArrayOutputStream of the
     * report content generated during the run process.
     * @return Returns itself with the report content output stream created.
     */
    public abstract Report runReport();

    public ByteArrayOutputStream getReportContent() {
        return this.reportContent;
    }

    public String getName() {
        return name;
    }

    public String getParameters() {
        return parameters;
    }
    
    public String getExportType() {
    	return exportType;
    }
}
