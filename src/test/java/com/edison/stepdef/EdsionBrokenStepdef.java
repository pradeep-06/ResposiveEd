package com.edison.stepdef;

import java.net.SocketException;

import com.edison.lib.BasePage;

import io.cucumber.java.en.*;
import modules.*;

public class EdsionBrokenStepdef extends BasePage{

	
@When("select the (string) in the control panel")
public void select_the(String option) throws Throwable { 	
		objCommonElements.clickOnItems(CommonBean.data.get(option));
}
	
	
@When("select and enter the dropdown values {string} {string} {string} {string} in course template")
public void select_the_dropdown_values(String Institution, String SearchBy,String SearchByName, String ActiveInactive) {
	  CommonBean.validationpoints+=3;
	  objEdsionBrokenlinkPage.SelectInstitution(CommonBean.data.get(Institution));
	  objEdsionBrokenlinkPage.SearchBy(CommonBean.data.get(SearchBy), CommonBean.data.get(SearchByName));
	  objEdsionBrokenlinkPage.selectActiveInactive(CommonBean.data.get(ActiveInactive)); 
}
@Then("Verify the record in course template table {string} {string} {string} {string} and click on name")
public void verify_the_filter_record_in_course_template_table(String Code, String Name, String Abbreviation, String Active_Inactive) throws SocketException {
   CommonBean.validationpoints++;
   objEdsionBrokenlinkPage.verifyCourseRecord(CommonBean.data.get(Code), CommonBean.data.get(Name), CommonBean.data.get(Abbreviation), CommonBean.data.get(Active_Inactive), "NameLink");
}
	
@And("Change the driver Focus to new window")
public void changeDriverFocus() {
	objEdsionBrokenlinkPage.changeDriverFocus();
}

@And("verify the broken links in each page {string} {string}")
public void verifyBrokenLinks(String name,String code) throws SocketException {
	objEdsionBrokenlinkPage.verifyBrokenLinks(CommonBean.data.get(name),CommonBean.data.get(code));
}
@And("Write the broken links in excel file {string} {string}")
public void writeBrokenToExcel(String filename,String filepath) {
	objBokenLink.createSheet(filepath, filename);
}
	

}