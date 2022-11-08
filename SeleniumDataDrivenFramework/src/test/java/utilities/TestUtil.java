package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import base.BaseTest;

public class TestUtil extends BaseTest {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();

		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		} catch (IOException e) {
			Reporter.log("Failed to capture screenshot");
			e.printStackTrace();
		}

	}

}
