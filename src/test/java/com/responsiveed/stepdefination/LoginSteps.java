package com.responsiveed.stepdefination;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.cucumber.PageObjects.LoginPageObjects;
import com.cucumber.managers.WebDriverManager;
import com.cucumber.utility.Constants;
import com.cucumber.utility.TestContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;



public class LoginSteps {

	TestContext testContext;
	LoginPageObjects login;

	public LoginSteps(TestContext context) {
		this.testContext = context;
		login = testContext.getPageObjectManager().getlogin();

	}

	@Given("^Launch the application$")
	public void launch_the_application() throws Throwable {

	}

	@When("^User enters credentials to login$")
	public void user_enters_Credentials_to_LogIn(DataTable usercredentials) throws Throwable {

		for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) {

			System.out.println(data.get("Username") + "     " + data.get("Password"));

			Thread.sleep(2000);

			String username = (String) data.get("Username");
			String password = (String) data.get("Password");

			login.user_name_txtbox(username);
			Thread.sleep(200);

			login.password_txtbox(password);
			login.Login_buton();

		}

	}

	// @When("^User enters Credentials to LogIn$")

	public void user_enters_testuser_and_Test(List<Credentials> usercredentials) throws Throwable {

		for (Credentials credentials : usercredentials) {

			System.out.println(credentials.getUsername() + "     " + credentials.getPassword());

			login.user_name_txtbox(credentials.getUsername());
			login.password_txtbox(credentials.getPassword());
			login.Login_buton();

		}
	}

	@Then("^Message displayed login successfully$")
	public void message_displayed_Login_Successfully() throws Throwable {

		System.out.println("Login_Successfully");

	}

}
