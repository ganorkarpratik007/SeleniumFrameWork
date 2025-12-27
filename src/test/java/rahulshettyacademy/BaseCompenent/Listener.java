package rahulshettyacademy.BaseCompenent;

import java.io.IOException;
import java.io.ObjectInputFilter.Status;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.ExtentReport;

public class Listener extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extrep = ExtentReport.getReportObject();
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal(); //ThreadSafe
    @Override
    public void onTestStart(ITestResult result) {
    	test = extrep.createTest(result.getMethod().getMethodName());
    	extenttest.set(test);
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	test.log(com.aventstack.extentreports.Status.PASS, "Test Passed Everbody");
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        System.out.println("Test Failed: " + result.getName());
        extenttest.get().fail(result.getThrowable());
        // Screenshot and Attach to Report
        String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {
    	extrep.flush();
    	
    }
}
