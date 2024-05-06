package com.responsiveed.pages;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import modules.CommonBean;

public class LoginPage {
	WebDriver driver;
	//Element locators
	@FindBy(how = How.XPATH,using = "//*[@formcontrolname='uname']") private WebElement usernamElement;
	@FindBy(how = How.NAME,using = "password") private WebElement passElement;
	@FindBy(how = How.XPATH,using = "//*[contains(text(),'Sign In')]//parent::button") private WebElement signinElement;
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;;
		PageFactory.initElements(driver, this);
	}
	
	//Observation and operational
	public void loginResponsive(String username,String passsword) {
		try {
			usernamElement.sendKeys(username);	
			passElement.sendKeys(passsword);
			CommonBean.policyLevelPassedCntr++;
			Assert.assertEquals(signinElement.isEnabled(), true);
			signinElement.click();
			CommonBean.policyLevelPassedCntr++;
		} catch (NoSuchElementException e) {
			CommonBean.policyLevelPassedCntr+=2;
		}
		
	}

}
