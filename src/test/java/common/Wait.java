package common;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class Wait {
	
	WebDriver driver;
	static Actions actions;
	static FluentWait<WebDriver> wait1;
	
	@SuppressWarnings("unchecked")
	public Wait(WebDriver driver) {
		this.driver=driver;
		actions = new Actions(this.driver);
		wait1 = new FluentWait(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class);
		
	}
	
	public static void elementToBeClickable(WebElement element) {
		wait1.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void 	visibilityOf(WebElement element) {
		wait1.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void elementToBeSelected(WebElement element) {
		wait1.until(ExpectedConditions.elementToBeSelected(element));
	}
	
	public static void invisibilityOf(WebElement element) {
		wait1.until(ExpectedConditions.invisibilityOf(element));
	}
//	elementToBeSelected()
//	frameToBeAvaliableAndSwitchToIt()
//	invisibilityOfTheElementLocated()
//	invisibilityOfElementWithText()
//	presenceOfAllElementsLocatedBy()
//	presenceOfElementLocated()
//	textToBePresentInElement()
//	textToBePresentInElementLocated()
//	textToBePresentInElementValue()
//	titleIs()
//	titleContains()
//	visibilityOf()
//	visibilityOfAllElements()
//	visibilityOfAllElementsLocatedBy()
//	visibilityOfElementLocated()
	

}
