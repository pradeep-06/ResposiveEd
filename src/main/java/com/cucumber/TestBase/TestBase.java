package com.cucumber.TestBase;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.cucumber.PageObjects.AssessmentPageObjects;
import com.cucumber.PageObjects.LoginPageObjects;
import com.cucumber.managers.FileReaderManager;
import com.cucumber.utility.Constants;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;  
import java.util.concurrent.TimeUnit;  
import java.util.function.Function;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
//import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {
	//	WebDriver driver;
	//	LoginPageObjects login;
	//	AssessmentPageObjects assessment;

	public TestBase() {
		//		this.driver = driver;
		//		DOMConfigurator.configure("log4j.xml");
	}


	public void pageLoadTime(long timeout){
		Constants.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));

	}


	/**
	 * This method will give is fluentWait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(Constants.driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}

	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec){
		Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement waitForElement(WebDriver driver, By elementIdentifier, int timeOutInSeconds, int pollingEveryInMiliSec){
		Wait<WebDriver> wait =
				new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds)).pollingEvery(Duration.ofSeconds(pollingEveryInMiliSec)).ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>()
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(elementIdentifier);
			}});
	}

	public void jsClick(WebDriver driver,WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

	/**
	 * This code will help you to find whether the element is present or not
	 * @param driver
	 * @param xpath
	 * @param time
	 * @return
	 */
	public Boolean isElementPresnt(WebElement ele,int timeOutInSeconds, int pollingEveryInMiliSec)
	{

		for(int i=0;i<timeOutInSeconds;i++)
		{
			try{
				Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
				fwait.until(ExpectedConditions.elementToBeClickable(ele));
				break;
			}catch(Exception e)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e1) 
				{
					System.out.println("Waiting for element to appear on DOM");

				}	
			}
		}
		return true;
	}
public Boolean isElementPresnt(By by,int timeOutInSeconds, int pollingEveryInMiliSec){

		for(int i=0;i<timeOutInSeconds;i++)
		{
			try{
				WebElement ele = Constants.driver.findElement(by);
				Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
				fwait.until(ExpectedConditions.elementToBeClickable(ele));
				break;
			}catch(Exception e)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e1) 
				{
					System.out.println("Waiting for element to appear on DOM");

				}
			//	return false;
			}
		}
		return true;
	}
public void safeActionsClick(WebElement ele,int timeOutInSeconds, int pollingEveryInMiliSec){
		try
		{
			if(isElementPresnt(ele, timeOutInSeconds,pollingEveryInMiliSec))
			{
				Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
				fwait.until(ExpectedConditions.elementToBeClickable(ele));
				System.out.println("driver found the element");
				ele.click();
			}
			else
			{     
				Assert.fail("Element ");
			}
		}
		catch(StaleElementReferenceException e)
		{

			Assert.fail("Element with  - StaleElementReferenceException");
		}
		catch (NoSuchElementException e)
		{
			Assert.fail("Element   was not found in DOM in time  - NoSuchElementException");
		}
		catch(Exception e)
		{
			Assert.fail("Element was not visible on the web page");
		}
	}
public void safeActionsClick(By by, int timeOutInSeconds, int pollingEveryInMiliSec){
		try
		{
			WebElement ele = Constants.driver.findElement(by);
			if(isElementPresnt(ele, timeOutInSeconds,pollingEveryInMiliSec))
			{
				Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
				fwait.until(ExpectedConditions.elementToBeClickable(ele));
				System.out.println("driver found the element");
				ele.click();
			}
			else
			{     
				Assert.fail("Element ");
			}
		}
		catch(StaleElementReferenceException e)
		{

			Assert.fail("Element with  - StaleElementReferenceException");
		}
		catch (NoSuchElementException e)
		{
			Assert.fail("Element   was not found in DOM in time  - NoSuchElementException");
		}
		catch(Exception e)
		{
			Assert.fail("Element was not visible on the web page");
		}
	}
public void safeActionsType(WebElement ele,String text,int timeOutInSeconds, int pollingEveryInMiliSec) throws InterruptedException, IOException{
		try
		{

			if(isElementPresnt(ele,timeOutInSeconds,pollingEveryInMiliSec))
			{
				ele.sendKeys(text);
			}
			else
			{     
				Assert.fail("Element not found ");
			}
		}
		catch(StaleElementReferenceException e)
		{

			Assert.fail("Failed due to StaleElementReferenceException");
		}
		catch (NoSuchElementException e)
		{
			Assert.fail("Element was not found in DOM in time  - NoSuchElementException");
		}
		catch(Exception e)
		{
			Assert.fail("Element was not visible on the web page");
		}
	}


	public boolean isAlertPresent() throws InterruptedException {

		boolean presentFlag = false;

		try {

			// Check the presence of alert
			Alert alert = Constants.driver.switchTo().alert();
			// Alert present; set the flag
			presentFlag = true;
			// if present consume the alert
			alert.accept();
			// ( Now, click on ok or cancel button )

		} catch (NoAlertPresentException ex) {
			// Alert not present
			// ex.printStackTrace();
		}

		return presentFlag;
	}

}
