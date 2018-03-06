package com.custom.library;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		if(extent == null)
		{
			CreateInstance("C:/a/extent.html");
		}
		return extent;
	}
	
	public static ExtentReports CreateInstance(String fileName){
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(fileName);
		htmlreporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlreporter.config().setChartVisibilityOnOpen(true);
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setDocumentTitle(fileName);
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setReportName(fileName);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		
		return extent;
	}
}
