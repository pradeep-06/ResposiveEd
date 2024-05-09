Feature: Administrator - Institutions Management.

@Test:Insistute001 @TestName:InsistuteFilter @InsistuteFilter
  Scenario Outline: Verify the Filter Criteria in Institutions
    Given Read the testdata "<TC_ID>" "<FileName>" "<Sheetname>"
    Given the user logs in with valid credentials
    When the user navigates to the "Screen" screen
    When user enter and select the "Product" "Name" "Status"
    Then Click on GO button
    Then Verify the filter record in institutions table "Name" "Active" "System" "IsConsortium" "Action"

    Examples: 
      | TC_ID     	   | FileName                | Sheetname  |
      | TC_ADMS_UM_001 | ResponcesiveEdQA.xlsx	 | Institution |
   
   
   
  @Test:Insistute002 @TestName:InsistuteMarkInActive @InsistuteMarkInActive
  Scenario Outline: Validate the insistute as InActive
    Given Read the testdata "<TC_ID>" "<FileName>" "<Sheetname>"
    Given the user logs in with valid credentials
    When the user navigates to the "Screen" screen
    When user enter and select the "Product" "Name" "Status"
    Then Click on GO button
    Then Verify the filter record in institutions table "Name" "Active" "System" "IsConsortium" "Action"
    And Click on Yes in the confirmation pop-up
    And Click On OK
    When user enter and select the "Product" "Name" "Status1"
    Then Click on GO button
    Then Verify the filter record in institutions table "Name" "Active1" "System" "IsConsortium" "Action1"
    And value should not show in the Insistitution dropdown "Name" in UserManagement
    And value should not show in the Insistitution dropdown "Name" in Teacher
    And value should not show in the Insistitution dropdown "Name" in Student Application
    When the user navigates to the "Screen" screen
    When user enter and select the "Product" "Name" "Status1"
    Then Click on GO button
    Then Verify the filter record in institutions table "Name" "Active1" "System" "IsConsortium" "Action"
    And Click on Yes in the confirmation pop-up
    And Click On OK
#    Then Store all results UI "TestCaseName"

    Examples: 
      | TC_ID     	   | FileName              | Sheetname   |
      | TC_ADMS_UM_002 | ResponcesiveEdQA.xlsx | Institution |
      
  @Test:Insistute003 @TestName:InsistuteCreateSchoolYear @CreateSchoolYear
  Scenario Outline: Validate the insistute as InActive  
   Given Read the testdata "<TC_ID>" "<FileName>" "<Sheetname>"
    Given the user logs in with valid credentials
    When the user navigates to the "Screen" screen
    When user enter and select the "Product" "Name" "Status"
    Then Click on GO button
    And Verify the filter record in institutions table "Name" "Active" "System" "IsConsortium" "Action"
    When Click on the Add School Year
    And Click on the create
    When Enter the details in create school year "GlobalSchoolYear" "SchoolYearName" "StartDate" "EndDate"
   Examples: 
      | TC_ID     	   | FileName              | Sheetname   |
      | TC_ADMS_SY_001 | ResponcesiveEdQA.xlsx | SchoolYear  |
