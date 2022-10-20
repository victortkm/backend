package com.example.demo.vo;

import lombok.Data;

@Data
public class ReportRequestVO {
    private String reportName;
    private String reportParameters;
    
    /* PDF / xlsx */
    private String exportType;
}
