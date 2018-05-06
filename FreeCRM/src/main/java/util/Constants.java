package util;

public class Constants {
	public static final String CHROME_DRIVER_EXE = "C:\\Learning\\JarFiles\\Drivers\\chromedriver.exe";
	public static final String FIREFOX_DRIVER_EXE = "C:\\Learning\\JarFiles\\Drivers\\geckodriver.exe";
	public static final String IE_DRIVER_EXE = "C:\\Learning\\JarFiles\\Drivers\\IEDriverServer.exe";
	
	//Default username and Password
	public static final String DEFAULT_USERNAME = "nikith";
	public static final String DEFAULT_PASSWORD = "Nikith_1";
	
	//Login Page
	public static final String LOGIN_USERNAME ="//input[@name='username']";
	public static final String PASSWORD ="//input[@name='password']";
	
	//Landing Page
	public static final String USER = "//td[@class='headertable']//td[@class='headertext' and @align='left']";
	
	
	
	
	
	
	//URLs
	public static final String HOMEPAGE_URL = "https://www.freecrm.com/index.html";
	
	//Paths
	public static final String REPORTS_PATH = System.getProperty("user.dir")+"\\reports\\";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\data\\Data.xlsx";
	public static final String TESTDATA_SHEET = "TestData";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";


	
	
	
	

	

	
	

	

	

	

	

	

	

	
	
	
	
}
