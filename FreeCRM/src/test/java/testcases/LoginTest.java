package testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import pages.LaunchPage;
import pages.LoginPage;
import pages.LandingPage;
import base.BaseTest;
import util.Constants;
import util.DataUtil;
import util.ExtentManager;
import util.Xls_Reader;

public class LoginTest extends BaseTest {
	
	String testCaseName = "LoginTest";
	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String,String> data) throws InterruptedException {
		
		test = extent.startTest("LoginTest");
		if(!DataUtil.isTestExecutable(xls,testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")) {
			test.log(LogStatus.SKIP,"Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as Runmode is  N");
			
		}
		test.log(LogStatus.INFO, "Starting Login Test");
		test.log(LogStatus.INFO, "Opening Browser");
		init(data.get("Browser"));
		LaunchPage launchPage = new LaunchPage(driver,test);
		PageFactory.initElements(driver,launchPage);
		
		LoginPage loginPage = launchPage.gotoLoginPage();
		loginPage.takeScreenShot();
		test.log(LogStatus.INFO, "Logging in");
		Object page = loginPage.doLogin(data.get("Username"),data.get("Password"));
		String actualResult ="";
		//if i am logged in
		if(page instanceof LandingPage)
			actualResult = "Success";
		else 
			actualResult = "Unsuccessful";
		
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure("Failure message");
		test.log(LogStatus.INFO, "Login Test Passed");
	
	}
	
	@AfterMethod
	public void quit() {
		if(extent!=null) {
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
	}
	
	
	@DataProvider
	public Object[][] getData(){
		
		return DataUtil.getData(xls, testCaseName);
	}

}
