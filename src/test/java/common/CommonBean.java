package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.restassured.response.Response;

public class CommonBean {

	public static LinkedHashMap<String, LinkedHashMap<String, String>> xnbMap;
	public static String PDFFilePath;

	public static HashMap<String, String> data=new HashMap<String, String>();
	public static int policyLevelPassedCntr;
	public static int policyLevelFailedCntr;
	public static int validationpoints;
	public static List<Map<String, String>> resultList;
	public static String Scenarioname;
	public static LinkedHashMap<String, String> resultcollect;
	public static ArrayList<String> collection=new ArrayList<String>();
	public static ArrayList<String> collectionAPI=new ArrayList<String>();
	public static LinkedHashMap<String, LinkedHashMap<String, String>> list;
	public static String Appname;
	public static int TotalpassUI=0;
	public static int TotalfailUI=0;
	public static int TotalpassAPI=0;
	public static int TotalfailAPI=0;
	public static int testcasecountUI=0;
	public static int testcasecountAPI=0;
	public static String scenarioName;
	public static ArrayList<String> scename;
	public static ArrayList<String> totalvalidation;
	public static ArrayList<String> passcountarray;
	public static ArrayList<String> failcountarray;

	public static HashMap<String, ArrayList<String>> JsonArrayfile;
	/**API Global Variables**/
	public static HashMap<String, String> queryParams=new HashMap<String, String>();
	public static Response response;
	public static Response Response;
	public static String URL;
	public static HashMap<String, String> Apiheaders;
	public static float responcetime;
	public static List<Integer> postKey=new ArrayList<Integer>();
	public static String putKey;

}