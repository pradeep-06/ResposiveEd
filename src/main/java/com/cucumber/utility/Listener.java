package com.cucumber.utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;

import org.testng.IInvokedMethodListener;

import org.testng.ISuite;

import org.testng.ISuiteListener;

import org.testng.ITestContext;

import org.testng.ITestListener;

import org.testng.ITestNGMethod;

import org.testng.ITestResult;

import org.testng.Reporter;

import modules.BaseClass;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener  {

	/*
	 * What is Listeners in Selenium WebDriver? 
	 * Listener is defined as interface that modifes the default TestNG's behavior. As the name suggests Listeners
	 * "listen" to the event defined in the selenium script and behave accordingly.
	 * It is used in selenium by implementing Listeners Interface. It allows
	 * customizing TestNG reports or logs. There are many types of TestNG listeners
	 * available
	 * 
	 * Types of Listeners in TestNG There are many types of listeners which allows
	 * you to change the TestNG's behavior.
	 * 
	 * Below are the few TestNG listeners:
	 * 
	 * IAnnotationTransformer , IAnnotationTransformer2 , IConfigurable ,
	 * IConfigurationListener , IExecutionListener, IHookable ,
	 * IInvokedMethodListener , IInvokedMethodListener2 , IMethodInterceptor ,
	 * IReporter, ISuiteListener, ITestListener . Above Interface are called TestNG
	 * Listeners. These interfaces are used in selenium to generate logs or
	 * customize the Testing reports.
	 */

	// This belongs to ISuiteListener and will execute before the Suite start
	
	

	public void onStart(ISuite isuitestart) {

		Reporter.log("About to begin executing Suite " + isuitestart.getName(), true);

	}

	// This belongs to ISuiteListener and will execute, once the Suite is finished

	public void onFinish(ISuite isuiteend) {

		Reporter.log("About to end executing Suite " + isuiteend.getName(), true);

	}
	

	// This belongs to ITestListener and will execute before starting of Test
	// set/batch

	public void onStart(ITestContext iteststart) {

		Reporter.log("About to begin executing Test " + iteststart.getName(), true);

	}

	// This belongs to ITestListener and will execute, once the Test set/batch is
	// finished

	public void onFinish(ITestContext itestend) {

		Reporter.log("Completed executing test " + itestend.getName(), true);

	}

	// This belongs to ITestListener and will execute only when the test is pass

	public void onTestSuccess(ITestResult itestpass) {

		// This is calling the printTestResults method

		printTestResults(itestpass);

	}

	// This belongs to ITestListener and will execute only on the event of fail test

	public void onTestFailure(ITestResult itestfail) {

		// This is calling the printTestResults method

//		printTestResults(itestfail);	
//		extent = new ExtentReports();
//		logger = extent.createTest(itestfail.getName());
//		String screenshotPath = captureScreenshot(itestfail.getName());
//        logger.log(Status.FAIL, "Test Case Failed: " + itestfail.getName());
//        logger.addScreenCaptureFromPath(screenshotPath);

	}

	// This belongs to ITestListener and will execute before the main test start
	// (@Test)

	public void onTestStart(ITestResult mainiteststart) {

		System.out.println("The execution of the main test starts now");

	}

	// This belongs to ITestListener and will execute only if any of the main
	// test(@Test) get skipped

	public void onTestSkipped(ITestResult mainitestskiped) {

		printTestResults(mainitestskiped);

	}

	// This is just a piece of shit, ignore this

	public void onTestFailedButWithinSuccessPercentage(ITestResult successpercentage) {
		
		System.out.println(successpercentage);

	}

	// This is the method which will be executed in case of test pass or fail

	// This will provide the information on the test
	
//	private String captureScreenshot(String screenshotName) {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        String destination = "Screenshots/" + screenshotName + ".png";
//        try {
//            File source = ts.getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(source, new File(destination));
//        } catch (Exception e) {
//            System.out.println("Exception while taking screenshot: " + e.getMessage());
//        }
//        return destination;
//    }


	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

			Reporter.log("Test Method had the following parameters : " + params, true);

		}

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("Test Status: " + status, true);

	}

	// This belongs to IInvokedMethodListener and will execute before every method
	// including @Before @After @Test

	public void beforeInvocation(IInvokedMethod methodname, ITestResult itestresults) {

		String textMsg = "About to begin executing following method : " + returnMethodName(methodname.getTestMethod());

		Reporter.log(textMsg, true);

	}

	// This belongs to IInvokedMethodListener and will execute after every method
	// including @Before @After @Test

	public void afterInvocation(IInvokedMethod arg1, ITestResult arg2) {

		String textMsg = "Completed executing following method : " + returnMethodName(arg1.getTestMethod());

		Reporter.log(textMsg, true);

	}

	// This will return method names to the calling function

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

	public void OnTestFailure(ITestResult iTestResult) {

	
	}

}
