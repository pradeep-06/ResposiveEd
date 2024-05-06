package com.cucumber.PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.TestBase.TestBase;
import com.cucumber.utility.Constants;
import com.cucumber.utility.Log;

public class LoginPageObjects {

	WebDriver driver;
	Actions act;
	TestBase testbase;

	By userName = By.xpath("//input[@formcontrolname='uname']");
	By passWord = By.xpath("//input[@formcontrolname='password']");
	By loginButton = By.xpath("//span[text()=' Sign In ']");

	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		testbase = new TestBase();
	}

	public void user_name_txtbox(String username) {
		Log.info("Explicity wait applied on the driver for 60 seconds");
		Constants.driver.manage().window().maximize();
		Constants.driver.findElement(userName).sendKeys(username);
		Log.info("user_name is found on the Home page");

	}

	public void password_txtbox(String password) {
		Log.info("Explicity wait applied on the driver for 60 seconds");
		Constants.driver.findElement(passWord).sendKeys(password);
		Log.info("password_txtbox is found on the Home page");
	}

	public void Login_buton() throws InterruptedException {
		Log.info("Explicity wait applied on the driver for 60 seconds");
		Constants.driver.findElement(loginButton).click();
		Log.info("password_txtbox is found on the Home page");
		Thread.sleep(8000);
		try {
			Alert alt = Constants.driver.switchTo().alert();
			alt.accept();

		} catch (Exception e) {
		}
	}

	public void Logintoappln(String UserName, String Password) throws InterruptedException {

		user_name_txtbox(UserName);
		password_txtbox(Password);
		Login_buton();

	}
	public void logoutApp() throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@class=\"mat-mdc-button-touch-target\"])[5]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		System.out.println("logout done successfully");
	}

}
