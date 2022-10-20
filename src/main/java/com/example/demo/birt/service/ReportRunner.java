package com.example.demo.birt.service;

import java.io.ByteArrayOutputStream;

import com.example.demo.birt.model.Report;

public interface ReportRunner {

    ByteArrayOutputStream runReport(Report report);
}