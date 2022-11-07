package testCase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class CustomerLoginTest extends BaseTest {

	@Test
	public void loginAsCustomer() {
		log.debug("loginAsCustomer Test started");
		driver.findElement(By.cssSelector(loc.getProperty("customerLogin_Button"))).click();
		log.debug("Customer Login button clicked");

		Assert.assertTrue(isElementPresent(By.id(loc.getProperty("user_Select"))));

		log.debug("loginAsCustomer Test finished");
	}

}
