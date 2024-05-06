Feature: Administrator - Check the flow of Create New User

  @TestCase:001 @TestName:CreateUser @CreateUser
  Scenario Outline: Verify the flow of Create New User
    Given Read the testdata "<TC_ID>" "<FileName>" "<Sheetname>"
    Given login by the Username and Password
    When click the UserMenu
    Then Click on The Create button
#    Then Enter data to all fields "Institution" "Role" "FirstName" "MiddleName" "LastName" "Email" "UserName" "Suffix" "TimeZoneID" "Gender" "Nick" "Password"
    Then Click on create button
    Then click on OK button
    And SignOut
    Then Store all results UI "TestCaseName"

    Examples: 
      | TC_ID          | FileName              | Sheetname  |
      | TC_ADMS_UM_019 | ResponcesiveEdQA.xlsx | CreateUser |
