package com.orangehrm.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DBConnection;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;

public class DBVerificationTest extends BaseClass {
	private LoginPage loginPage;
	private HomePage homePage;

	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}

	@Test(dataProvider = "emplVerification", dataProviderClass = DataProviders.class)
	public void verifyEmployeeNameVerificationFromDB(String emplID, String empName) {
		ExtentManager.logStep("Logging with Admin Credentials");
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		ExtentManager.logStep("click on PIM tab");
		homePage.clickOnPIMTab();
		ExtentManager.logStep("Search for Employee");
		homePage.employeeSearch(empName);
		staticWait(1);
		ExtentManager.logStep("Get the Employee Name from DB");
		String employee_id = emplID;

		// fetch the data into map
		Map<String, String> employeeDetails = DBConnection.getEmployeeDetails(employee_id);
		String emplFirstName = employeeDetails.get("emp_firstname");
		String emplMiddleName = employeeDetails.get("emp_middle_name");
		String emplLastName = employeeDetails.get("emp_lastname");

		String emplFirstAndMiddleName = (emplFirstName + " " + emplMiddleName).trim();
		ExtentManager.logStep("Verify the employee first and middle name");
		Assert.assertTrue(homePage.verifyEmployeeFirstAndMiddleName(emplFirstAndMiddleName),
				"First and Middle name are not Matching");

		// validation for last name
		ExtentManager.logStep("Verify the employee last name");
		Assert.assertTrue(homePage.verifyEmployeeLastName(emplLastName));

		ExtentManager.logStep("DB Validation Completed");

	}
}
