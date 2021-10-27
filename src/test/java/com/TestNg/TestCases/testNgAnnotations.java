package com.TestNg.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgAnnotations {
  @Test(priority=1)
  public void testcase1() {
	  System.out.println("inside Test case 1");
  }
  
  @Test(priority=1)
  public void case2() {
	  System.out.println("inside Test case 2");
  }
  
  @Test
  public void Bestcase3() {
	  System.out.println("inside Test case 3");
  }
  
  @Test
  public void Testcase4() {
	  System.out.println("inside Test case 4");
  }
  
  @Test
  public void Cestcase5() {
	  System.out.println("inside Test case 5");
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
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("inside afterSuite");
  }

}
