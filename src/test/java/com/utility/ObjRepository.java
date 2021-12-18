package com.utility;

public class ObjRepository {
	
 public static final String SingleFrameTextArea = constants.xpath+"&"+"//input[@type='text']";
 public static final String Single_Frame = constants.ID+"&"+"singleframe";
 public static final String WebTableAllNames = constants.xpath+"&"+"//table[@id='example']/tbody/tr/td[3]";
 public static final String MouseOperationRightClick = constants.xpath+"&"+"//span[contains(text(),'right click me')]";
 public static final String DailogBoxCopy = constants.xpath+"&"+"//ul[@class='context-menu-list context-menu-root']/li[3]";
 
 public static final String DoubleCickFrame = constants.tagName+"&"+"iframe";
 public static final String DoubleClickbox = constants.xpath+"&"+"//span[contains(text(),'Double click the block')]/preceding-sibling::div";
 public static final String DragAndDropSource = constants.xpath+"&"+"//*[@id='draggable']";
 public static final String DragAndDropTarget = constants.xpath+"&"+"//*[@id='droppable']";

 public static final String Links = constants.tagName+"&"+"a";
 public static final String FileUploadBrowse = constants.xpath+"&"+"//input[@id='input-4']/preceding-sibling::span";
 public static final String FileUploadBrowse1 = constants.xpath+"&"+"/html/body/section/div[1]/div/div/div[1]/div[3]/span";
 
 public static final String FileDownload1MB = constants.xpath+"&"+"//a[@download='file-sample_1MB.doc']";
 
 
 public static final String DataDrivenFirstName = constants.xpath+"&"+"//input[@placeholder='First Name']";
 public static final String DataDrivenlastName = constants.xpath+"&"+"//input[@placeholder='Last Name']";
 public static final String DataDrivenAddress = constants.xpath+"&"+"//*[@ng-model='Adress']";
 public static final String DataDrivenEmailAddress = constants.xpath+"&"+"//*[@ng-model='EmailAdress']";
 public static final String DataDrivenPhoneNumber = constants.xpath+"&"+"//*[@type='tel']";
 public static final String DataDrivenGenderMale = constants.xpath+"&"+"//*[@value='Male']";
 public static final String DataDrivenGenderFeMale= constants.xpath+"&"+"//*[@value='FeMale']";
 public static final String DataDrivenCricket = constants.ID+"&"+"checkbox1";
 public static final String DataDrivenMovies = constants.ID+"&"+"checkbox2";
 public static final String DataDrivenHockey = constants.ID+"&"+"checkbox3";
 public static final String DataDrivenLanguage = constants.ID+"&"+"msdd";
 public static final String DataDrivenAll_Languags = constants.xpath+"&"+"//*[@id='msdd']/following-sibling::div/ul/li/a";
 public static final String DataDrivenSkills = constants.ID+"&"+"Skills";
 public static final String DataDrivenAllskills = constants.xpath+"&"+"//*[@id='Skills']/option";
 public static final String DataDrivenSelectCountry = constants.xpath+"&"+"//*[@role='combobox']/span[2]";
 public static final String DataDrivenTextBox_SelectCountry = constants.xpath+"&"+"//*[@type='search']";
 public static final String DataDrivenDOB_YY = constants.xpath+"&"+"//*[@id='yearbox']";
 public static final String DataDrivenAllYears = constants.xpath+"&"+"//*[@id='yearbox']/option";
 public static final String DataDrivenDOB_MM = constants.xpath+"&"+"//*[@placeholder='Month']";
 public static final String DataDrivenAllMonths = constants.xpath+"&"+"//*[@placeholder='Month']/option";
 public static final String DataDrivenDOB_DD = constants.xpath+"&"+"//*[@id='daybox']";
 public static final String DataDrivenALLDays = constants.xpath+"&"+"//*[@id='daybox']/option";
 public static final String DataDrivenPassword = constants.ID+"&"+"firstpassword";
 public static final String DataDrivenConformPwd = constants.ID+"&"+"secondpassword";
 public static final String DataDrivenTag_Skills= constants.xpath+"&"+"//label[contains(text(),'Skills')]";
 public static final String DataDrivenCloseIconLanguage= constants.xpath+"&"+"//span[@class='ui-icon ui-icon-close']";


 
 
 

 


}
