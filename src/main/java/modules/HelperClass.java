package modules;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.cucumber.managers.EmailGenerator;
import com.cucumber.managers.ExtentReport;

public class HelperClass implements 
						EmailGenerator,
						ExtentReport{
	
	Properties prop;
	InputStream input;
	 ExtentReports extent;
	private  void PropertyManager() {
			 prop = new Properties();
			 try {
				input= getClass().getClassLoader().getResourceAsStream("execution.properties");
				prop.load(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String getProperty(String key) {

			return prop.getProperty(key);
		}
		
	
	/**
	 * This constructor is protected
	 * */
	protected HelperClass() {
		
	}

	@Override
	public void getInstance() {
		 ExtentReports extent = new ExtentReports();
		 PropertyManager();
		 String pathString=prop.getProperty("extent.html.path");
         ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+pathString).viewConfigurer()
         		.viewOrder()
         	    .as(new ViewName[] { 
         		   ViewName.DASHBOARD, 
         		   ViewName.TEST, 
         		   ViewName.CATEGORY, 
//         		   ViewName.AUTHOR, 
//         		   ViewName.DEVICE, 
         		   ViewName.EXCEPTION, 
         		   ViewName.LOG 
         		})
         	  .apply();
         spark.config().setTheme(Theme.DARK);
         spark.config().setReportName(prop.getProperty("extent.html.ReportName"));
         spark.config().setDocumentTitle(prop.getProperty("extent.html.DocumentTitle"));
         spark.config().setProtocol(Protocol.HTTPS);
         extent.attachReporter(spark);
        this.extent=extent;
	
	
	}

	@Override
	public void setCreateTestPass(String test,WebDriver driver) {
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		String base64=scrShot.getScreenshotAs(OutputType.BASE64);
		extent.createTest(test).addScreenCaptureFromBase64String("data:image/png;base64,"+base64)
	     .pass(MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64,"+base64).build());
       
		
	}

	@Override
	public void setCreateTestFail(String test,WebDriver driver) {
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		String base64=scrShot.getScreenshotAs(OutputType.BASE64);
		extent.createTest(test).addScreenCaptureFromBase64String("data:image/png;base64,"+base64)
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64,"+base64).build());
		
	}

	@Override
	public void setCreateTestSkipped(String test) {
		extent.createTest(test).skip("Skkiped");
		
	}

	@Override
	public void flushReport() {
		extent.flush();
		
	}

	@Override
	public void send() {
		PropertyManager();
		if(prop.getProperty("auto.email.to").equalsIgnoreCase("true")) {
			String to=prop.getProperty("auto.email.to");
			String CC=prop.getProperty("auto.email.cc");;
			String subject=prop.getProperty("auto.email.subject");;
			String from=prop.getProperty("auto.email.from");
			String password=prop.getProperty("auto.email.password");
			byte[] decodedBytes = Base64.getDecoder().decode(password);
	        String decodedPassword = new String(decodedBytes);
	        System.out.println("****************Sending Email**********************");
			Email.send(to, from, CC, null, subject, null, null, decodedPassword);
			System.out.println("************Email Sent Sucessfully*****************");
		}else {
			System.out.println("************Email trigger disabled*****************");
		}

	}
	
	

	


}
