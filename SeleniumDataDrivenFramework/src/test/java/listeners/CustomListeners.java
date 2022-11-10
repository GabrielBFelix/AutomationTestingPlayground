package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import utilities.TestUtil;

public class CustomListeners extends BaseTest implements ITestListener {

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		TestUtil.captureScreenshot();
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " Failed with exception : " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		Reporter.log("Click to see screenshot");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
		rep.endTest(test);
		rep.flush();

		// Log into log file
		log.debug(result.getName() + " FAILED");
	}

	public void onTestStart(ITestResult arg) {
		// Log into extent reports
		test = rep.startTest(arg.getName().toUpperCase());

		// Log into log file
		log.debug(arg.getName() + " Test started");
	}

	public void onTestSuccess(ITestResult result) {
		// Log into log file
		log.debug(result.getName().toUpperCase() + " PASS");

		// Log into extent reports
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");

		rep.endTest(test);
		rep.flush();
	}

}
