package com.responsiveed.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.managers.ExtentReport;
import com.cucumber.managers.RepoterClass;

public class ExtentMain {
	
	public static void main(String[] args) {
		ExtentReport extentReport=new RepoterClass();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://lms-sis-demo.sapphirus.in/");
		
		extentReport.setCreateTestPass("URL lauched", driver);
		
		extentReport.flushReport();
		
		
		
	}

}
