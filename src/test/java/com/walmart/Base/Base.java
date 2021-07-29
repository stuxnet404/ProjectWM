package com.walmart.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.TimeUtil;

public class Base {
	public static Properties PO = new Properties();
	public static WebDriver driver;
	public static Logger logger;

	public Base() {
		// will read config.properties file
		try {
			FileInputStream file = new FileInputStream(
					"C:\\Users\\Mr Avi\\eclipse-workspace\\com.walmart\\src\\test\\java\\enviornmnetvariable\\Config.properties");
			PO.load(file);
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	// method to open browser
	public static void initate() {
		logger = Logger.getLogger("walmart");
		PropertyConfigurator.configure("Log4j.properties");
		String Browsername = PO.getProperty("browser");
		if (Browsername.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			logger.info(Browsername + " is open");
		} else if (Browsername.equals("Firefox")) {
			System.setProperty("webdriver.geckodriver.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			logger.info(Browsername + " is open");
		} else if (Browsername.equals("Edge")) {
			System.getProperty("webdriver.msedgedriver.driver", "msedgedriver.exe");
			driver = new EdgeDriver();
			logger.info(Browsername + " is open");
		} else {
			System.out.println("Please enter correct browser name from 'Chrome'or'Firefox'or'Edge'");
		}

		driver.manage().window().maximize();
		logger.info(Browsername + " is maximized");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TimeUtil.page, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TimeUtil.implicit, TimeUnit.SECONDS);
		driver.get(PO.getProperty("homeurl"));
		logger.info("Homepage of AUT opened");

	}

	// Method to login
	public static void login() {
		driver.get(PO.getProperty("loginurl"));
		logger.info("Login url opened");
	}

	// Method scroll down till the end of page up to which elements are loaded
	public static void scrolldown() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.END).perform();
		logger.info("Scrolled to bottom of page");
		Thread.sleep(2000);

	}

	// Method to scroll to very top of page
	public static void scrollup() throws InterruptedException {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
		logger.info("Scrolled to top of page");
		Thread.sleep(2000);

	}

	// Method to change tab
	public static void tabchange() {
		String ow = driver.getWindowHandle();
		for (String wh : driver.getWindowHandles()) {
			if (!ow.contentEquals(wh)) {
				driver.switchTo().window(wh);
				logger.info("Tab changed");
				break;
			}
		}

	}

	// Method to press down key given number of times , it is not scrolling down the
	// whole page
	public static void down() {
		for (int i = 0; i < 5; i++) {
			Actions acti = new Actions(driver);
			acti.sendKeys(Keys.ARROW_DOWN).perform();
			logger.info("Scrolled down " + i + " time");
		}
	}

	// Method to close the browser
	public static void Teardown() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("Thread Sleep Time out of 3 seconds.");
		driver.quit(); // quit will close browser driver as close will only close current window
		logger.info("Close the browser");
	}
}
