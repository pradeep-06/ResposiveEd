package com.cucumber.TestRunner;

import java.io.IOException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.cucumber.managers.EmailGenerator;
import com.cucumber.managers.RepoterClass;
import com.cucumber.utility.Log;
import com.responsiveed.lib.BasePage;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import modules.CommonBean;
import modules.ExcelWrite;

@CucumberOptions(features = { "src/test/resources/features/Institute.feature"},
					 tags = "@CreateSchoolYear",
					 glue = { "com.responsiveed.stepdefination" }, 
					 plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", }, 
					 //"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					 monochrome = true, 
					 dryRun = false
					 )
public class InsistuteRunner extends AbstractTestNGCucumberTests {

		String testcasename;

		@Parameters({"browser","common.appname"})
		@BeforeClass
		public void beforeclass(String browser,String appname) {

			testcasename = this.getClass().getSimpleName();

			System.out.println("***************** " + testcasename + " Test satrting *****************");
			DOMConfigurator.configure("log4j.xml");
			CommonBean.Appname=appname;
			BasePage.launchBrowser(browser);
			BasePage.PageObectInitialization();
			Log.startTestCase(testcasename);
			
		}
		
		@AfterClass(alwaysRun = true)
		public void testDownClass() throws IOException {
			
			EmailGenerator emailGenerator=new RepoterClass();
			BasePage.quitDriver();
			Log.endTestCase(testcasename);
			BasePage.destroyPageObjects();
			System.out.println("***************** " + testcasename + " Test end *****************");		
			System.out.println(CommonBean.Appname);
			System.out.println("***************Creating excel method********************");
			ExcelWrite.createNewResultSheet(CommonBean.Appname);		
			System.out.println("***************Excel Created sucessfully********************");
			emailGenerator.send();

		}
		
		
	}

