package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	static ExtentReports extrep;
	public static ExtentReports getReportObject()
	{
		   String path = System.getProperty("user.dir") + "\\reports\\index.html";
	        ExtentSparkReporter spre = new ExtentSparkReporter(path);
	        spre.config().setReportName("WEB Automation Result");
	        spre.config().setDocumentTitle("Test Results");

	        extrep = new ExtentReports();
	        extrep.attachReporter(spre);
	        extrep.setSystemInfo("Tester", "Pratik");
	        extrep.createTest(path);
	        return extrep;
	     
	}
}
