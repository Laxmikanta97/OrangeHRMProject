package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.LoggerManager;

public class BaseClass {
	protected static Properties prop;
	protected static WebDriver driver;
	private static ActionDriver actionDriver;
	public static final Logger logger = LoggerManager.getLogger(BaseClass.class);

	// load the congif file
	@BeforeSuite
	public void loadConfig() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
		logger.info("config.properties file loaded");
	}

	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("Setting up webDriver for:" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(2);
		logger.info("WebDriver Initialized and Browser Maximized");
		logger.trace("This is a Trace message");
		logger.error("This is a error message");
		logger.debug("This is a debug message");
		logger.fatal("This is a fatal message");
		logger.warn("This is a warm message");

		// intialise the actiondriver only once

		if (actionDriver == null) {
			actionDriver = new ActionDriver(driver);
			logger.info("ActionDriver instance is created.");
		}
	}

	// Intialise the webdriver based on browser defined in config.properties
	private void launchBrowser() {
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			logger.info("ChromeDriver Instance is created.");
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			logger.info("FirefoxDriver Instance is created.");
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			logger.info("EdgeDriver Instance is created.");
			
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
		logger.info("WebDriver instance is closed");
		driver = null;
		actionDriver = null;
	}

	// Getter method for webdriver
	public static WebDriver getDriver() {
		if (driver == null) {
			System.out.println("Webdriver is not intialized");
			throw new IllegalStateException("webDriver is not initialised");
		}
		return driver;
	}

	// Getter method for ActionDriver
	public static ActionDriver getActionDriver() {
		if (actionDriver == null) {
			System.out.println("actionDriver is not intialized");
			throw new IllegalStateException("actionDriver is not initialised");
		}
		return actionDriver;
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
