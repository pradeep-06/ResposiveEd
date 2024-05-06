package com.responsiveed.stepdefination;
//package com.cucumber.stepdefination;
//
//import com.cucumber.utility.TestContext;
//
//public class CreateAppQuestionSteps {
////	WebDriver driver;
//	TestContext testContext;
//	LoginPageObjects login;
//     CreateAppQuestionPageObjects caqp;
//     private final int TIMEOUT_WAIT = 30;
//	private final int POOLING_WAIT = 8000;
// 
// 
//	public CreateAppQuestionSteps(TestContext context) 
//	{
//		System.out.println("Started CreatQuestion Step Definition ");
//		testContext = context;
//		login = testContext.getPageObjectManager().getlogin();
//		caqp = testContext.getPageObjectManager().getCreateQuestion();
//
// 
//	}
//	@Given("^LoginTo the Application$")
//	public void loginto_the_Application() throws Throwable {
//		login.Logintoappln("pakkirareddy.y", "Pakkira@123");
//	}
//	@Given("^Click by Question$")
//	public void Click_by_Question() throws Throwable
//	{
//		caqp.clickonAppQuestion();
//	}
// 
//	@Given("^Click on the GO and Create Button$")
//	public void click_on_the_GO_and_Create_Button() throws Throwable 
//	{
//	    caqp.ClickonGoandCraeteBtn();
//	}
// 
//	@When("^Enter Question in Enter Question input field$")
//	public void enter_Question_in_Enter_Question_input_field() throws Throwable
//	{
//		caqp.EnterQuestion();
//	}
// 
//	@When("^Select Question and Enter answer fields$")
//	public void select_Question_and_Enter_answer_fields() throws Throwable 
//	{
//	 caqp.SelectTypeandEnterAnswers();
//	}
// 
//	@Then("^check whether Question Created$")
//	public void check_whether_Question_Created() throws Throwable {
//		System.out.println("Question Created Succefully!!!");
//	}
// 
// 
//}