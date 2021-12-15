package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library {
	public static WebDriver driver = null;
	public static Properties ObjProp = new Properties();
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReport;
	public static ExtentTest extenttest;
	
	//helper methods
		/*ExtentHtmlReporter    : responsible for look and feel of the report ,
		we can specify the report name , document title , theme of the report 

		ExtentReports : used to create entries in your report , create test cases in report , 
		who executed the test case, environment name , browser 

		ExtentTest : update pass fail and skips and logs  the test cases results*/
		//Below is the method to StartExtentReport 
		//Author : Raghu
		public static void StartExtentReport() throws Exception {
			// TODO Auto-generated method stub
			try {
				// specify location of the report
				htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//src//test//resources/ExtentReportV4.html");
				htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
				htmlReporter.config().setReportName("Functional and Regression Testing"); // Name of the report
				htmlReporter.config().setTheme(Theme.STANDARD);

				extentReport = new ExtentReports();
				extentReport.attachReporter(htmlReporter);

				// Passing General information
				extentReport.setSystemInfo("Host name", "localhost");
				extentReport.setSystemInfo("Environemnt", "UAT");
				extentReport.setSystemInfo("user", "Raghu");
				extentReport.setSystemInfo("Browser", ObjProp.getProperty("browser"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//below is the defination
		public static void readProperyFile() throws Exception {
			// TODO Auto-generated method stub
			try {
				//Properties ObjProp = new Properties();
				System.out.println(System.getProperty("user.dir"));
				File objPropertyFile = new File(
						System.getProperty("user.dir") + "//src//test//resources//file1.properties");
				FileInputStream ObjFileInput = new FileInputStream(objPropertyFile);
				ObjProp.load(ObjFileInput);
				ObjProp.getProperty("browser");
				System.out.println(ObjProp.getProperty("browser"));
				ObjProp.getProperty("GMO_Online");
				System.out.println(ObjProp.getProperty("GMO_Online"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void launchBrowser() {
			// TODO Auto-generated method stub
			String bowser=ObjProp.getProperty("browser");
			switch (bowser) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup(); 
				driver = new FirefoxDriver();
				break;
			case "chrome":
				WebDriverManager.chromedriver().setup(); 
				//driver = new ChromeDriver();
				ChromeOptions objChromeOptions = new ChromeOptions();
				objChromeOptions.setAcceptInsecureCerts(true);
				//driver= new ChromeDriver(objChromeOptions);
				Map<String,Object> chromePrefs = new HashMap<String,Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.prompt_for_download", false);
				chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
				objChromeOptions.setExperimentalOption("prefs", chromePrefs);
				driver = new ChromeDriver(objChromeOptions);
				break;
			case "edge":
				WebDriverManager.edgedriver().setup(); 
				driver = new EdgeDriver();
				break;
			case "IE":
				WebDriverManager.iedriver().setup();
				driver=new InternetExplorerDriver();
				break;
			default:
				WebDriverManager.operadriver().setup();
				driver=new OperaDriver();
			}
			driver.get(ObjProp.getProperty("GMO_Online"));
			//driver.get("https://demo.borland.com/gmopost/");
			driver.manage().window().maximize();
			/*implicit wait : It is a global waiting mechanism which is applicable for all web
			elements that are declared under this statatement.
			If webelement is identifed within less time than specified then it is going to access next webelement
			*/
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
			
		}
		
		public static void waitForPageToLoad() {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			// explicit wait -> Applicable for one webEllement
			WebDriverWait wait = new WebDriverWait(driver, 60);//60 seconds 
			wait.until(pageLoadCondition);
		}

		public static String takescreeshot(WebDriver driver) throws Exception {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//String dateName = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
			String dateName = new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
			System.out.println(dateName);
			String destination = System.getProperty("user.dir") + "//screenshots//" + dateName
					+ "captured.png";
			FileUtils.copyFile(source, new File(destination));
			return destination;
		}
		
		public static String takescreeshot(WebDriver driver,String testCaseName) throws Exception {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//String dateName = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
			String dateName = new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
			System.out.println(dateName);
			String destination = System.getProperty("user.dir") + "//screenshots//" + dateName +testCaseName
					+ "captured.png";
			FileUtils.copyFile(source, new File(destination));
			return destination;
		}

		public static WebElement FindElement(String Oreplocator){
			System.out.println(Oreplocator);
			String locator = Oreplocator.split("&")[0];
			String value = Oreplocator.split("&")[1];
			By search = null;
			if (locator.equals("id")){
				search=By.id(value);
			}else if (locator.equals("xpath")){
				search=By.xpath(value);
			}else if (locator.equals("name")){
				search=By.name(value);
			}else if (locator.equals("className")){
				search=By.className(value);
			}else if (locator.equals("tagName")){
				search=By.tagName(value);
			}else if (locator.equals("linkText")){
				search=By.linkText(value);
			}else if (locator.equals("partialLinkText")){
				search=By.partialLinkText(value);
			}else if (locator.equals("cssSelector")){
				search=By.cssSelector(value);
			}
			return driver.findElement(search);
		}
		
		public static List<WebElement> FindElements(String Oreplocator){
			System.out.println(Oreplocator);
			String locator = Oreplocator.split("&")[0];
			String value = Oreplocator.split("&")[1];
			By search = null;
			if (locator.equals("id")){
				search=By.id(value);
			}else if (locator.equals("xpath")){
				search=By.xpath(value);
			}else if (locator.equals("name")){
				search=By.name(value);
			}else if (locator.equals("className")){
				search=By.className(value);
			}else if (locator.equals("tagName")){
				search=By.tagName(value);
			}else if (locator.equals("linkText")){
				search=By.linkText(value);
			}else if (locator.equals("partialLinkText")){
				search=By.partialLinkText(value);
			}else if (locator.equals("cssSelector")){
				search=By.cssSelector(value);
			}
			return driver.findElements(search);
		}
		
		public static void switchToFrameusingWebElementRef(WebElement element){
			driver.switchTo().frame(element);
		}
		
		public static void switchToFrameUsingIdOrName(String IDorName){
			driver.switchTo().frame(IDorName);
		}
		
		public static void DoubleClick(WebElement element){
			Actions obj = new Actions(driver);
			obj.doubleClick(element).build().perform();
		}
		
		public static void RightClick(WebElement element){
			Actions obj = new Actions(driver);
			obj.contextClick(element).build().perform();
		}

		//add href and verify in overloading
		public static int  ValidateLink(String link) throws Exception {
			try {
				URL Obj = new URL(link);
				HttpURLConnection objHttpUrlconnection = (HttpURLConnection)Obj.openConnection();
				objHttpUrlconnection.connect();
				int HttpStatusCode  = objHttpUrlconnection.getResponseCode();
				if(HttpStatusCode>=200 && HttpStatusCode<400){
					System.out.println(link + " is valid link . Status code is : "+HttpStatusCode);
				}else if(HttpStatusCode>=400 && HttpStatusCode<600){
					System.out.println(link + " is Not a valid link .Status code is : "+HttpStatusCode);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
			
		}
		
	public void SelectValueFromDropDown(List<WebElement> AllDropDownItems, String DropDownValue) {
		
			
		}
}
