package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class HomePage {
	private ActionDriver actionDriver;

	// define locators using By class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMlogo = By.xpath("//div[@class='oxd-brand-banner']//img");

	//// initializing the Actiondriver object by passing weDriver instance
	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}

	// Method to verify if admin page is visible
	public boolean isAdminTabvisible() {
		
		return actionDriver.isDisplayed(adminTab);
	}

	// Method to verify if orangeHRMlogo is visible
	public boolean verifyOrnageHRMlogo() {
		return actionDriver.isDisplayed(orangeHRMlogo);
	}

	// Method to perform logout
	public void logout() {
		actionDriver.click(userIDButton);
		actionDriver.click(logoutButton);

	}
}
