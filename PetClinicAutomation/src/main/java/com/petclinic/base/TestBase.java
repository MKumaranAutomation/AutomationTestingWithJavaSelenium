package com.petclinic.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;

public class TestBase {

	public static WebDriver driver;
	static Properties prop;
	public static String baseurl;
	static String driverPath = System.getProperty("user.dir");
	public static String browserName;
	public ExtentReports extent;

	/* Read the config file for the urls */
	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					driverPath +File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"aftermarket"+File.separator+"config"+File.separator+"config.properties");
			prop.load(fis);

		} catch (FileNotFoundException fnfex) {
			fnfex.printStackTrace();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
	
	@Parameters({ "browser", "url" })
	public static void initialization(String browser, String url) throws InterruptedException, TimeoutException {
		browserName = browser;
		
		if(url.equalsIgnoreCase("dev")){
			baseurl = prop.getProperty("devurl");
		}else if (url.equalsIgnoreCase("qa")) {
			baseurl = prop.getProperty("qaurl");
		} else if (url.equalsIgnoreCase("uat")) {
			baseurl = prop.getProperty("uaturl");
		} else if (url.equalsIgnoreCase("prod")) {
			baseurl = prop.getProperty("produrl");
		} else if (url.equalsIgnoreCase("uathotfix")){
			baseurl = prop.getProperty("uathotfix");
		} else if (url.equalsIgnoreCase("demo")) {
			baseurl = prop.getProperty("demourl");
		}

		/* This is to pickup the browser drivers based on the browser type */
		
		if (System.getProperty("os.name").contains("Windows")){
			try {
				if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", driverPath+File.separator+"Drivers"+File.separator+"chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("test-type");
					options.addArguments("no-sandbox");
					options.addArguments("disable-infobars");
					options.addArguments("disable-extensions");
					options.addArguments("--disable-browser-side-navigation");
					options.setPageLoadStrategy(PageLoadStrategy.NONE);
					options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
					driver = new ChromeDriver(options);
					
					/*For disabling the Web push notifications of the browser*/
					ChromeOptions option = new ChromeOptions();
					option.addArguments("--disable-notifications");
				}else if (browser.equalsIgnoreCase("firefox")) {
					DesiredCapabilities capabilities = new DesiredCapabilities();
					System.setProperty("webdriver.gecko.driver", driverPath+"/Drivers/geckodriver.exe");
					capabilities.setCapability("marionette", true);
					driver = new FirefoxDriver();

				}else if (browser.equalsIgnoreCase("edge")) {
					System.setProperty("webdriver.edge.driver", driverPath+"/Drivers/MicrosoftWebDriver.exe");
					driver = new EdgeDriver();
					
				}else if(browser.equalsIgnoreCase("internetexplorer")) {
					File ieDriverFile = new File(driverPath+"/Drivers/IEDriverServer.exe");
					System.setProperty("webdriver.ie.driver", ieDriverFile.getAbsolutePath());
					driver = new InternetExplorerDriver();
					
				}else {
					System.out.println("The Browser is not picked up");
				}
				
			}catch (TimeoutException timeoutexception) {
				driver.navigate().refresh();
				System.out.println(timeoutexception.getMessage());
			}

		}

		else if(System.getProperty("os.name").contains("Mac")) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath+File.separator+"Drivers"+File.separator+"chromedriver 2");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("no-sandbox");
				options.addArguments("disable-infobars");
				options.addArguments("disable-extensions");
				options.addArguments("--disable-browser-side-navigation");
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				driver = new ChromeDriver(options);
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(baseurl);
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void getResults(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				captureScreenshot(result.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static String captureScreenshot(String screenshotName) throws Exception {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + File.separator+"Screenshots"+File.separator+TestBase.browserName+File.separator+screenshotName+ ".png";
		File finalDestination = new File(destination);
		Files.copy(screenshotFile, finalDestination);
		return destination;
	}
	
	
	/*Display Currrent Date & Time*/
	public String displayDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}

}
