package testCase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLogin extends BaseTest {

	@Test
	public void bankManagerLogin() {
		verifyEquals("abc", "xyz");
		click("bankManagerLoginBtn_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(loc.getProperty("addCustomerBtn_CSS"))));
	}

}
