package com.cucumber.PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import com.cucumber.TestBase.TestBase;
import com.cucumber.utility.Constants;


public class allUserManagementDropDwnHandlePageObj {
	WebDriver driver;
	Actions act;
	TestBase testbase;

	By userMenu = By.xpath("//span[text()='Users']/../../../..");
	By institutinsDropDown = By.xpath("//mat-label[text()='Institutions:']/../../../..");
	By instSelect =By.xpath("//span[text()='ResponsiveEd Institutions']");
	By rolesDropDown = By.xpath("//mat-label[text()=\"Role's:\"]/../../../..");
	By roleSelect = By.xpath("(//span[text()='Administrator'])[2]");
	By enrollmentStatusDropDown = By.xpath("//mat-label[text()='Enrollment Status:']/../../../..");
	By statusSelect = By.xpath("(//span[text()='All'])[2]");
	By searchByDropDown = By.xpath("//mat-label[text()='Search By:']/../../../..");
	By searchSelect = By.xpath("(//span[text()='All'])[3]");
    By goButton = By.xpath("//span[text()='GO']");


    public allUserManagementDropDwnHandlePageObj(WebDriver driver) {
		this.driver = driver;
		testbase = new TestBase();
	}


	public void ClickontheUSERMENU() throws Exception {
		Constants.driver.findElement(userMenu).click();
		Thread.sleep(2000);;
	}


	public void SelectinstitutinsDropDown() throws Exception {
		Constants.driver.findElement(institutinsDropDown).click();
		Thread.sleep(2000);
		Constants.driver.findElement(instSelect).click();
		Thread.sleep(2000);
	}


	public void SelectrolesDropDown() throws Exception {
		Constants.driver.findElement(rolesDropDown).click();
		Thread.sleep(2000);
		Constants.driver.findElement(roleSelect).click();
		Thread.sleep(2000);
		
	}


	public void SelectenrollmentStatusDropDown() throws Exception {
		Constants.driver.findElement(enrollmentStatusDropDown).click();
		Thread.sleep(2000);
		Constants.driver.findElement(statusSelect).click();
		Thread.sleep(2000);
	}


	public void SlectsearchByDropDown() throws Exception {
		Constants.driver.findElement(searchByDropDown).click();
		Thread.sleep(2000);
		Constants.driver.findElement(searchSelect).click();
		Thread.sleep(2000);
	}


	public void ClickONGOBUTTON() throws InterruptedException {
		Constants.driver.findElement(goButton).click();
		Thread.sleep(4000);
	}


	
    

}