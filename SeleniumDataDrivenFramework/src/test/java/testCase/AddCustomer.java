package testCase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class AddCustomer extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "testdata")
	public void addCustomer(String firstName, String lastName, String postCode) {
		click("bankManagerLoginBtn_CSS");
		click("addCustomerBtn_CSS");
		type("firstNameInp_CSS", firstName);
		type("lastNameInp_CSS", lastName);
		type("postCodeInp_CSS", postCode);
		click("submitCustomerBtn_CSS");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id :"));
		alert.accept();
	}

}
