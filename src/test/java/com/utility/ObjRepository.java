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


}
