package testCase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.ExcelReader;

public class AddCustomer extends BaseTest {

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "testdata")
	public void addCustomer(String firstName, String lastName, String postCode, String alertText) {

		click("bankManagerLoginBtn_CSS");
		click("addCustomerBtn_CSS");
		type("firstNameInp_CSS", firstName);
		type("lastNameInp_CSS", lastName);
		type("postCodeInp_CSS", postCode);
		click("submitCustomerBtn_CSS");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();

		Assert.fail("Customer not added successfully");
	}

}
