package com.responsiveed.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.CommonMethods;
import modules.CommonBean;

public class SchoolYearPage {
	
	WebDriver driver;
	Actions actions;
	CommonMethods objCommonMethods;
	
	
	@FindBy(how=How.XPATH,using = "//*[text()='Add School Year']//ancestor::button")private WebElement addschoolyearDriver;
	@FindBy(how=How.XPATH,using = "//*[text()='Create']//ancestor::button")private WebElement createElement;
	@FindBy(how=How.NAME,using = "schoolYearId")private WebElement gobalSYElement;
	@FindBy(how=How.XPATH,using = "//input[@placeholder='School Year Name']")private WebElement schoolyearElement;
	@FindBy(how=How.XPATH,using = "//input[@formcontrolname='startString']")private WebElement startdatlElement;
	@FindBy(how=How.XPATH,using = "//input[@formcontrolname='endString']")private WebElement enddatElement;
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
		createElement.click();
		CommonBean.policyLevelPassedCntr++;
	}
	
	public void createSchoolYear(String gobalSY,String schoolname,String startdate,String enddate) {
		objCommonMethods.matSelectdropdown(createElement, gobalSY);
		CommonBean.policyLevelPassedCntr++;
		schoolyearElement.sendKeys(schoolname);
		CommonBean.policyLevelPassedCntr++;
		startdatlElement.sendKeys(startdate);
		CommonBean.policyLevelPassedCntr++;
		enddatElement.sendKeys(enddate);
		CommonBean.policyLevelPassedCntr++;
		
	}
	

}
