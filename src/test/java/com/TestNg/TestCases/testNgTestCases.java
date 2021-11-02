package com.TestNg.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgTestCases {
	public WebDriver driver = null;
	// public static int=10;

	@Test(priority = 0)
	public void validtaeGMO_OnlineLaunchedSuccessfully() {
		System.out.println("inside Test case validtaeGMO_OnlineLaunchedSuccessfully");
		String ActualTitle = driver.getTitle();
		System.out.println("ActualTitle: " + ActualTitle);
		String ExpectedTitle = "Welcome to Green Mountain Outpos";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		
	}

	@Test(priority = 1, dependsOnMethods = { "validtaeGMO_OnlineLaunchedSuccessfully" })
	public void validtaeEnterGMO_OnlineSuccessfully() {
		System.out.println("inside Test case validtaeEnterGMO_OnlineSuccessfully");
		driver.findElement(By.name("bSubmit")).click();
		String ActualTile = driver.findElement(By.xpath("//h1[contains(text(),'OnLine Catalog')]")).getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(ActualTile, "OnLine Catalo");
		driver.findElement(By.xpath("//input[@name='QTY_BACKPACKS']")).sendKeys("4");
		driver.findElement(By.xpath("//input[@value='Place An Order']")).click();
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
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.borland.com/gmopost/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		/*note : implicit wait is a global waiting mechanism which is applicable for all web elements. If the script is able to 
		 identify the web element with in given time it will move forward executing other web element */
		
		// Boolean flag=driver.findElement(By.id("name")).isEnabled();

		/*
		 * int x=10; System.out.println(x++);//10 (11)
		 * System.out.println(++x);//12 System.out.println(x--);//12 (11)
		 * System.out.println(--x);//10 int a=7; int b=5; int min=(a<b)?a:b;
		 * System.out.println(min);
		 */

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
