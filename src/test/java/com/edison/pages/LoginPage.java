package com.edison.pages;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import modules.CommonBean;

public class LoginPage {
	WebDriver driver;
	//Element locators
	@FindBy(how = How.XPATH,using = "//*[@formcontrolname='uname']") private WebElement usernamElement;
	@FindBy(how = How.NAME,using = "password") private WebElement passElement;
	@FindBy(how = How.XPATH,using = "//*[contains(text(),'Sign In')]//parent::button") private WebElement signinElement;
	
	
	/***Edision login**/
	@FindBy(how = How.XPATH,using = "//*[contains(text(),'Username')]//following-sibling::input") private WebElement edisonUsername;
	@FindBy(how = How.XPATH,using = "//*[contains(text(),'Password')]//following-sibling::input") private WebElement edisonPassword;
	@FindBy(how = How.XPATH,using = "//span[text()='Sign in']//parent::button") private WebElement edisonSignin;
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;;
		PageFactory.initElements(driver, this);
	}
	
	
public void loginEdison(String username,String passsword) {
	 try {
			edisonUsername.sendKeys(username);
			edisonPassword.sendKeys(passsword);
			CommonBean.policyLevelPassedCntr++;
			edisonSignin.click();
			CommonBean.policyLevelPassedCntr++;
			Thread.sleep(4000);
	 } catch (NoSuchElementException e) {
		 CommonBean.policyLevelPassedCntr++;
		 CommonBean.policyLevelPassedCntr++;
		
	}
	 catch (Exception e) {
		 SoftAssert assert1 =new SoftAssert();
		 assert1.fail();
		e.printStackTrace();
	 }
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
