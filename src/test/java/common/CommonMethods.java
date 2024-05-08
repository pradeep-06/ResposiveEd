package common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonMethods {
	WebDriver driver;
	
	public CommonMethods(WebDriver driver) {
		this.driver=driver;
	}
	public void matSelectdropdown(WebElement element,String visibleText) {
		try {
				element.click();
				List<WebElement> options =element.findElements(By.xpath("following::div[@role='listbox']//mat-option"));
				for(WebElement option:options) {
					String actual = option.findElement(By.xpath("child::span")).getText().trim();
					if(actual.equals(visibleText)) {
						option.click();
//						option.sendKeys(Keys.TAB);
						break;
					}		
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	public void JavascriptScrollIntoView(WebElement element) {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void JavascriptClick(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void scrollToBottom(WebElement elementm) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
}
