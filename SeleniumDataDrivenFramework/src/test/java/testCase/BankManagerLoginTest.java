package testCase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest {

	@Test
	public void loginAsBankManager() {
		log.debug("loginAsBankManager Test started");
		driver.findElement(By.cssSelector(loc.getProperty("bankManagerLogin_Button"))).click();
		log.debug("Bank Manager Login button clicked");

		Assert.assertTrue(isElementPresent(By.cssSelector(loc.getProperty("addCustomer_Button"))));

		log.debug("loginAsBankManager Test finished");

		Assert.fail();
	}

}
