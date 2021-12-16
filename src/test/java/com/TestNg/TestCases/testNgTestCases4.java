package com.TestNg.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.utility.ObjRepository;
import com.utility.constants;
import com.utility.library;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
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
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgTestCases4 extends library {

	HashMap<String, String> testData = new HashMap<String, String>();
	// public static int=10;

	@Test(priority = 0)
	public void validtaeGMO_OnlineLaunchedSuccessfully() {
		System.out.println("inside Test case validtaeGMO_OnlineLaunchedSuccessfully");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		library.waitForPageToLoad();
		String ActualTitle = driver.getTitle();
		System.out.println("ActualTitle: " + ActualTitle);
		String ExpectedTitle = "Welcome to Green Mountain Outpost";
		Assert.assertEquals(ActualTitle, ExpectedTitle);

	}

	@Test(priority = 1, dependsOnMethods = { "validtaeGMO_OnlineLaunchedSuccessfully" })
	public void validtaeEnterGMO_OnlineSuccessfully() throws InterruptedException {
		System.out.println("inside Test case validtaeEnterGMO_OnlineSuccessfully");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.name("bSubmit")).click();
		waitForPageToLoad();
		String ActualTile = driver.findElement(By.xpath("//h1[contains(text(),'OnLine Catalog')]")).getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(ActualTile, "OnLine Catalog");
		driver.findElement(By.xpath("//input[@name='QTY_BACKPACKS']")).sendKeys(constants.FrameBackpackQty);
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
		System.out.println("current URL:" + driver.getCurrentUrl());
	}

	@Test(priority = 2)
	public void ValidatingHandlingOfAlerts() throws InterruptedException {
		System.out.println("inside ValidatingHandlingOfAlerts");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		// driver.get(url);
		driver.navigate().to(ObjProp.getProperty("AlertURL"));
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert obj = driver.switchTo().alert();
		String text = obj.getText();
		System.out.println(text);
		Thread.sleep(5000);
		Assert.assertEquals(text, "You clicked a button");
		obj.accept();
		// 2nd alert
		driver.findElement(By.id("timerAlertButton")).click();
		Thread.sleep(5000);
		Alert obj1 = driver.switchTo().alert();
		String timerText = obj1.getText();
		Assert.assertEquals(timerText, "This alert appeared after 5 seconds");
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		// js.executeScript("arguments[0].scrollIntoView(true);",obj1);
		obj1.accept();
		// 3rd alert
		driver.findElement(By.id("confirmButton")).click();
		Alert obj3 = driver.switchTo().alert();
		String confirmBoxText = obj3.getText();
		Assert.assertEquals(confirmBoxText, "Do you confirm action?");
		obj3.dismiss();

		String confirmBocResultText = driver.findElement(By.id("confirmResult")).getText();
		Assert.assertEquals(confirmBocResultText, "You selected Cancel");
		// 4th alert
		driver.findElement(By.id("promtButton")).click();
		Alert obj4 = driver.switchTo().alert();
		obj4.sendKeys("I am Learning Selenium");
		obj4.accept();
		String PromptResult = driver.findElement(By.id("promptResult")).getText();
		Assert.assertEquals(PromptResult, "You entered I am Learning Selenium");

	}

	@Test(priority = 3)
	public void HandlingFrames() throws Exception {
		System.out.println("inside HandlingFrames");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("FramesURL"));
		waitForPageToLoad();
		// driver.switchTo().frame(ObjRepository.Single_Frame);
		// driver.switchTo().frame("singleframe");
		library.switchToFrameUsingIdOrName("singleframe");
		takescreeshot(driver);
		// driver.findElement(By.xpath(ObjRepository.SingleFrameTextArea)).sendKeys("text
		// area inside frame");
		library.FindElement(ObjRepository.SingleFrameTextArea).sendKeys("text area inside frame");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[contains(text(),'Iframe with in an Iframe')]")).click();
		WebElement ParentframeElement = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(ParentframeElement);
		WebElement childframeElement = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(childframeElement);
		takescreeshot(driver);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("text area inside second frame");
		driver.switchTo().defaultContent();
	}

	@Test(priority = 4)
	public void HandlingWindows() {
		System.out.println("inside HandlingWindows");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("WindowsURL"));
		waitForPageToLoad();
		Set<String> AllWindows = driver.getWindowHandles();
		String ParentWindow = driver.getWindowHandle();
		for (String IndividualWindow : AllWindows) {
			driver.switchTo().window(IndividualWindow);
			String title = driver.getTitle();
			System.out.println("title: " + title);
			if (title.equalsIgnoreCase("tech mahindra")) {
				driver.manage().window().maximize();
			} else if (title.equalsIgnoreCase("icici")) {
				// driver.close();//closes only the current instance of driver
			} else if (title.contains("Naukri")) {
				String str = driver.getPageSource();
				// System.out.println("content of page source: "+str);
			} else {
				driver.switchTo().window(ParentWindow);
			}
		}
		// driver.quit();//closes all instances of browsers which are open
	}

	@Test(priority = 5)
	public void HandlingWebTable() {
		System.out.println("inside HandlingWebTable");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("WebTableURL"));
		waitForPageToLoad();
		List<WebElement> AllLastNames = library.FindElements(ObjRepository.WebTableAllNames);
		// for(WebElement LastName : AllLastNames){
		for (int i = 1; i <= AllLastNames.size(); i++) {
			// String IndLastName = LastName.getText();
			String IndLastName = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[3]"))
					.getText();
			if (IndLastName.equals("Nash")) {
				String Salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[7]"))
						.getText();
				String StartDate = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[6]"))
						.getText();
				String Office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[5]"))
						.getText();
				String Position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[4]"))
						.getText();
				System.out.println("Salary: " + Salary);
				System.out.println("StartDate: " + StartDate);
				System.out.println("Office: " + Office);
				System.out.println("Position: " + Position);
				break;
			}
		}

	}

	@Test(priority = 6)
	public void MouseRightClickOperation() {
		System.out.println("inside MouseRightClickOperation");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("mouseOpeartionRightClick"));
		waitForPageToLoad();
		WebElement rightClickElement = library.FindElement(ObjRepository.MouseOperationRightClick);
		library.RightClick(rightClickElement);
		library.FindElement(ObjRepository.DailogBoxCopy).click();
		Alert alertObj = driver.switchTo().alert();
		String AlertText = alertObj.getText();
		System.out.println("AlertText: " + AlertText);
		Assert.assertEquals(AlertText, "clicked: copy");
		/*
		 * if(AlertText.contains("copy")){ alertObj.accept(); }
		 */
		alertObj.accept();
	}

	@Test(priority = 7)
	public void MouseDoubleClickOperation() throws InterruptedException {
		System.out.println("inside MouseDoubleClickOperation");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("mouseOpeartionDoubleClick"));
		waitForPageToLoad();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,-500)");

		// js.executeScript("window.scrollBy(0,1000)");//To scroll vertically
		// Down by 1000 pixels
		// js.executeScript("window.scrollBy(0,-500)");//To scroll vertically Up
		// by 500 pixels
		// js.executeScript("window.scrollBy(500,0)");//To scroll horizontally
		// right by 500 pixels
		// js.executeScript("window.scrollBy(-500,0)");//To scroll horizontally
		// left by 500 pixels
		// Color loginButtonColour =
		// Color.fromString(driver.findElement(By.id("login")).getCssValue("color"));

		WebElement element = library.FindElement(ObjRepository.DoubleCickFrame);
		js.executeScript("arguments[0].scrollIntoView();", element);
		// driver.switchTo().frame(element);
		library.switchToFrameusingWebElementRef(element);

		WebElement doubleclick = library.FindElement(ObjRepository.DoubleClickbox);
		library.DoubleClick(doubleclick);

		Color BackGroundColor = Color
				.fromString(library.FindElement(ObjRepository.DoubleClickbox).getCssValue("background-color"));
		System.out.println("BackGroundColor:" + BackGroundColor);
		String ActualBackGroundColor = BackGroundColor.asRgba();
		System.out.println("ActualBackGroundColor:" + ActualBackGroundColor);
		Assert.assertEquals(ActualBackGroundColor, "rgba(255, 255, 0, 1)");
		driver.switchTo().defaultContent();// to come back from frame to normal
											// page
	}

	@Test(priority = 8)
	public void MouseDragAndDropOperation() throws InterruptedException {
		System.out.println("inside MouseDragAndDropOperation");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("mouseOperationDragAndDrop"));
		waitForPageToLoad();
		WebElement frameElement = library.FindElement(ObjRepository.DoubleCickFrame);
		library.switchToFrameusingWebElementRef(frameElement);
		WebElement source = library.FindElement(ObjRepository.DragAndDropSource);
		WebElement target = library.FindElement(ObjRepository.DragAndDropTarget);
		Actions Obj = new Actions(driver);
		// Obj.dragAndDrop(source, target).build().perform();
		Obj.clickAndHold(source);
		Obj.moveToElement(target);
		Obj.release(target).build().perform();
		driver.switchTo().defaultContent();// to come back from frame to normal
											// page

		/*
		 * Color BackGroundColor = Color.fromString(
		 * library.FindElement(ObjRepository.DragAndDropTarget).getCssValue(
		 * "background-color")); String ActualBackGroundColor =
		 * BackGroundColor.asRgba(); System.out.println("ActualBackGroundColor:"
		 * + ActualBackGroundColor); Assert.assertEquals(ActualBackGroundColor,
		 * "rgba(255, 255, 0, 1)");
		 */
	}

	@Test(priority = 9)
	public void ValidateLinks() throws InterruptedException {
		System.out.println("inside ValidateLinks");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("Links"));
		waitForPageToLoad();
		List<WebElement> AllLinks = library.FindElements(ObjRepository.Links);
		for (WebElement individualLink : AllLinks) {
			String link = individualLink.getAttribute("href");
			try {
				library.ValidateLink(link);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test(priority = 10)
	public void ValidateFileUpload() throws InterruptedException, AWTException {
		System.out.println("inside ValidateFileUpload");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("FileUpload"));
		waitForPageToLoad();
		Thread.sleep(8000);
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element = library.FindElement(ObjRepository.FileUploadBrowse1);
		// js.executeScript("arguments[0].scrollIntoView();", element);
		// element.click();
		Actions obj = new Actions(driver);
		obj.click(element).build().perform();
		// library.FindElement(ObjRepository.FileUploadBrowse1).click();
		StringSelection objStringSelection = new StringSelection(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.jpg");
		Clipboard objClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		objClipboard.setContents(objStringSelection, null);
		try {
			Transferable objTransferable = objClipboard.getContents(null);
			if (objTransferable.isDataFlavorSupported(DataFlavor.stringFlavor))
				System.out.println(objTransferable.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Robot objRobot = new Robot();
		objRobot.delay(250);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_CONTROL);
		objRobot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		objRobot.keyRelease(KeyEvent.VK_V);
		objRobot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(4000);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}

	@Test(priority = 11)
	public void ValidatingFileDownload() throws InterruptedException {
		System.out.println("inside ValidatingFileDownload");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("FileDownload"));
		waitForPageToLoad();
		Thread.sleep(5000);
		WebElement element = library.FindElement(ObjRepository.FileDownload1MB);
		Actions obj = new Actions(driver);
		obj.click(element).build().perform();

		Thread.sleep(10000);
		File objFile = new File(System.getProperty("user.dir"));
		File[] listOfFiles = objFile.listFiles();
		boolean fileFound = false;
		File obj_File = null;
		for (File IndividualFile : listOfFiles) {
			String FileName = IndividualFile.getName();
			System.out.println(FileName);
			if (FileName.contains("file-sample")) {
				fileFound = true;
				obj_File = new File(FileName);
			}
		}
		Assert.assertTrue(fileFound, "File Downloaded Not Found");
		obj_File.deleteOnExit();

	}

	@Test(priority = 12)
	public void ValidDataDriven() throws IOException, AWTException {
		System.out.println("inside ValidDataDriven");
		extenttest = extentReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProp.getProperty("AutomationRegister"));
		waitForPageToLoad();
		try {
			FileInputStream FileinputObj = new FileInputStream(
					new File(System.getProperty("user.dir") + "//src//test//resources//AutomationDemoSite.xlsx"));
			XSSFWorkbook WorkBookObj = new XSSFWorkbook(FileinputObj);
			XSSFSheet SheetObj = WorkBookObj.getSheet("TestData");
			int TotalRows = SheetObj.getLastRowNum();
			for (int row = 1; row <= TotalRows; row++) {
				testData = ReadTestDataFromExcelFile(SheetObj, row);
				if ((testData.get("RunMode").equals("Yes"))) {
					testData.get("FirstName");
					testData.get("Address");
					System.out.println(testData.get("FirstName"));
					System.out.println(testData.get("Address"));
					library.FindElement(ObjRepository.DataDrivenFirstName).clear();
					library.FindElement(ObjRepository.DataDrivenFirstName).sendKeys(testData.get("FirstName"));
					library.FindElement(ObjRepository.DataDrivenlastName).clear();
					library.FindElement(ObjRepository.DataDrivenlastName).sendKeys(testData.get("LastName"));
					library.FindElement(ObjRepository.DataDrivenAddress).clear();
					library.FindElement(ObjRepository.DataDrivenAddress).sendKeys(testData.get("Address"));
					library.FindElement(ObjRepository.DataDrivenEmailAddress).clear();
					library.FindElement(ObjRepository.DataDrivenEmailAddress).sendKeys(testData.get("EmailAddress"));
					library.FindElement(ObjRepository.DataDrivenPhoneNumber).clear();
					library.FindElement(ObjRepository.DataDrivenPhoneNumber).sendKeys(testData.get("PhoneNumber"));

					if (testData.get("Gender").equalsIgnoreCase("male")) {
						library.FindElement(ObjRepository.DataDrivenGenderMale).click();
					} else {
						library.FindElement(ObjRepository.DataDrivenGenderFeMale).click();
					}

					if (testData.get("Hobbies").equalsIgnoreCase("Cricket")) {
						library.FindElement(ObjRepository.DataDrivenCricket).click();
					} else if (testData.get("Hobbies").equalsIgnoreCase("MOvies")) {
						library.FindElement(ObjRepository.DataDrivenMovies).click();
					} else if (testData.get("Hobbies").equalsIgnoreCase("hockey")) {
						library.FindElement(ObjRepository.DataDrivenHockey).click();
					}
					if (row > 1) {
						library.FindElement(ObjRepository.DataDrivenCloseIconLanguage).click();
					}
					library.FindElement(ObjRepository.DataDrivenLanguage).click();
					List<WebElement> AllLanguages = library.FindElements(ObjRepository.DataDrivenAll_Languags);
					SelectValueFromDropDownLanguage(AllLanguages, testData.get("Languages"));

					library.FindElement(ObjRepository.DataDrivenTag_Skills).click();

					library.FindElement(ObjRepository.DataDrivenSkills).click();
					List<WebElement> AllSkills = library.FindElements(ObjRepository.DataDrivenAllskills);
					SelectValueFromDropDown(AllSkills, testData.get("Skills"));

					library.FindElement(ObjRepository.DataDrivenSelectCountry).click();
					library.FindElement(ObjRepository.DataDrivenTextBox_SelectCountry)
							.sendKeys(testData.get("SelectCountry"));

					Robot obj = new Robot();
					obj.keyPress(KeyEvent.VK_ENTER);
					obj.keyRelease(KeyEvent.VK_ENTER);

					library.FindElement(ObjRepository.DataDrivenDOB_YY).click();
					List<WebElement> AllYears = library.FindElements(ObjRepository.DataDrivenAllYears);
					SelectValueFromDropDown(AllYears, testData.get("DOB_YY"));

					library.FindElement(ObjRepository.DataDrivenDOB_MM).click();
					List<WebElement> AllMonths = library.FindElements(ObjRepository.DataDrivenAllMonths);
					SelectValueFromDropDown(AllMonths, testData.get("DOB_MM"));

					library.FindElement(ObjRepository.DataDrivenDOB_DD).click();
					List<WebElement> AllDays = library.FindElements(ObjRepository.DataDrivenALLDays);
					SelectValueFromDropDown(AllDays, testData.get("DOB_DD"));

					library.FindElement(ObjRepository.DataDrivenPassword).clear();
					library.FindElement(ObjRepository.DataDrivenPassword).sendKeys(testData.get("Password"));
					library.FindElement(ObjRepository.DataDrivenConformPwd).clear();
					library.FindElement(ObjRepository.DataDrivenConformPwd).sendKeys(testData.get("confirm_password"));
					
					FileOutputStream objFileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "//src//test//resources//AutomationDemoSite.xlsx"));
					WriteResultsToExcel(WorkBookObj,SheetObj,row);
					WorkBookObj.write(objFileOutputStream);
				} else {
					System.out.println("Run Mode is not given as Yes for row:"+row);
				}
			}
			WorkBookObj.close();
			FileinputObj.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void WriteResultsToExcel(XSSFWorkbook workBookObj, XSSFSheet sheetObj, int row) {
		sheetObj = workBookObj.getSheet("TestData");
		XSSFCellStyle CellStyle = workBookObj.createCellStyle();
		// CellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		System.out.println("Row Number in excel is :" + row);
		sheetObj.getRow(row).createCell(18).setCellValue("PASS");
		sheetObj.getRow(row).getCell(18).setCellStyle(CellStyle);
	}

	private HashMap<String, String> ReadTestDataFromExcelFile(XSSFSheet sheetObj, int row) {
		DataFormatter objFormatter = new DataFormatter();
		testData.put("RunMode", sheetObj.getRow(row).getCell(0).getStringCellValue());
		testData.put("TestCaseName", sheetObj.getRow(row).getCell(1).getStringCellValue());
		testData.put("FirstName", sheetObj.getRow(row).getCell(2).getStringCellValue());
		testData.put("LastName", sheetObj.getRow(row).getCell(3).getStringCellValue());
		testData.put("Address", sheetObj.getRow(row).getCell(4).getStringCellValue());
		testData.put("EmailAddress", sheetObj.getRow(row).getCell(5).getStringCellValue());

		String PhoneNumber = objFormatter.formatCellValue(sheetObj.getRow(row).getCell(6));
		testData.put("PhoneNumber", PhoneNumber);

		testData.put("Gender", sheetObj.getRow(row).getCell(7).getStringCellValue());
		testData.put("Hobbies", sheetObj.getRow(row).getCell(8).getStringCellValue());
		testData.put("Languages", sheetObj.getRow(row).getCell(9).getStringCellValue());

		testData.put("Skills", sheetObj.getRow(row).getCell(10).getStringCellValue());
		testData.put("Country", sheetObj.getRow(row).getCell(11).getStringCellValue());
		testData.put("SelectCountry", sheetObj.getRow(row).getCell(12).getStringCellValue());

		String DOB_YY = objFormatter.formatCellValue(sheetObj.getRow(row).getCell(13));
		testData.put("DOB_YY", DOB_YY);

		testData.put("DOB_MM", sheetObj.getRow(row).getCell(14).getStringCellValue());

		String DOB_DD = objFormatter.formatCellValue(sheetObj.getRow(row).getCell(15));
		testData.put("DOB_DD", DOB_DD);

		testData.put("Password", sheetObj.getRow(row).getCell(16).getStringCellValue());
		testData.put("confirm_password", sheetObj.getRow(row).getCell(17).getStringCellValue());

		return testData;
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		System.out.println("inside afterMethod");
		if (result.getStatus() == ITestResult.FAILURE) {
			// to add test case name in extent report
			extenttest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
			// to add error/exception that occured in extent report
			extenttest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
			String screenshotPath = library.takescreeshot(driver, result.getName());
			// adding screen shot in extent report
			extenttest.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			extenttest.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extenttest.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		// driver.quit();
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		try {
			StartExtentReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		extentReport.flush();// after this line execution export report will be
								// generated
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		try {
			library.readProperyFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // declaration

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

}
