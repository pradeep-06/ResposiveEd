package com.responsiveed.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UserManagement {
	WebDriver driver;
	
	@FindBy(how = How.XPATH,using = "//input[@attribute=value]")
	private WebElement firstName;
	public WebElement getFirstName() {
		return firstName;
	}
	
	
	public UserManagement(WebDriver driver) {
		PageFactory.initElements(this.driver, driver);
	}

}
