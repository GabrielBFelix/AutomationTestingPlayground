package testCase;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class OpenAccount extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "testdata")
	public void openAccount(Hashtable<String, String> data) {

		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode is N");
		}

		click("bankManagerLoginBtn_CSS");
		click("openAccountBtn_CSS");
		select("customerSlct_ID", data.get("customer"));
		select("currencySlct_ID", data.get("currency"));
		click("processBtn_XPATH");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Account created successfully with account Number :"));
		alert.accept();
	}

}
