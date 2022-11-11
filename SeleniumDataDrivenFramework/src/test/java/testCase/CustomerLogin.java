package testCase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class CustomerLogin extends BaseTest {

	@Test
	public void customerLogin() {
		click("customerLoginBtn_CSS");
		Assert.assertTrue(isElementPresent(By.id(loc.getProperty("userSlct_ID"))));
	}

}
