package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitwait = Integer.parseInt(BaseClass.getProp().getProperty("explicitwait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitwait));
	}

	// methos to click element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Unable to click element:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// method to enter text into an input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			// driver.findElement(by).clear();
			// driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("unable to enter the value" + e.getMessage());
		}
	}

	// method to compare two text
	public boolean compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.equals(actualText)) {
				System.out.println("text are Matching:" + actualText + "equls" + actualText);
				return true;
			} else {
				System.out.println("text are not Matching:" + actualText + "not equls" + actualText);
				return false;
			}
		} catch (Exception e) {
			System.out.println("unable to compare texts:" + e.getMessage());
		}
		return false;
	}

	// Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get the text");
			return "";
		}
	}

	// method to check if an element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			System.out.println("element is not displayed; " + e.getMessage());
			return false;
		}

	}

	/*
	 * public boolean isDisplayed(By by) { try { waitForElementToBeVisible(by);
	 * boolean isDisplayed = driver.findElement(by).isDisplayed(); if (isDisplayed)
	 * { System.out.println("element is visible"); return isDisplayed; } else {
	 * return isDisplayed; } } catch (Exception e) {
	 * System.out.println("element is not displayed:" + e.getMessage()); return
	 * false; }
	 * 
	 * }
	 */
	// scroll to elelement
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println("Page did not load within" + e.getMessage());
		}
	}

	// wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("element is not clickable:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// wait for element to be visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("element is not visible:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// wait for page to load
	public void waitforPageLoad(int timeOutinSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutinSec)).until(webDriver -> ((JavascriptExecutor) webDriver)
					.executeScript("return document.readyState").equals("complete"));
			System.out.println("page loaded successfully... ");
		} catch (Exception e) {
			System.out.println("Page did not load within :" + timeOutinSec + "seconds Exception: " + e.getMessage());
		}

	}

}
