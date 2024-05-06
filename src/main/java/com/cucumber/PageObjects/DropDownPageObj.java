package com.cucumber.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class DropDownPageObj {
	
	@FindBy(xpath = "//mat-label[text()='Institutions:']/../../../..")
	private WebElement institutinsDropDown;
	
	@FindBy(xpath = "//mat-label[text()=\\\"Role's:\\\"]/../../../..")
	private WebElement rolesDropDown;
	
	@FindBy(xpath = "//mat-label[text()='Enrollment Status:']/../../../..")
	private WebElement enrollmentStatusDropDown;
	
	@FindBy(xpath = "//mat-label[text()='Search By:']/../../../..")
	private WebElement searchByDropDown;
	
	@FindBy(xpath = "//span[text()='GO']")
	private WebElement goButton;
	
	@FindBy(xpath = "//span[text()='Sapphirus School']")
	private WebElement insSelect;
	
	@FindBy(xpath = "//span[text()='Administrator']")
	private WebElement RoleSelect;

	@FindBy(xpath = "(//span[text()='All'])[2]")
	private WebElement statusselect;
	
	@FindBy(xpath = "(//span[text()='All'])[3]")
	private WebElement searchBySelect;

	
	public DropDownPageObj(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getInstitutinsDropDown() {
		return institutinsDropDown;
	}

	public WebElement getRolesDropDown() {
		return rolesDropDown;
	}

	public WebElement getEnrollmentStatusDropDown() {
		return enrollmentStatusDropDown;
	}

	public WebElement getSearchByDropDown() {
		return searchByDropDown;
	}

	public WebElement getGoButton() {
		return goButton;
	}

	public WebElement getInsSelect() {
		return insSelect;
	}

	public WebElement getRoleSelect() {
		return RoleSelect;
	}

	public WebElement getStatusselect() {
		return statusselect;
	}

	public WebElement getSearchBySelect() {
		return searchBySelect;
	}
	
	
	
}
