Feature: Administrator - Course Management.
 
@Test:CourseManagement001 @TestName:CourseManagementFilter @CourseManagementFilter
  Scenario Outline: Verify the Filter Criteria in Course Template
    Given Read the testdata "<COURSE_ID>" "<FileName>" "<Sheetname>"
    Given the user logs in with valid credentials
    When Click on Control Panel
    Then select the "Option" in the control panel
    When select and enter the dropdown values "Institution" "SearchBy" "Name" "ActiveInactive" in course template
    Then Click on GO button
    Then Verify the record in course template table "Code" "Name" "Abbreviation" "ActiveInactive" and click on name
    And Change the driver Focus to new window
    Then verify the broken links in each page "Name" "Code"
    And Write the broken links in excel file "<FileName2>" "<FilePath>"
 
    Examples:
      | COURSE_ID     	   	| FileName               	| Sheetname 		|FileName2					|FilePath					 |
      | COURSE_CODE_003 	| EdisonLearning.xlsx	 	| ELCourseTemplate  |EdsionBrokenLink_AdvMath	|src\\test\\resources\\Edison|
#      | COURSE_CODE_002 	| EdisonLearning.xlsx	 	| ELCourseTemplate  |EdsionBrokenLink_EDMP		|src\\test\\resources\\Edison|