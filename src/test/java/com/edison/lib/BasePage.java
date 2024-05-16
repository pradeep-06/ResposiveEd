package com.edison.lib;


import com.edison.pages.CommonElements;
import com.edison.pages.EdsionBrokenlinkPage;
import com.edison.pages.InsistitutePage;
import com.edison.pages.LoginPage;
import com.edison.pages.SchoolYearPage;

import common.EdisonBokenLink;
import modules.BaseClass;


public class BasePage extends BaseClass{
	
	
	public static LoginPage objLoginpage=null;
	public static InsistitutePage objInsistitutePage=null;
	public static CommonElements objCommonElements=null;
	public static SchoolYearPage objSchoolYearPage=null;
	public static EdsionBrokenlinkPage objEdsionBrokenlinkPage=null;
	public static EdisonBokenLink objBokenLink=null;
	

	public static void PageObectInitialization() {
		objLoginpage = new LoginPage(driver);
		objCommonElements= new CommonElements(driver);
		objInsistitutePage = new InsistitutePage(driver);
		objSchoolYearPage=new SchoolYearPage(driver);
		objEdsionBrokenlinkPage=new EdsionBrokenlinkPage(driver);
		objBokenLink=new EdisonBokenLink();
	}
	
	public static void destroyPageObjects() {
		objLoginpage=null;
		objCommonElements=null;
		objInsistitutePage=null;
		objEdsionBrokenlinkPage=null;
		objBokenLink=null;
	}

}
