package com.TestNg.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgTestCases2 {
	public WebDriver driver = null;
	Properties ObjProp = new Properties();
	// public static int=10;

	@Test(priority = 0)
	public void validtaeGMO_OnlineLaunchedSuccessfully() {
		System.out.println("inside Test case validtaeGMO_OnlineLaunchedSuccessfully");
		waitForPageToLoad();
		String ActualTitle = driver.getTitle();
		System.out.println("ActualTitle: " + ActualTitle);
		String ExpectedTitle = "Welcome to Green Mountain Outpost";
		Assert.assertEquals(ActualTitle, ExpectedTitle);

	}

	@Test(priority = 1, dependsOnMethods = { "validtaeGMO_OnlineLaunchedSuccessfully" })
	public void validtaeEnterGMO_OnlineSuccessfully() throws InterruptedException {
		System.out.println("inside Test case validtaeEnterGMO_OnlineSuccessfully");
		driver.findElement(By.name("bSubmit")).click();
		waitForPageToLoad();
		String ActualTile = driver.findElement(By.xpath("//h1[contains(text(),'OnLine Catalog')]")).getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(ActualTile, "OnLine Catalog");
		driver.findElement(By.xpath("//input[@name='QTY_BACKPACKS']")).sendKeys("4");
		driver.findElement(By.xpath("//input[@value='Place An Order']")).click();
		waitForPageToLoad();
		String innerhtml = driver.findElement(By.xpath("//p[@align='center']")).getAttribute("innerHTML");
		String outhtml = driver.findElement(By.xpath("//p[@align='center']")).getAttribute("outerHTML");
		System.out.println(innerhtml);
		System.out.println(outhtml);
		if (innerhtml.contains("Proceed With Order")) {
			Assert.assertTrue(true, "validated description inside place order page");
		} else {
			Assert.assertTrue(false, "validated description inside place order page");
		}
		String UnitPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();
		System.out.println("UnitPrice: " + UnitPrice);
		String individualUnitPrice = UnitPrice.substring(1).trim();
		System.out.println(individualUnitPrice);
		float TotalpriceExternalFrameBack = Float.parseFloat(individualUnitPrice) * 4;
		System.out.println("TotalpriceExternalFrameBack: " + TotalpriceExternalFrameBack);
		String Expectedvalue = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText().substring(1).trim();
		float ExpectedPrice = Float.parseFloat(Expectedvalue);
		Assert.assertEquals(TotalpriceExternalFrameBack, ExpectedPrice);
		sa.assertAll();
		driver.navigate().refresh();
		Thread.sleep(8000);
		driver.navigate().back();
		Thread.sleep(8000);
		driver.navigate().forward();
		Thread.sleep(8000);
		System.out.println("current URL:"+driver.getCurrentUrl());
	}
	
	@Test(priority=2)
	public void ValidatingHandlingOfAlerts() throws InterruptedException{
		System.out.println("inside ValidatingHandlingOfAlerts");
		//driver.get(url);
		driver.navigate().to(ObjProp.getProperty("AlertURL"));
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert obj = driver.switchTo().alert();
		String text = obj.getText();
		System.out.println(text);
		Thread.sleep(5000);
		Assert.assertEquals(text, "You clicked a button");
		obj.accept();
		//2nd alert
		driver.findElement(By.id("timerAlertButton")).click();
		Thread.sleep(5000);
		Alert obj1 = driver.switchTo().alert();
		String timerText = obj1.getText();
		Assert.assertEquals(timerText, "This alert appeared after 5 seconds");
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].scrollIntoView(true);",obj1);
		obj1.accept();
		//3rd alert
		driver.findElement(By.id("confirmButton")).click();
		Alert obj3 = driver.switchTo().alert();
		String confirmBoxText = obj3.getText();
		Assert.assertEquals(confirmBoxText, "Do you confirm action?");
		obj3.dismiss();
		
		String confirmBocResultText= driver.findElement(By.id("confirmResult")).getText();
		Assert.assertEquals(confirmBocResultText, "You selected Cancel");
		//4th alert
		driver.findElement(By.id("promtButton")).click();
		Alert obj4 = driver.switchTo().alert();
		obj4.sendKeys("I am Learning Selenium");
		obj4.accept();
		String PromptResult = driver.findElement(By.id("promptResult")).getText();
		Assert.assertEquals(PromptResult, "You entered I am Learning Selenium");
		
		
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		launchBrowser();
	}

	
	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		try {
			readProperyFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//declaration

	}

	/*
	 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
	 * driver.get("http://demo.borland.com/gmopost/");
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); note :
	 * implicit wait is a global waiting mechanism which is applicable for all
	 * web elements. If the script is able to identify the web element with in
	 * given time it will move forward executing other web element
	 */
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

	//helper methods
	//below is the defination
	private void readProperyFile() throws Exception {
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
	
	private void launchBrowser() {
		// TODO Auto-generated method stub
		String bowser=ObjProp.getProperty("browser");
		switch (bowser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup(); 
			driver = new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup(); 
			driver = new ChromeDriver();
			//ChromeOptions objChromeOptions = new ChromeOptions();
			//objChromeOptions.setAcceptInsecureCerts(true);
			//driver= new ChromeDriver(objChromeOptions);
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
	
	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		WebDriverWait wait = new WebDriverWait(driver, 60);//60 seconds 
		wait.until(pageLoadCondition);
	}


	
}
