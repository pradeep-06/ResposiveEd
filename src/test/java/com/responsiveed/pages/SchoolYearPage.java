package com.responsiveed.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import common.CommonMethods;
import modules.CommonBean;

public class SchoolYearPage {
	
	WebDriver driver;
	Actions actions;
	CommonMethods objCommonMethods;
	
	
	@FindBy(how=How.XPATH,using = "//*[text()='Add School Year']//ancestor::button")private WebElement addschoolyearDriver;
	@FindBy(how=How.XPATH,using = "//*[text()='Create']//ancestor::button")private WebElement createElement;
	@FindBy(how=How.XPATH,using = "//*[text()='Create']//ancestor::button[@type='submit']")private WebElement submitElement;
	@FindBy(how=How.NAME,using = "schoolYearId")private WebElement gobalSYElement;
	@FindBy(how=How.XPATH,using = "//input[@placeholder='School Year Name']")private WebElement schoolyearElement;
	@FindBy(how=How.XPATH,using = "//input[@formcontrolname='startString']")private WebElement startdatlElement;
	@FindBy(how=How.XPATH,using = "//input[@formcontrolname='endString']")private WebElement enddatElement;
	@FindAll({
		@FindBy(how=How.XPATH,using = "//tbody[@role='rowgroup']//child::tr")
	}) private List<WebElement> rows;
	@FindBy(how=How.XPATH,using = "//mat-icon[text()='keyboard_arrow_left']")private WebElement expandElement;
	
	
	public SchoolYearPage(WebDriver driver) {
		this.driver=driver;
		this.actions=new Actions(driver);
		PageFactory.initElements(driver, this);
		objCommonMethods=new CommonMethods(driver);
	}
	
	public void clickAddSchoolYear() {
	 try {
			Thread.sleep(3000);
			addschoolyearDriver.click();
			CommonBean.policyLevelPassedCntr++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void clickOnCreate() {
	  try {
			createElement.click();
			CommonBean.policyLevelPassedCntr++;
	  } catch (ElementClickInterceptedException e) {
		  submitElement.click();
	  }
		
	}
	
	public void createSchoolYear(String gobalSY,String schoolname,String startdate,String enddate) {
		objCommonMethods.matSelectdropdown(gobalSYElement, gobalSY);
		CommonBean.policyLevelPassedCntr++;
		schoolyearElement.sendKeys(schoolname);
		CommonBean.policyLevelPassedCntr++;
		startdatlElement.sendKeys(startdate);
		CommonBean.policyLevelPassedCntr++;
		enddatElement.sendKeys(enddate);
		CommonBean.policyLevelPassedCntr++;
		
	}
	
	
	public void verifycreateSchoolYear(String name, String startdate,String enddate,String extendedate,String action) {
		
		if(name==null||name=="null") name="";
		if(startdate==null||startdate=="null")startdate="";
		if(enddate==null||enddate=="null")enddate="";
		if(extendedate==null||extendedate=="null")extendedate="";
		ArrayList<String> array=new ArrayList<String>();
		Collections.addAll(array, "",name,startdate,enddate,extendedate,"","");
		expandElement.click();
		verifyRecord(array, rows, action);
	}
	
	
	private void verifyRecord(ArrayList<String> array,List<WebElement> rows,String action) {
		
		boolean bool=false;	
		CommonBean.validationpoints++;
		for(WebElement row:rows) {
			actions.scrollToElement(row);
			List<WebElement> columns=row.findElements(By.xpath("child::td"));
			int count=0;	
			for(int i=0;i<array.size();i++) {
				String acutal=columns.get(i).getText().trim().replace("-", "/");
				String expectd=array.get(i);
				if(acutal.contains(expectd)) {
					count++;
				}
				if(count==array.size()&&action.equalsIgnoreCase("filter")) {
					CommonBean.policyLevelPassedCntr++;
					bool=true;
					break;
				}
				if(count==array.size()&&action.equalsIgnoreCase("edit")) {
					
					columns.get(5).findElement(By.xpath("child::button")).click();
					CommonBean.policyLevelPassedCntr++;
					bool=true;
					break;
					
				}
				if(count==array.size()&&action.equalsIgnoreCase("delete")) {
					columns.get(6).findElement(By.xpath("child::button")).click();
					CommonBean.policyLevelPassedCntr++;
					bool=true;
					break;
				}
				if(count==array.size()&&action.equalsIgnoreCase("checkbox")) {
					columns.get(0).findElement(By.xpath("descendant::input")).click();
					CommonBean.policyLevelPassedCntr++;
					bool=true;
					break;
				}
				
			}
		}
		if(bool==false) {
			Assert.fail();
		}
		
	}
	

}
