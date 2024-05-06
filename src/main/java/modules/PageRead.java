package modules;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;


@SuppressWarnings("unused")
public class PageRead {

	PropertyManager prop = new PropertyManager("execution.properties");
	
	public CommonBean commonBean;
	
	public static ArrayList<String> scename = new ArrayList<String>();
	
	public void storeAcutalResult() {
	try {
			
		String scenarioname=CommonBean.scenarioName;
		Integer passcount=CommonBean.policyLevelPassedCntr;
		Integer failcount=CommonBean.policyLevelFailedCntr;
		
		Integer validationpoints= CommonBean.policyLevelPassedCntr+ CommonBean.policyLevelFailedCntr;
		System.out.println(CommonBean.scenarioName);
		System.out.println(CommonBean.validationpoints);
		System.out.println(CommonBean.policyLevelFailedCntr);
		System.out.println(CommonBean.policyLevelPassedCntr);
		
		
		CommonBean.collection.add(1, CommonBean.scenarioName);
		Iterator<String> it = CommonBean.scename.iterator();
		
		int i = CommonBean.scename.size();
		System.out.println(i);
		(CommonBean.scename).add(CommonBean.scenarioName);
		System.out.println(CommonBean.scename);
		System.out.println(i);
		System.out.println((CommonBean.scename).get(1));
		System.out.println(CommonBean.scename);
		
		CommonBean.totalvalidation = new ArrayList<String>();
		CommonBean.totalvalidation.add(Integer.toString(validationpoints));
		System.out.println(CommonBean.totalvalidation);
		CommonBean.passcountarray = new ArrayList<String>();
		CommonBean.passcountarray.add(Integer.toString(passcount));
		CommonBean.failcountarray = new ArrayList<String>();
		CommonBean.failcountarray.add(Integer.toString(failcount));
		
	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void storeAllExpectedResultCombinedUI() {
		
		System.out.println( "Testcount : "+CommonBean.testcasecountUI);
		System.out.println( "ScenarioName : "+CommonBean.scenarioName);
		System.out.println( "Validation points : "+CommonBean.validationpoints);
		System.out.println( "Pass Count : "+CommonBean.policyLevelPassedCntr);
		System.out.println( "Fail Count : "+(CommonBean.validationpoints-CommonBean.policyLevelPassedCntr));
		System.out.println( "App Name : "+CommonBean.Appname);
		
		CommonBean.policyLevelFailedCntr=CommonBean.validationpoints-CommonBean.policyLevelPassedCntr;
		CommonBean.collection.add(String.valueOf(CommonBean.scenarioName));
		CommonBean.collection.add(String.valueOf(CommonBean.validationpoints));
		CommonBean.collection.add(String.valueOf(CommonBean.policyLevelPassedCntr));
		CommonBean.collection.add(String.valueOf(CommonBean.policyLevelFailedCntr));
		
	}
	
public void storeAllExpectedResultComninedAPI() {
		
		System.out.println( "Testcount : "+CommonBean.testcasecountAPI);
		System.out.println( "ScenarioName : "+CommonBean.scenarioName);
		System.out.println( "Validation points : "+CommonBean.validationpoints);
		System.out.println( "Pass Count : "+CommonBean.policyLevelPassedCntr);
		System.out.println( "Fail Count : "+(CommonBean.validationpoints-CommonBean.policyLevelPassedCntr));
		System.out.println( "App Name : "+CommonBean.Appname);
		
		CommonBean.policyLevelFailedCntr=CommonBean.validationpoints-CommonBean.policyLevelPassedCntr;
		CommonBean.collectionAPI.add(String.valueOf(CommonBean.scenarioName));
		CommonBean.collectionAPI.add(String.valueOf(CommonBean.validationpoints));
		CommonBean.collectionAPI.add(String.valueOf(CommonBean.policyLevelPassedCntr));
		CommonBean.collectionAPI.add(String.valueOf(CommonBean.policyLevelFailedCntr));
		
	}
	
public void writeClasstoJson(String Appname) {
	
	System.out.println("Write in Json");
	FileWriter fileWriter;
	String stringJsonString="[";
	header hd = new header();
	
	System.out.println(stringJsonString);
	for(int i=0;i<=CommonBean.collection.size();i=i+4) {
		
		CommonBean.resultcollect.put("ScenarioName", CommonBean.collection.get(i));
		CommonBean.resultcollect.put("Testcasename", CommonBean.collection.get(i+1));
		CommonBean.resultcollect.put("Passcount", CommonBean.collection.get(i+2));
		CommonBean.resultcollect.put("Failcount", CommonBean.collection.get(i+3));
		
	   System.out.println("Array to put in json "+CommonBean.resultcollect);
		
	    stringJsonString = stringJsonString+new Gson().toJson(CommonBean.resultcollect)+";"+"]";
	   System.out.println("Printing the array collection in Json \n"+stringJsonString);
	   
	   String jsonpath=prop.getProperty("jsonStorage")+"/"+Appname+".json";
	   System.out.println(jsonpath);
	   try {
		boolean append =true;
		fileWriter= new FileWriter(jsonpath,append);
		Gson gson= new GsonBuilder().setPrettyPrinting().create();
		System.out.println("Create Json");
		JsonElement jsonElement = gson.fromJson(stringJsonString, JsonElement.class);
		fileWriter.write(gson.toJson(jsonElement));
		fileWriter.close();
	   } catch (Exception e) {
		e.printStackTrace();
		System.out.println("!!! Expection !!! : Error while writing into JSON");
	   }
	   
	   System.out.println("***********************************************");
	   System.out.println(Appname+" COMPLETED");
	   System.out.println("***********************************************");
	   
	}
	
	
}

private class header {
	
	private String policyNo;
	private String issueState;
	private ArrayList<String> scenarioNamearray;
	private ArrayList<String> totalVaidationarray;
	private ArrayList<String> passedCountarray;
	private ArrayList<String> failedCountarray;
	
	private int totalValidation;
	private int passedCount;
	private int failedCount;
	private String  scenarioName;
	private String  ComparisionStatus;
	
	public header() {
		this.setscenarioName(CommonBean.scenarioName);
		this.setTotalValidation(CommonBean.policyLevelPassedCntr+CommonBean.policyLevelFailedCntr);
		this.setPassedCount(CommonBean.policyLevelPassedCntr);
		this.failedCount=CommonBean.policyLevelFailedCntr;
		if(failedCount>0) 
			this.setComparisionStatus("FAILED");
			else {
				this.setComparisionStatus("PASSED");
			}
		
		
		
	}
	
	

	public  String setscenarioName(String scenarioName) {
		return scenarioName=scenarioName;
		
	}
	
	public String getscenarioName() {
		return scenarioName;
	}
	
	public ArrayList<String> getscanrioNamearray(){
		return scenarioNamearray;
	}
	
	public String getPolicyNo() {
		return policyNo;
	}
	
	public void setPolicyNo(String policyNo) {
		this.policyNo=policyNo;
	}
	
	public String getIssueState() {
		return issueState;
	}
	
	public void setIssueState(String issueState) {
		this.issueState=issueState;
	}

	public int getTotalValidation() {
		return totalValidation;
	}
	
	public void setTotalValidation(int totalValidation) {
		this.totalValidation=totalValidation;
	}
	
	public int getPassedCount() {
		return passedCount;
	}
	
	public void setPassedCount(int passedCount) {
		this.passedCount=passedCount;
	}
	
	
	public String getComparisionStatus() {
		return ComparisionStatus;
	}
	
	public void setComparisionStatus(String ComparisionStatus) {
		this.ComparisionStatus=ComparisionStatus;
	}
	
}
}