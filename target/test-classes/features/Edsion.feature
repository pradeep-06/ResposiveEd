Feature: Administrator - Course Management.
 
@Test:CourseManagement001 @TestName:CourseManagementFilter @CourseManagementFilter
  Scenario Outline: Verify the Filter Criteria in Course Template
    Given Read the testdata "<COURSE_ID>" "<FileName>" "<Sheetname>"
    Given the user logs in with valid credentials
    When Click on Control Panel
    Then select the "Option" in the control panel
    When select the dropdown values "Institution" "SearchBy" "ActiveInactive"
    Then Click on GO button
    Then Verify the filter record in course template table "Code" "Name" "Abbreviation" "Active/Inactive"
 
    Examples:
      | COURSE_ID     	   	| FileName               	| Sheetname 		|
      | TC_ADCM_CT_001 		| EdisonLearning.xlsx	 	| ELCourseTemplate 	|