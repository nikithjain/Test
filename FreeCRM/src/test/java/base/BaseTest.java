package base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import util.ExtentManager;
import util.Xls_Reader;
import util.Constants;


public class BaseTest {
	
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	public Xls_Reader xls = new Xls_Reader(Constants.DATA_XLS_PATH);
	public WebDriver driver;
	
	
	public void init(String bType) {
		test.log(LogStatus.INFO,"Opening Browser -"+bType);
		if(bType.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver",Constants.FIREFOX_DRIVER_EXE);
			driver = new FirefoxDriver();
		}
		else if(bType.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",Constants.CHROME_DRIVER_EXE);
			driver = new ChromeDriver();
		}
		else if(bType.equals("IE")){
			System.setProperty("webdriver.ie.driver",Constants.IE_DRIVER_EXE);
			driver =  new InternetExplorerDriver();
		}	
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO,"Opened Browser successfully -"+bType);
	}
	
	public void reportFailure(String failureMessage) {
		test.log(LogStatus.FAIL,failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	public void takeScreenShot() {
		
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=Constants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}

}
