package com.cucumber.managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.cucumber.enums.DriverType;
import com.cucumber.enums.Environment;

public class ConfigFileReader {

	private static Properties pro;
	private final String propertyFilePath = System.getProperty("user.dir")+"/src/test/resources/configs/Configuation.properties";

	public ConfigFileReader() {

		try {

			FileInputStream reader = new FileInputStream(propertyFilePath);

			pro = new Properties();

			try {
				pro.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}

	}

	public String getChromePath() {

		String chromePath = pro.getProperty("CHROMEPATH");

		if (chromePath != null) {

			return chromePath;

		} else {

			throw new RuntimeException("chromePath not specified in the Configuration.properties file.");
		}
	}

	public String getGeckoPath() {

		String geckoPath = pro.getProperty("GECKOPATH");

		if (geckoPath != null) {

			return geckoPath;

		} else {

			throw new RuntimeException("getGeckoPath not specified in the Configuration.properties file.");
		}
	}

	public String getEDGEPath() {

		String iePath = pro.getProperty("EDGEPATH");

		if (iePath != null) {

			return iePath;

		} else {

			throw new RuntimeException("edgePath not specified in the Configuration.properties file.");
		}
	}

	public long getImplicitlyWait() {

		String implicitlyWait = pro.getProperty("IMPLICITYWAIT");

		if (implicitlyWait != null) {

			return Long.parseLong(implicitlyWait);
		} else {

			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
		}
	}

	public long getWebdriverWait() {

		String webdriverWait = pro.getProperty("WEBDRIVERWAIT");

		if (webdriverWait != null) {

			return Long.parseLong(webdriverWait);
		} else {

			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
		}
	}
	
	public long getPoolingWait() {

		String webdriverWait = pro.getProperty("POOLINGWAIT");

		if (webdriverWait != null) {

			return Long.parseLong(webdriverWait);
		} else {

			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
		}
	}

	public String getApplicationUrl() {

		String url = pro.getProperty("URL");

		if (url != null) {

			return url;

		} else {
			throw new RuntimeException("url not specified in the Configuration.properties file.");
		}
	}

	public DriverType getBrowser() {			
		String browserNames = pro.getProperty("BROWSER");
		
		if (browserNames == null || browserNames.equals("chrome"))

		{

			return DriverType.CHROME;

		} else if (browserNames == null || browserNames.equalsIgnoreCase("firefox"))

		{
			return DriverType.FIREFOX;

		} else if (browserNames == null || browserNames.equals("edge")) {

			return DriverType.EDGE;

		} else {

			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserNames);
		}
	
		
	
	}

	public Environment getEnvironment() {

		String environmentName = pro.getProperty("ENVIRONMENT");

		if (environmentName == null || environmentName.equalsIgnoreCase("local")) {

			return Environment.LOCAL;

		} else if (environmentName.equals("remote")) {

			return Environment.REMOTE;
		} else {
			throw new RuntimeException(
					"Environment Type Key value in Configuration.properties is not matched : " + environmentName);
		}
	}

	public Boolean getBrowserWindowSize() {

		String windowSize = pro.getProperty("WINDOWMAXIMIZE");

		if (windowSize != null) {

			return Boolean.valueOf(windowSize);
		}

		return true;
	}

	public String getOS() {

		String OS = pro.getProperty("OS");

		if (OS != null) {
			return OS;
		}

		return OS;
	}

	public String getReportConfigPath() {

		String reportConfigPath = pro.getProperty("REPORTCONFIGPATH");

		if (reportConfigPath != null) {

			return reportConfigPath;
		} else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}

	public String getTestDataResourcePath() {

		String testDataResourcePath = pro.getProperty("testDataResourcePath");

		if (testDataResourcePath != null) {

			return testDataResourcePath;
		} else {

			throw new RuntimeException(
					"Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
		}
	}

}