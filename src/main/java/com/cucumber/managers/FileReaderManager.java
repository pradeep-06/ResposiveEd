package com.cucumber.managers;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager;
	private static ConfigFileReader configFileReader;

	public String s;

	/*private FileReaderManager() {
		
		s = "Hello I am a string part of Singleton class"; 

	}*/

	public static FileReaderManager getInstance() {
		
		if(fileReaderManager ==null)
		{
			fileReaderManager = new FileReaderManager(); 
		}

		return fileReaderManager;
	}

	public ConfigFileReader getConfigReader() {

		if (configFileReader == null)
		{
			configFileReader = new ConfigFileReader();
		}
		
		return configFileReader;
	}

}
