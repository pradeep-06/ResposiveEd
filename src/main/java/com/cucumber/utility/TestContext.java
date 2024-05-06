package com.cucumber.utility;

import com.cucumber.managers.PageObjectManager;
import com.cucumber.managers.WebDriverManager;

public class TestContext {

	
	private WebDriverManager webDriverManager = null;
	private PageObjectManager pageObjectManager = null;
	//public ScenarioContext scenarioContext;

	public TestContext() {		
        System.gc();
//		webDriverManager = new WebDriverManager();
		pageObjectManager = new PageObjectManager();
		//scenarioContext = new ScenarioContext();
	}

	public WebDriverManager getWebDriverManager() {

		return webDriverManager;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	


	/*public ScenarioContext getScenarioContext() {
		
		return scenarioContext;
	}*/

}