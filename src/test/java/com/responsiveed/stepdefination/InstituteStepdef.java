package com.responsiveed.stepdefination;




import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.responsiveed.lib.BasePage;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import modules.BaseClass;
import modules.CommonBean;

public class InstituteStepdef extends BasePage{
	

	@When("the user navigates to the {string} screen")
	public void the_user_navigates_to_the_screen(String screen) throws Throwable { 
		
		objCommonElements.clickOnMenuItem(CommonBean.data.get(screen));
	}

	@When("user enter and select the {string} {string} {string}")
	public void user_enter_and_select_the(String Product, String Name, String Status) throws Throwable {
	  CommonBean.validationpoints+=3;
	  objInsistitutePage.selectProduct(CommonBean.data.get(Product));
	  objInsistitutePage.setName(CommonBean.data.get(Name));
	  objInsistitutePage.selectStatus(CommonBean.data.get(Status));
	}


	@Then("Verify the filter record in institutions table {string} {string} {string} {string} {string}")
	public void verify_the_filter_record_in_institutions_table(String Name, String Status, String System, String isConsortium, String action) throws Throwable {
	
		
		objInsistitutePage.inisitutetable(CommonBean.data.get(Name), CommonBean.data.get(Status), CommonBean.data.get(System), CommonBean.data.get(isConsortium), CommonBean.data.get(action));
	}

	@Then("value should not show in the Insistitution dropdown {string} in UserManagement")
	public void value_should_not_show_in_the_Insistitution_dropdown_in_UserManagement(String Name) throws Throwable {
		objCommonElements.clickOnMenuItem("Users");
		objInsistitutePage.verifyInActiveUserManagement(CommonBean.data.get(Name));
	
	}

	@Then("value should not show in the Insistitution dropdown {string} in Teacher")
	public void value_should_not_show_in_the_Insistitution_dropdown_in_Teacher(String name) throws Throwable {
		objCommonElements.clickOnMenuItem("Teachers");
		objInsistitutePage.verifyInActiveUserManagement(CommonBean.data.get(name));
	 
	}

	@Then("value should not show in the Insistitution dropdown {string} in Advisor")
	public void value_should_not_show_in_the_Insistitution_dropdown_in_Advisor(String name) throws Throwable {
		objCommonElements.clickOnMenuItem("Advisors");
		objInsistitutePage.verifyInActiveUserManagement(CommonBean.data.get(name));
	}

	@Then("value should not show in the Insistitution dropdown {string} in Student Application")
	public void value_should_not_show_in_the_Insistitution_dropdown_in_Student_Application(String name) throws Throwable {
		
		objCommonElements.clickOnMenuItem("Student Application");
		objInsistitutePage.verifyInActiveUserManagement(CommonBean.data.get(name));
		
	}
	
	

	

}
