package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	protected static Properties prop;
	protected static WebDriver driver;

	// load the congif file
	@BeforeSuite
	public void loadConfig() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}

	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("Setting up webDriver for:" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(2);
	}

	// Intialise the webdriver based on browser defined in config.properties
	private void launchBrowser() {
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Erro in browser config" + browser);
		}
	}

	// method is to configure browser settings as implicitwait,maximize the browser
	// and naviagte to url
	private void configureBrowser() {
		// Implicit wait
		int implicitwait = Integer.parseInt(prop.getProperty("implicitwait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwait));

		// Maximize windows
		driver.manage().window().maximize();

		// Navigate to url
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {

			System.out.println("Failed to navigate to url:" + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Unable to quit the driver" + e.getMessage());
			}
		}
	}

	// driver getter method
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// static wait for pause
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

	// prop getter method
	public static Properties getProp() {
		return prop;
	}

	// prop setter method
	public void setProp() {

	}
}
