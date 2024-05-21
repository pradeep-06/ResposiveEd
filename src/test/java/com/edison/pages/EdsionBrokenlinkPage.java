package com.edison.pages;

import org.openqa.selenium.WebDriver;

import java.lang.invoke.StringConcatFactory;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import common.CommonMethods;
import modules.*;

public class EdsionBrokenlinkPage {
	
	WebDriver driver;
	Actions actions;
	CommonMethods objCommonMethods;
	String currentwindowhandle;
	
	public static ArrayList<String> brokenlinks= new ArrayList<String>();
	
	@FindBy(how = How.ID,using = "searchOrgSelect") private WebElement InstitutionElement;
	@FindBy(how = How.XPATH,using = "//select[@id='searchTypeSelect']") private WebElement SearchByElement;
	@FindBy(how=How.XPATH,using ="//input[@id='searchValText']")private WebElement SearchByEnterElement;
	@FindBy(how = How.XPATH,using = "//select[@id='searchIsActiveSelect']") private WebElement ActiveInactiveElement; 
	@FindAll({
		@FindBy(how = How.XPATH,using = "(//table[@class='info_table' and @cellpadding='2' ]//child::tbody//tr[2])[1]//child::td")
	})private List<WebElement> rows;

	/***Broken Links Xpath****/	
	@FindAll({
		@FindBy(how = How.XPATH,using = "//ul[@class='tree-default']//child::li[@depth='1']//a[contains(text(),'Unit')]")
	})private List<WebElement> units;
	
	@FindAll({
		@FindBy(how = How.XPATH,using = "//a[contains(@href,'https') or contains(@href,'http')]")
	})private List<WebElement> linksElements;
	
	
	
	public EdsionBrokenlinkPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(this.driver);
		objCommonMethods=new CommonMethods(driver);
	}
public void SelectInstitution(String value) {
  try {
	  Thread.sleep(3000);
		objCommonMethods.selectExpandDropdown(InstitutionElement, value);
//		  Select select =new Select(InstitutionElement);
//		  select.selectByVisibleText(value);
		  CommonBean.policyLevelPassedCntr++;
  } catch (Exception e) {
		Assert.fail();
	e.printStackTrace();
  }
	
}
	public void SearchBy(String dropdownvalue,String inputSearBy) {
	try {
		Select select =new Select(SearchByElement);
		  select.selectByVisibleText(dropdownvalue);
		  Thread.sleep(2000);
		  SearchByEnterElement.sendKeys(inputSearBy);
		  CommonBean.policyLevelPassedCntr++;	
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		} 
	}
 
	public void selectActiveInactive(String value) {
		 Select select =new Select(ActiveInactiveElement);
		  select.selectByVisibleText(value);
		  CommonBean.policyLevelPassedCntr++;
	}
public void changeDriverFocus() {
 try {
	 currentwindowhandle=driver.getWindowHandle();
	 objCommonMethods.switchTOWindow(currentwindowhandle);
	 Thread.sleep(3000);
 	} catch (Exception e) {
 		Assert.fail();
 	}
	
}
	
	public void verifyCourseRecord(String Code, String Name, String Abbreviation, String Active_Inactive,String action) throws SocketException {
		if(Code==null||Code=="null")Code="";
		if(Name==null||Name=="null")Name="";
		if(Abbreviation==null||Abbreviation=="null")Abbreviation="";
		if(Active_Inactive==null||Active_Inactive=="null")Active_Inactive="";
		ArrayList<String> arrayList=new ArrayList<String>();
		System.out.println(rows.size());
		Collections.addAll(arrayList, "",Code,Name,Abbreviation,Active_Inactive,"","");
		System.out.println(arrayList.size());
		verifyRecord(arrayList, rows, action);
	}
	
	
private void verifyRecord(ArrayList<String> array,List<WebElement> rows, String action) throws SocketException  {
 try {
	 Thread.sleep(3000);
	 int count=0;
	 for(int i=0;i<array.size();i++) {
		String actual=rows.get(i).getText().trim();
		String expected=array.get(i).trim();
		if(expected.contains(actual)) {
			count++;
		}
		
		if(count==array.size()&&action.equalsIgnoreCase("NameLink")) {
			rows.get(2).findElement(By.xpath("child::a")).click();
			CommonBean.policyLevelPassedCntr++;
			break;
		}
		if(count==array.size()&&action.equalsIgnoreCase("search")) {
			rows.get(5).click();
			CommonBean.policyLevelPassedCntr++;
			break;
		}
		 
	 }
 	} catch (Exception e) {
 		Assert.fail();
	e.printStackTrace();
 }
}

/***Broken Links****/	
public void verifyBrokenLinks(String name,String code) throws SocketException{
  try {
	/**Collected the Units*/
	  for(WebElement unitElement:units) {
		  WebElement classElement=null;
		  String unit=null;
		  String lesson=null;
		  String page=null;
		  classElement=unitElement.findElement(By.xpath("parent::li"));		  
		  Thread.sleep(1000);
		  if(!classElement.getAttribute("class").toLowerCase().contains("open")) {
			  actions.scrollToElement(unitElement).build().perform();
			  actions.moveToElement(unitElement).click().build().perform();
		  }
		  unit=unitElement.getText().trim();
		  /**collect the lessons*/
		  List<WebElement> lessonsElements=classElement.findElements(By.xpath("descendant::li[@depth='2']"));
		  for(WebElement lessonElement:lessonsElements) {
			  WebElement depth2=null;
			  depth2=lessonElement.findElement(By.xpath("descendant::div[@class='nonTitleDivs']"));
			  if(!lessonElement.getAttribute("class").toLowerCase().contains("open")) {
				  WebElement lessonlink=depth2.findElement(By.xpath("parent::a"));	
				  actions.scrollToElement(lessonlink).build().perform();;
				  actions.moveToElement(lessonlink).click().build().perform();
			  }
			  lesson=depth2.getText().trim();
			  /**collect the pages*/
			  List<WebElement> pagesElements=lessonElement.findElements(By.xpath("descendant::li[@depth='3']//a")); 
			  for(WebElement pageElement:pagesElements) {
				  pageElement.click();
				  page=pageElement.getText().trim();
				  String Unit_Lesson_page=unit+"-"+lesson+"-"+page;
//				  List<WebElement> linksElements=null;
//				  linksElements=driver.findElements(By.xpath("//a[contains(@href,'https') or contains(@href,'http')]"));
				  for(int i=0;i<linksElements.size();i++) {
					  String url=null; 
					   url=linksElements.get(i).getAttribute("href").trim();
					   Thread.sleep(1000);
					   URL link = new URL(url);
					   HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
					   httpURLConnection.setConnectTimeout(4000);
						try {
							
							httpURLConnection.connect();
						} catch (Exception e) {
							httpURLConnection.connect();
						}
						int statuscode = 0;
						
						try {
							 statuscode=httpURLConnection.getResponseCode();
						} catch (java.net.SocketException e) {	
							 try {
								 statuscode=httpURLConnection.getResponseCode();
							 } catch (Exception e2) {
								 statuscode=507;
								 brokenlinks.add(name);
								 brokenlinks.add(code);
								 brokenlinks.add(Unit_Lesson_page);
								 brokenlinks.add(url);
								 brokenlinks.add("FAIL");
								 brokenlinks.add("Connection failure");
							 } 			
						}
						if(statuscode!=507) {
							if ((statuscode >=200&&statuscode<=206)||(statuscode>=301&&statuscode<=301)) {
								brokenlinks.add(name);
								brokenlinks.add(code);
								brokenlinks.add(Unit_Lesson_page);
								brokenlinks.add(url);
								brokenlinks.add("PASS");
								brokenlinks.add(Integer.toString(statuscode));
//								System.out.println(url + " - " + httpURLConnection.getResponseMessage());
							} else {
								brokenlinks.add(name);
								brokenlinks.add(code);
								brokenlinks.add(Unit_Lesson_page);
								brokenlinks.add(url);
								brokenlinks.add("FAIL");
								brokenlinks.add(Integer.toString(statuscode));
							}
						}
						
				  }
				 
			  }
			  
		  }
		  
		  
				  
	  }
	  Thread.sleep(3000);
	  driver.close();
	  Thread.sleep(2000);
	  driver.switchTo().window(currentwindowhandle);
	  
//	  for(int i=0;i<brokenlinks.size();i++) {
//		  System.out.println(brokenlinks.get(i));
//	  }
	  
  } catch (Exception e) {
		Assert.fail();
	e.printStackTrace();
  }
}
}
