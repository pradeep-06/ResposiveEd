package com.responsiveed.stepdefination;

import org.openqa.selenium.By;

import com.cucumber.PageObjects.CreateUserPageObj;
import com.cucumber.PageObjects.LoginPageObjects;
import com.cucumber.PageObjects.allUserManagementDropDwnHandlePageObj;
import com.cucumber.utility.Constants;
import com.cucumber.utility.TestContext;

import io.cucumber.java.en.*;


public class UserManagementDropDwnHandStepDef {
	TestContext testContext;
	LoginPageObjects login;
	CreateUserPageObj user;
	allUserManagementDropDwnHandlePageObj dropDown;
	
	public UserManagementDropDwnHandStepDef(TestContext context) {
		System.out.println("Started UserManagementDropDwnHandStepDef Step Definition ");
		testContext = context;
		login = testContext.getPageObjectManager().getlogin();
//		user = testContext.getPageObjectManager().getcreateUser();
		dropDown = testContext.getPageObjectManager().getdropDowns();
		
	}
	@Given("^login to the APP$")
	public void login_to_the_APP() throws Throwable {
		login.Logintoappln("srinivas.test", "Welcome1!");
		Thread.sleep(2000);
	}

	@Then("^Click on the USERMENU$")
	public void click_on_the_USERMENU() throws Throwable {
		Thread.sleep(2000);
		Constants.driver.findElement(By.xpath("//span[text()='Users']/../../../..")).click();
		Thread.sleep(2000);
	}

	@Then("^Select institutinsDropDown$")
	public void select_institutinsDropDown() throws Throwable {
		dropDown.SelectinstitutinsDropDown();
		Thread.sleep(2000);
	}

	@Then("^Select rolesDropDown$")
	public void select_rolesDropDown() throws Throwable {
		dropDown.SelectrolesDropDown();
		Thread.sleep(2000);
	}

	@Then("^Select enrollmentStatusDropDown$")
	public void select_enrollmentStatusDropDown() throws Throwable {
		dropDown.SelectenrollmentStatusDropDown();
		Thread.sleep(2000);
	}

	@Then("^Slect searchByDropDown$")
	public void slect_searchByDropDown() throws Throwable {
		dropDown.SlectsearchByDropDown(); 
		Thread.sleep(2000);
	}





	
}
