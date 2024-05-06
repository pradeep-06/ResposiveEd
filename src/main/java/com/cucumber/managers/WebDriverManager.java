package com.cucumber.managers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cucumber.enums.DriverType;
import com.cucumber.enums.Environment;
import com.cucumber.utility.Constants;

public class WebDriverManager {

	//private WebDriver driver;
	private static DriverType driverType;
	private static Environment environment;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";
	private static final String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environment = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}
	
	public void launchBrowser(String browser) {

		  // If the browser is Firefox, then do this

		  if(browser.equalsIgnoreCase("firefox")) {
			  Constants.driver = io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().create();  
			  Constants.driver.manage().window().maximize();

		  }else if (browser.equalsIgnoreCase("chrome")) { 
			  
				Constants.driver = io.github.bonigarcia.wdm.WebDriverManager.chromedriver().create();
				  Constants.driver.manage().window().maximize();

		  }else if (browser.equalsIgnoreCase("EDGE")) {
			  Constants.driver = io.github.bonigarcia.wdm.WebDriverManager.chromiumdriver().edgedriver().create();
			  Constants.driver.manage().window().maximize();
			
		  }

		  Constants.driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		  Constants.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		  } 
	
	
	public WebDriver getDriver() {

		if (environment.name().equals("LOCAL")) {

			Constants.driver = createLocalDriver();

		} else if (environment.name().equals("REMOTE")) {

			Constants.driver = createRemoteDriver();
		}
		
		
		return Constants.driver;

	}

	private WebDriver createRemoteDriver() {

		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	public WebDriver createLocalDriver() {

		if (System.getProperty("os.name").contains("Window")) {
			
			if (driverType.name().equals("FIREFOX")) {
				
				System.setProperty(GECKO_DRIVER_PROPERTY, System.getProperty("user.dir")
						+ FileReaderManager.getInstance().getConfigReader().getGeckoPath());
		
				FirefoxOptions options = new FirefoxOptions();
				options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

				Constants.driver = new FirefoxDriver(options);
				
			} else if (driverType.name().equals("CHROME")) {
				
				System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir")
						+ FileReaderManager.getInstance().getConfigReader().getChromePath());
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
				Constants.driver = new ChromeDriver(options);
				
			} else if (driverType.name().equals("EDGE")) {
				
				System.setProperty(EDGE_DRIVER_PROPERTY,
						System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getEDGEPath());
				EdgeOptions options = new EdgeOptions();
				options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
				Constants.driver = new EdgeDriver(options);

			}

			if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())

				Constants.driver.manage().window().maximize();

			Constants.driver.manage().timeouts().implicitlyWait(
					FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
			
			Constants.driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

		}

		else if (System.getProperty("os.name").contains("Mac os")) {

			if (driverType.name().equals("FIREFOX")) {
				
				System.setProperty(GECKO_DRIVER_PROPERTY, System.getProperty("user.dir")
						+ FileReaderManager.getInstance().getConfigReader().getGeckoPath());

				Constants.driver = new FirefoxDriver();
				
			} else if (driverType.name().equals("CHROME")) {
				
				System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir")
						+ FileReaderManager.getInstance().getConfigReader().getChromePath());

				Constants.driver = new ChromeDriver();
				
			} else if (driverType.name().equals("EDGE")) {
				
				System.setProperty(EDGE_DRIVER_PROPERTY,
						System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getEDGEPath());
				EdgeOptions options = new EdgeOptions();
				options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
				Constants.driver = new EdgeDriver(options);
			}

			if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())

				Constants.driver.manage().window().maximize();

			Constants.driver.manage().timeouts().implicitlyWait(
					FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);

			Constants.driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		}

		return Constants.driver;

	}

	
	  public void quitDriver() { 
	  //driver.close();
	  Constants.driver.quit();
	  
	  }
	 

}