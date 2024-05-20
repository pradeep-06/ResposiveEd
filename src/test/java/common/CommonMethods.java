package common;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class CommonMethods {
	WebDriver driver;
	
	public CommonMethods(WebDriver driver) {
		this.driver=driver;
	}
	public void matSelectdropdown(WebElement element,String visibleText) {
		try {
				Wait.elementToBeClickable(element);
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
				SoftAssert soft =new SoftAssert();
				soft.fail();
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
	
	public void switchTOWindow(String currentWindowHanlde) {
		
	        Set<String> allWindowHandles = driver.getWindowHandles();
	        Iterator<String> iterator = allWindowHandles.iterator();
	        // Here we will check if child window has other child windows and will fetch the heading of the child window
	        while (iterator.hasNext()) {
	            String ChildWindow = iterator.next();
	                if (!currentWindowHanlde.equalsIgnoreCase(ChildWindow)) {
	                driver.switchTo().window(ChildWindow);
	            }
	        }
	}
	
	public void switchTOWindow(String currentWindowHanlde,int index) {	
        Set<String> allWindowHandles = driver.getWindowHandles();
        ArrayList<String>handles=new ArrayList<String>(allWindowHandles);
        driver.switchTo().window(handles.get(index));  
	}
	
	public String generateRandomString (String type, int length) {
		String candidateChars=null;
		StringBuilder sb = new StringBuilder ();
		Random random = new Random ();
		switch (type.toLowerCase()) {
		case "word":
			candidateChars="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for (int i = 0; i < length; i ++) {
	            sb.append (candidateChars.charAt (random.nextInt (candidateChars
	                    .length ())));
	        }
			break;
		case "number":
			candidateChars="0123456789";
			for (int i = 0; i < length; i ++) {
	            sb.append (candidateChars.charAt (random.nextInt (candidateChars
	                    .length ())));
	        }
			break;
		default:
			System.out.println("Mention type properly");
			break;
		}
		return sb.toString();
		
	}
	
}
