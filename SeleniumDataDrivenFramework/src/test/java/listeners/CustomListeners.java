package listeners;

import java.util.Date;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import utilities.ExtentManager;
import utilities.TestUtil;

public class CustomListeners extends BaseTest implements ITestListener {
	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	private static ExtentReports extent = ExtentManager.getInstance();
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		TestUtil.captureScreenshot();
		// test.log(LogStatus.FAIL, result.getName().toUpperCase() + " Failed with
		// exception : " + result.getThrowable());
		// test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		Reporter.log("Click to see screenshot");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
		extent.endTest(test);
		extent.flush();

		// Log into log file
		log.debug(result.getName() + " FAILED");
	}

	public void onTestStart(ITestResult arg) {
		// Log into log file
		log.debug(arg.getName().toUpperCase() + " TEST START");

		// Log into extent reports
		test = extent.startTest(arg.getName().toUpperCase());

	}

	public void onTestSuccess(ITestResult result) {
		// Log into log file
		log.debug(result.getName().toUpperCase() + " PASS");

		// Log into extent reports
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");

		extent.endTest(test);
		extent.flush();
	}

}
