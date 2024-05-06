package modules;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;



import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	public static WebDriver driver;

	
	public static ChromeOptions options = new ChromeOptions();	
	public static PropertyManager prop= new PropertyManager("execution.properties");


	
	public static void launchBrowser(String brower) {
		
		switch (brower.toUpperCase()) {
		case "CHROME":
			Map<String, Object> preference = new HashMap<String, Object>();
			preference.put("profile.default_content_setting_values.notifications", 1);
			options.setExperimentalOption("prefs", preference);
			driver=new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			break;
		case "EDGE":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "SAFARI":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;

		default:
			System.out.println("Mention browser name properly like CHROME, EDGE,FIREFOX,SAFARI");
			System.exit(0);
			break;
		}
		System.out.println(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		
	}
	
	
	
	public static void quitDriver() {
		driver.quit();
	}




	public void OnTestFailure(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		
	}


}
