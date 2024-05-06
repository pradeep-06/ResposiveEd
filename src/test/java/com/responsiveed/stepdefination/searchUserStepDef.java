package com.responsiveed.stepdefination;

import org.openqa.selenium.By;

import com.cucumber.PageObjects.CreateUserPageObj;
import com.cucumber.PageObjects.LoginPageObjects;
import com.cucumber.PageObjects.allUserManagementDropDwnHandlePageObj;
import com.cucumber.PageObjects.sreachUserpageObj;
import com.cucumber.utility.Constants;
import com.cucumber.utility.JavaUtility;
import com.cucumber.utility.TestContext;

import io.cucumber.java.en.*;



public class searchUserStepDef {
	TestContext testContext;
	LoginPageObjects login;
	CreateUserPageObj user;
	allUserManagementDropDwnHandlePageObj dropDown;
	sreachUserpageObj sreach;
	JavaUtility java;
	
	public searchUserStepDef(TestContext context) {
		System.out.println("Started searchUserStepDef Step Definition ");
		testContext = context;
		login = testContext.getPageObjectManager().getlogin();
		sreach = testContext.getPageObjectManager().getsearchUser();
//		dropDown = testContext.getPageObjectManager().getdropDowns();
		
	}
	@Given("^User on the loginpage$")
	public void user_on_the_loginpage() throws Throwable {
		login.Logintoappln("srinivas.test", "Welcome1!");
		Thread.sleep(2000);
	}

	@Then("^User Click on the UserMenu$")
	public void user_Click_on_the_UserMenu() throws Throwable {
	sreach.clickonUserMenu();
	Thread.sleep(2000);
	}

	@Then("^User selected All Drop Down Values$")
	public void user_selected_All_Drop_Down_Values() throws Throwable {
		dropDown = testContext.getPageObjectManager().getdropDowns();
		dropDown.SelectinstitutinsDropDown();
		Thread.sleep(2000);
		dropDown.SelectrolesDropDown();
		Thread.sleep(2000);
		dropDown.SelectenrollmentStatusDropDown();
		Thread.sleep(2000);
		dropDown.SlectsearchByDropDown(); 
		Thread.sleep(2000);
		
	}

	@Then("^User Clicks GO button$")
	public void user_Clicks_GO_button() throws Throwable {
		dropDown.ClickONGOBUTTON(); 
	}

	@Then("^User Enter User Nane And search$")
	public void user_Enter_User_Nane_And_search() throws Throwable {
	    sreach.EnterUserName("srinivas07");
	    Thread.sleep(2000);
//	    java.pagedown();
//	    Thread.sleep(4000);
//	    sreach.EnterUserName("srinivas07");
	    Thread.sleep(4000);
	    login.logoutApp();
	}

	
}
