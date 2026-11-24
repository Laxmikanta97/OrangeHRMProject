package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	private LoginPage loginPage;
	private HomePage homePage;

	@BeforeMethod
	public void setUpPage() {

		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}

	@Test
	public void verifyValidLogintest() {
		loginPage.login("Admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabvisible(), "Admin tab should be visible after succesfull login");
		homePage.logout();
		staticWait(2);
	}
	
	@Test
	public void inValidLoginTest()
	{
		loginPage.login("admin", "admin464");
		String expectedErrorMsg = "Invalid credentials1";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMsg), "Invalid error message");
		
	}
}
