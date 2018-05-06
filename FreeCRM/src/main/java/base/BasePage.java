package base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import pages.common.TopMenu;
import util.Constants;

public class BasePage {
	
	public WebDriver driver;
	public TopMenu menu;
	public ExtentTest test;
	
	public BasePage() {}
	
	public BasePage(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test=test;
		menu = new TopMenu(driver,test);
		PageFactory.initElements(driver,menu);
	}
	public String verifyTitle(String expTitle) {
		test.log(LogStatus.INFO,"Verifying the title - "+expTitle);
		return "";
	}
	
	public String verifyText(String locator,String expText) {
		return "";
	}

	public boolean isElementPresent(String locator) {
		test.log(LogStatus.INFO,"Trying to find element -> "+locator);
		int s = driver.findElements(By.xpath(locator)).size();
		if(s==0) {
			test.log(LogStatus.INFO,"Element not found");
			return false;
		}
		else {
			test.log(LogStatus.INFO,"Element found");
			return true;
		}
	}
	
	public TopMenu getMenu() {
		return menu;
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
	
	public void reportFailure(String failureMessage) {
		test.log(LogStatus.FAIL,failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	
	public WebElement getElement(String locatorKey) {
		WebElement e = null;
		try {
			/*if(locatorKey.endsWith("_id"))
				e = driver.findElement(By.id(prop.getProperty(locatorKey)));
			else if(locatorKey.endsWith("_xpath"))*/
				e = driver.findElement(By.xpath(Constants.USER));
			/*else if(locatorKey.endsWith("_name"))
				e = driver.findElement(By.name(prop.getProperty(locatorKey)));*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			reportFailure("Failure is Element Extraction - "+locatorKey);
			Assert.fail("Failure is Element Extraction - "+locatorKey);
		}
		
		return e;
		
	}
	
	public WebElement getElementInFrame(String locatorKey) {
		List<WebElement> e = null;
		WebElement element  = null;
		System.out.println("Frames");
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println(size);
		for(int i=0; i<size; i++) {
		    driver.switchTo().frame(i);
		   	e = driver.findElements(By.xpath(Constants.USER));
			
				
		    if(e.size()>0) {
		    	element = getElement(locatorKey);
		    	System.out.println(element.getAttribute("value"));
		    	System.out.println(i);
		    }
		    driver.switchTo().defaultContent();
		    	
		}
		
		
		return element;
	}
	
}
