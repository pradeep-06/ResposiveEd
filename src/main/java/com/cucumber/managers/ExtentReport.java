/**These code was written by Benedict Pradeep SS195*/
package com.cucumber.managers;

import org.openqa.selenium.WebDriver;



public interface ExtentReport {
	
	
	/***
	 * 
	 * This method will configure and setup the extent report
	 * */
	public void getInstance();
	
	/***
	 * 
	 * This method will work when test case is passed with screenshot attachment
	 * */	
	public void setCreateTestPass(String test,WebDriver driver);
	
	/***
	 * 
	 * This method will work when test case is failed with screenshot attachment
	 * */	
	public void setCreateTestFail(String test, WebDriver driver);
	
	/***
	 * 
	 * This method will work when test case is skipped with screenshot attachment
	 * */	
	public void setCreateTestSkipped(String test);
	
	
	
	public void flushReport();
	

}
