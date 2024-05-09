package com.responsiveed.pages;

import java.util.List;

import org.bouncycastle.oer.its.ieee1609dot2.SequenceOfRecipientInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import modules.CommonBean;

public class CommonElements {
	WebDriver driver;
	
	@FindBy(how = How.XPATH,using = "//*[text()='GO']//parent::button")private WebElement goElement;
	@FindAll({
				@FindBy (how = How.XPATH,using = "//div[contains(@class,'focus-indicator')]//parent::*")
		     }) private List<WebElement> menu;
	@FindBy(how = How.XPATH,using = "//button[text()='Yes']")private WebElement yesElement;
	@FindBy(how = How.XPATH,using = "(//button[text()='No'])[2]")private WebElement noElement;
	@FindBy(how = How.XPATH,using = "//button[text()='Ok']")private WebElement okElement;
	@FindBy(how=How.XPATH,using = "")private WebElement creatElement;
	@FindBy(how=How.XPATH,using = "")private WebElement cancelElement;
	public CommonElements(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMenuItem(String screen) {
	 try {
			Thread.sleep(3000);
			CommonBean.validationpoints++;
			for(WebElement element:menu) {
				String actualScreen=element.findElement(By.xpath("child::span//span[@class='hide-menu']")).getText().trim();
				if(actualScreen.equalsIgnoreCase(screen)) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					element.click();
					CommonBean.policyLevelPassedCntr++;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void clickOnConfirmationbutton(String buttonname) {
		if(buttonname.equalsIgnoreCase("yes")) {
			yesElement.click();
		}
		if(buttonname.equalsIgnoreCase("no")) {
			noElement.click();
		}
		if(buttonname.equalsIgnoreCase("ok")) {
			okElement.click();
		}
	}
	
	public void clickGobutton() {
		goElement.click();
	}
}
