package pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import util.Constants;

public class TopMenu {
	
	/*@FindBy(xpath=Constants.NAVIGATION_LABEL)
	public WebElement navigationLabel;
	
	@FindBy(xpath=Constants.SETTINGS_LINK)
	public WebElement settings;*/
	
	ExtentTest test;
	
	
	WebDriver driver;
	public TopMenu(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public void logout() {
		
	}
	

	
	public void search() {
		
	}

}
