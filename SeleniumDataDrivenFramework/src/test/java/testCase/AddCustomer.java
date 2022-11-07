package testCase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.ExcelReader;

public class AddCustomer extends BaseTest {

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "testdata")
	public void addCustomer(String firstName, String lastName, String postCode, String alertText) {
		log.debug("addCustomer Test started");

		driver.findElement(By.cssSelector(loc.getProperty("bankManagerLogin_Button"))).click();
		log.debug("Bank Manager Login button clicked");
		driver.findElement(By.cssSelector(loc.getProperty("addCustomer_Button"))).click();
		driver.findElement(By.cssSelector(loc.getProperty("firstName_input"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(loc.getProperty("lastName_input"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(loc.getProperty("postCode_input"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(loc.getProperty("submitCustomer_button"))).click();

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();

		log.debug("addCustomer Test finished");
	}

}
