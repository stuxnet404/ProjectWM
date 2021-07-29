package ScreenshotofFailedTest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.walmart.Base.Base;

public class ListenerClass extends Base implements ITestListener {
	
	 public void onTestStart(ITestResult result) {
		    logger.info("Test "+result.getName()+" Started.");
		  }

	public void onTestFailure(ITestResult result) {

		DateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd_HH_MM_SS_MS");
		Date date = new Date();
		String d = dateformat.format(date) + ".png";
		File shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String pat = System.getProperty("user.dir");
			FileUtils.copyFile(shot, new File(pat + "/Screenshot/" + result.getName() + d));
			logger.info("Screenshot taken");
		} catch (IOException e) {
			logger.fatal(e);
		}
		logger.info("Test "+result.getName()+" Finished.");
		logger.warn("Test " + result.getName() + " Failed.");

	}

	public void onTestSuccess(ITestResult result) {
		logger.info("Test "+result.getName()+" Finished.");
		logger.info("Test " + result.getName() + " Passed.");

	}
	  public void onTestFailedWithTimeout(ITestResult result) {
		    logger.info("Test "+result.getName()+" Finished.");
		    logger.info("Test "+result.getName()+" Failed because of timeout.");
		  }
	  

}
