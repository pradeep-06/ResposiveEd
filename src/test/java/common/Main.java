package common;

import java.util.Random;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Main {

	
	public static void main(String[] args) {
		
	EdisonBokenLink objBokenLink=new EdisonBokenLink();
	String filepath=System.getProperty("user.dir")+"\\src\\test\\resources\\Edison";
	String filename="EdsionBrokenLink";
	objBokenLink.createSheet(filepath, filename);
	 

	}

}
