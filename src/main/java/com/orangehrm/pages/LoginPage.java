package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class LoginPage {
	private ActionDriver actionDriver;

	// define locators using By class
	private By userNameField = By.name("username");
	private By userpswField = By.cssSelector("input[type='password']");
	private By loginbutton = By.xpath("//button[text()=' Login ']");
	private By errorMessage = By.xpath("//p[text()='Invalid credentials']");

	// initializing the Actiondriver object by passing weDriver instance
	public LoginPage(WebDriver driver)
	{
		this.actionDriver = new ActionDriver(driver);
	}

	// method to perform login
	public void login(String username, String password) {
		actionDriver.enterText(userNameField, username);
		actionDriver.enterText(userpswField, password);
		actionDriver.click(loginbutton);
	}

	// method to check if error message is displayed
	public boolean isErrormessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}

	// method to get the text from error message
	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}

	// verify if error is displayed
	public boolean verifyErrorMessage(String expectedError) {
		return actionDriver.compareText(errorMessage, expectedError);
	}
}
