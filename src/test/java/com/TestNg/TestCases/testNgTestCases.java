package com.TestNg.TestCases;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgTestCases {
	public WebDriver driver=null;
	//public static int=10;
  
  @Test
  public void validtaeGMO_OnlineLaunchedSuccessfully() {
	  System.out.println("inside Test case 5");
	  String ActualTitle=driver.getTitle();
	  System.out.println("ActualTitle: "+ActualTitle);
	  String ExpectedTitle="Welcome to Green Mountain Outpos";
	  Assert.assertEquals(ActualTitle, ExpectedTitle);
	 // Assert.assertEquals(ActualTitle, "Welcome to Green Mountain Outpost");
	/*  the above is the first test case in 
	  selenium*/
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
	 /* WebDriverManager.edgedriver().setup();
	  driver= new EdgeDriver();
	  driver.get("http://demo.borland.com/gmopost/");
	  driver.manage().window().maximize();
	  Boolean flag=driver.findElement(By.id("name")).isEnabled();*/
	   
		  int x=10;  
		  System.out.println(x++);//10 (11)  
		  System.out.println(++x);//12  
		  System.out.println(x--);//12 (11)  
		  System.out.println(--x);//10  
		  int a=7;  
		  int b=5;  
		  int min=(a<b)?a:b;  
		  System.out.println(min);
		   
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("inside afterSuite");
  }

}
