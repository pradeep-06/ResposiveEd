package com.cucumber.PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import com.cucumber.TestBase.TestBase;
import com.cucumber.utility.Constants;


public class sreachUserpageObj {
	WebDriver driver;
	Actions act;
	TestBase testbase;

	By userMenu = By.xpath("//span[text()='Users']/../../../..");
	By searchFiels = By.xpath("//input[@placeholder='Search User']");


    public sreachUserpageObj(WebDriver driver) {
		this.driver = driver;
		testbase = new TestBase();
	}

    public void clickonUserMenu()
	{
    	Constants.driver.findElement(userMenu).click();
	}

	public void EnterUserName(String Name) throws InterruptedException {
		Constants.driver.findElement(searchFiels).sendKeys(Name);
		Thread.sleep(4000);
		
	}


	
    

}