package testCase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class OpenAccount extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "testdata")
	public void openAccount(String customer, String currency) {
		click("bankManagerLoginBtn_CSS");
		click("openAccountBtn_CSS");
		select("customerSlct_ID", customer);
		select("currencySlct_ID", currency);
		click("processBtn_XPATH");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Account created successfully with account Number :"));
		alert.accept();
	}

}
