package com.custom.library;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

public class BasePage {
	final static  Logger logger = Logger.getLogger(BasePage.class);

	public static WebDriver driver;
	public static GlobalSeleniumLibrary myLib;
	private static JavaPropertiesManager configProperty;
	private static String browser;

	@BeforeMethod
	public void beforeEachTestStart() throws Exception {
		myLib = new GlobalSeleniumLibrary(driver); // move this line tO beforeClass method
		//driver = myLib.startChromeBrowser();
		driver = myLib.startLocalBrowser(browser);
	}

	@AfterMethod
	public void afterEachTestComplete(ITestResult result) {

		try {
			if (ITestResult.FAILURE == result.getStatus()) {
				myLib.captureScreenshot(result.getName(), "target/images/");
			}
			Thread.sleep(5 * 1000); // stops the test for 5 seconds
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close(); // close the browser
		//	driver.quit(); // kill the driver process
		}
	}

	@AfterClass
	public void afterAllTestsComplete() {

		if (driver != null) // if there is any open browser left, close it.
		{
			//driver.close();
			driver.quit();
		}
	}

	@BeforeClass
	public void beforeAllTestStart() {

		configProperty = new JavaPropertiesManager("src/test/resources/config.properties");
		browser = configProperty.readProperty("browserType");
		myLib = new GlobalSeleniumLibrary(driver);

		if (configProperty.readProperty("demoMode").contains("true")) {
			//myLib.isDemoMode = true;
			logger.info("Test is running Demo mode = ON ...");
		} else {
			logger.info("Test is running Demo mode = OFF ...");
		}

	}

}
