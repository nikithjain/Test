package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import base.BasePage;
//import com.selenium.facebook.pom.PageObjectModel_WithPageFactory.pages.session.LandingPage;
import util.Constants;

public class LoginPage extends BasePage {
	
	
	
	@FindBy(xpath=Constants.LOGIN_USERNAME)
	public WebElement email;
	
	@FindBy(xpath=Constants.PASSWORD)
	public WebElement password;

	public LoginPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
		this.test= test;
	}
	
	public Object doLogin(String usName, String pwd) throws InterruptedException {
		test.log(LogStatus.INFO,"Logging in "+usName+"/"+pwd);
		email.sendKeys(usName);
		password.sendKeys(pwd);
		password.sendKeys(Keys.ENTER);
		//logic - validate
		Thread.sleep(5000);
		
		WebElement usr = getElementInFrame(Constants.USER);
		boolean loginSuccess = true;
		
		if(loginSuccess) {
			test.log(LogStatus.INFO,"Login Success");
			LandingPage landingPage = new LandingPage(driver,test);
			PageFactory.initElements(driver,landingPage);
			return landingPage;
			
		}
		else {
			test.log(LogStatus.INFO,"Login Not Success");
			LoginPage loginPage = new LoginPage(driver,test);
			PageFactory.initElements(driver,loginPage);
			return loginPage;
		}	
	}
	
	
	

}
