package testCase;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class AddCustomer extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "testdata")
	public void addCustomer(Hashtable<String, String> data) {

		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode is N");
		}

		click("bankManagerLoginBtn_CSS");
		click("addCustomerBtn_CSS");
		type("firstNameInp_CSS", data.get("firstname"));
		type("lastNameInp_CSS", data.get("lastname"));
		type("postCodeInp_CSS", data.get("postcode"));
		click("submitCustomerBtn_CSS");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id :"));
		alert.accept();
	}

}
