package testCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.ReadXLSData;

public class FirstTestCase extends BaseTest {

	@Test(dataProviderClass = ReadXLSData.class, dataProvider = "btvdata")
	public static void loginTest(String username, String password) {
		driver.findElement(By.className(loc.getProperty("signin_class"))).click();
		driver.findElement(By.id(loc.getProperty("usernameInput_id"))).sendKeys(username); //
		driver.findElement(By.id(loc.getProperty("loginButton_id"))).click();
		WebElement passwordInput = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.id(loc.getProperty("passwordInput_id"))));
		passwordInput.sendKeys(password);
		driver.findElement(By.id(loc.getProperty("loginButton_id"))).click();

		WebElement topTextSize = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.id(loc.getProperty("zohoLogo_class"))));
	}

}
