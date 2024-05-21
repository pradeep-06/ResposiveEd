package com.edison.pages;

import java.net.SocketException;
import java.util.List;

import org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi.ecCVCDSA;
import org.bouncycastle.oer.its.ieee1609dot2.SequenceOfRecipientInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import common.Wait;
import modules.CommonBean;

public class CommonElements {
	WebDriver driver;
	Actions actions;
	
	@FindBy(how = How.XPATH,using = "//*[@title='Control Panel']") private WebElement ControlpanelElement;
	@FindAll({
		@FindBy(how=How.XPATH,using="//form[@name='controlPanelForm']//descendant::a")
	})private List<WebElement> items;
	
	
	@FindBy(how = How.ID,using = "siteTemplateGoButton") private WebElement goElement;
	
	public CommonElements(WebDriver driver) {
		this.driver=driver;
		actions=new Actions(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickoncontrolpanel() {
		Wait.elementToBeClickable(ControlpanelElement);
		ControlpanelElement.click();
		CommonBean.policyLevelPassedCntr++;
	}
	public void clickGobutton() throws SocketException {
	 Wait.elementToBeClickable(goElement);	
	 goElement.click();
		CommonBean.policyLevelPassedCntr++;
	}
	
	public void clickOnItems(String option) throws SocketException {
		 try {
				Thread.sleep(3000);
				for(WebElement element:items) {
					String actualoption=element.getText().trim();
					if(actualoption.equalsIgnoreCase(option)) {
						WebElement scrollElement=element.findElement(By.xpath("ancestor::tr[1]"));
						actions.scrollToElement(scrollElement);
						Thread.sleep(1000);
						element.click();
						Thread.sleep(1000);
						CommonBean.policyLevelPassedCntr++;
						break;
					}
				}
			}catch (Exception e) {
				 SoftAssert assert1 =new SoftAssert();
				 assert1.fail();
				e.printStackTrace();
			}
			
		}
	
	
}
