package Selenium_E2E_Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsSelenium {

	public static ExtentReports CallExtentReports() {

		String Path = System.getProperty("user.dir") + "//Test Reports//index.html";
		
		ExtentSparkReporter ESP = new ExtentSparkReporter(Path);
		
		ESP.config().setReportName("Automation Test Results");

		ESP.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();

		extent.attachReporter(ESP);
		
		extent.setSystemInfo("Tester Name", "Saikumar Majjari");
		
		return extent;

	}

}
