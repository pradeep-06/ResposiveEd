package com.responsiveed.lib;


import com.responsiveed.pages.CommonElements;
import com.responsiveed.pages.InsistitutePage;
import com.responsiveed.pages.LoginPage;
import com.responsiveed.pages.SchoolYearPage;

import modules.BaseClass;


public class BasePage extends BaseClass{
	
	
	public static LoginPage objLoginpage=null;
	public static InsistitutePage objInsistitutePage=null;
	public static CommonElements objCommonElements=null;
	public static SchoolYearPage objSchoolYearPage=null;
	
	

	public static void PageObectInitialization() {
		objLoginpage = new LoginPage(driver);
		objCommonElements= new CommonElements(driver);
		objInsistitutePage = new InsistitutePage(driver);
		objSchoolYearPage=new SchoolYearPage(driver);
	}
	
	public static void destroyPageObjects() {
		objLoginpage=null;
		objCommonElements=null;
		objInsistitutePage=null;
	}

}
