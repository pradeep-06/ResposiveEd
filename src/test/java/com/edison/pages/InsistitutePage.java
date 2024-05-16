package com.edison.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.CommonMethods;
import modules.CommonBean;


public class InsistitutePage {
	
	WebDriver driver;
	Actions actions;
	CommonMethods objCommonMethods;
	
	@FindBy(how = How.XPATH,using = "//mat-select[@formcontrolname='product_id']") private WebElement productElement;
	@FindBy(how = How.XPATH,using = "//mat-select[@name='orgIdParam' or @name='org_id' or @formcontrolname='schoolID']") private WebElement insitututeDropdown;
	@FindBy(how = How.XPATH,using = "//input[@formcontrolname='name']") private WebElement nameElement;
	@FindBy(how = How.XPATH,using = "//mat-select[@formcontrolname='status']") private WebElement statusElement;
	@FindAll({
		@FindBy(how = How.XPATH,using = "//tbody[@class='mdc-data-table__content']//child::tr")
	})List<WebElement> rows;
	
	
	
	public InsistitutePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(this.driver);
		objCommonMethods=new CommonMethods(driver);
	}
	
	public void selectProduct(String value) {
		objCommonMethods.matSelectdropdown(productElement, value);
	  CommonBean.policyLevelPassedCntr++;
	}
	
	public void setName(String value) {
		nameElement.clear();
		nameElement.sendKeys(value);
		 CommonBean.policyLevelPassedCntr++;
	}
	
	public void selectStatus(String value) {
		objCommonMethods.matSelectdropdown(statusElement, value);
		  CommonBean.policyLevelPassedCntr++;
	}
	
	public void verifyInActiveUserManagement(String insistitutename) {
	   verifyInActiveInsistute(insitututeDropdown, insistitutename);	
	}
	public void verifyInActiveAdvisor(String insistitutename) {
		 verifyInActiveInsistute(insitututeDropdown, insistitutename);
		
	}
	public void verifyInActiveTeacher(String insistitutename) {
		 verifyInActiveInsistute(insitututeDropdown, insistitutename);
	}
	public void verifyInActiveStudentApplication(String insistitutename) {
		 verifyInActiveInsistute(insitututeDropdown, insistitutename);
	}
	
	
	private void verifyInActiveInsistute(WebElement element,String value) {
		try {
			    CommonBean.validationpoints++;
				element.click();
				List<WebElement> options =element.findElements(By.xpath("following::div[@role='listbox']//mat-option"));
				int count=0;
				for(WebElement option:options) {
					String actual = option.findElement(By.xpath("child::span")).getText().trim();
					if(!actual.equals(value)) {			    
						count++;
					}	
					if(count==options.size()) {
						CommonBean.policyLevelPassedCntr++;
					}
				}
				element.sendKeys(Keys.TAB);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	public void inisitutetable(String Name, String Status, String System, String isConsortium, String action) {
		if(Name==null||Name=="null")Name="";	
		if(Status ==null||Status =="null") Status="";		
		if(System==null||System=="null")System="";
		if(isConsortium==null||System=="null")isConsortium="";
		
		ArrayList<String> array= new ArrayList<String>();
		Collections.addAll(array, Name,Status,System,isConsortium,"search","");	
		verifyRecord(array,rows, action);
		
	}
	private void verifyRecord(ArrayList<String>array,List<WebElement> rows, String action ) {
	  try {
		  CommonBean.validationpoints++;
		  boolean bool=false;
		  while (bool==false) {
			  Thread.sleep(3000);
			  for(WebElement row:rows) {		  
				  int count=0;
				  List<WebElement> elements=row.findElements(By.xpath("child::td"));
				  for(int i=0;i<array.size();i++) {
					  String actual=null;
					   actual=elements.get(i).getText().trim();
					  String expected=array.get(i);		  
					  if(actual.contains(expected)) {
						  count++;
					  }
					   if(count==array.size()&&action.equalsIgnoreCase("Filter")) {
						   actions.scrollToElement(row);
//						   js.executeScript("arguments[0].scrollIntoView(true);", elements.get(i));
						   CommonBean.policyLevelPassedCntr++;
						   bool=true;
						   break;
					   }
					   if(count==array.size()&&action.equalsIgnoreCase("MarkInActive")) {
						   actions.scrollToElement(row);
						   WebElement element=null;
						   element=row.findElement(By.xpath("child::td[6]/button"));
						   objCommonMethods.JavascriptClick(element);
//						   row.findElement(By.xpath("child::td[6]/button")).click();
						   CommonBean.policyLevelPassedCntr++;
						   bool=true;
						   break;
					   }
					   if(count==array.size()&&action.equalsIgnoreCase("SearchIcon")) {
						   actions.scrollToElement(row);
						   WebElement element=null;
						   element=row.findElement(By.xpath("child::td[5]/button"));
						   objCommonMethods.JavascriptClick(element);
//						   row.findElement(By.xpath("child::td[6]/button")).click();
						   CommonBean.policyLevelPassedCntr++;
						   bool=true;
						   break;
					   }
				  }
			  }
			
			  if(bool==true) {
				  
				 break;
				 
			  }else {
				  
			  }
		  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
