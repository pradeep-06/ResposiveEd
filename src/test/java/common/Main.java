package common;

import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Main {

	public static ArrayList<String> brokenlinks= new ArrayList<String>();

	public static void main(String[] args) {
		brokenlinks.add("Advanced Mathematics A Part 2");
		brokenlinks.add("AdvMath.001.B");
		brokenlinks.add("Unit 1-Lesson 1-Page 1");
		brokenlinks.add("https://connect.discoveryeducation.com/index.cfm?&cdPartner=BA34-27GQ&cdUser=26DA-9267&guidAssetID=83fec64b-a47a-479a-8133-c02184c5c73f");
		brokenlinks.add("PASS");
		
		brokenlinks.add("Advanced Mathematics A Part 2");
		brokenlinks.add("AdvMath.001.B");
		brokenlinks.add("Unit 1-Lesson 1-Page 1");
		brokenlinks.add("https://connect.discoveryeducation.com/index.cfm?&cdPartner=BA34-27GQ&cdUser=26DA-9267&guidAssetID=83fec64b-a47a-479a-8133-c02184c5c73f");
		brokenlinks.add("FAIL");
		
		
		
	EdisonBokenLink objBokenLink=new EdisonBokenLink();
		
	String filepath=System.getProperty("user.dir")+"\\src\\test\\resources\\Edison";
	String filename="EdsionBrokenLink";
	objBokenLink.createSheet(filepath, filename);
	 

	}

}
