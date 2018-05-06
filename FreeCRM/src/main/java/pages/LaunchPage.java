package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import base.BasePage;
import util.Constants;

public class LaunchPage extends BasePage{
	
	
	
	public LaunchPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
		this.test=test;
		
	}
	
	public LoginPage gotoLoginPage() {
		test.log(LogStatus.INFO,"Opening the URL - "+Constants.HOMEPAGE_URL);
		driver.get(Constants.HOMEPAGE_URL);
		test.log(LogStatus.PASS,"URL opened - "+Constants.HOMEPAGE_URL);
		LoginPage loginPage = new LoginPage(driver,test);
		PageFactory.initElements(driver,loginPage);
		return loginPage;
	}

}
