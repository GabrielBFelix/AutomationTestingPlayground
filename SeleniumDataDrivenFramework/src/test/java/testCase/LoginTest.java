package testCase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.ReadXLSData;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass = ReadXLSData.class, dataProvider = "btvdata")
	public void loginAsBankManager(String username, String password) throws InterruptedException {

		log.debug("Login Test started");
		driver.findElement(By.cssSelector(loc.getProperty("customerLogin_Button"))).click();
		log.debug("Customer Login button clicked");
		Thread.sleep(3000);

		log.debug("Login successfully executed");
	}

}
