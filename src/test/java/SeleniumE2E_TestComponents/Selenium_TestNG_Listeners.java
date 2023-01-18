package SeleniumE2E_TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Selenium_E2E_Resources.ExtentReportsSelenium;

public class Selenium_TestNG_Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	String FileName = null;
	ExtentReports extent = ExtentReportsSelenium.CallExtentReports();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		test = extent.createTest(result.getMethod().getMethodName());
		
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().log(Status.PASS, "The Test Case " + result.getMethod().getMethodName() + " is Passed");
		
		try {
			Driver = (WebDriver) result.getTestClass().getRealClass().getField("Driver").get(result.getInstance());
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		try {
			FileName = getScreenshot(result.getMethod().getMethodName(), Driver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.addScreenCaptureFromPath(FileName);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().fail(result.getThrowable());
		try {
			Driver = (WebDriver) result.getTestClass().getRealClass().getField("Driver").get(result.getInstance());
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String FileName = null;

		try {
			FileName = getScreenshot(result.getMethod().getMethodName(), Driver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(FileName);

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
