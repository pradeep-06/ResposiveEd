@tag
Feature: Administrator - Check the flow of all User Management Drop Down Handle

  @sanity
  Scenario: Verify the flow of all User Management Drop Down Handle
    Given login to the APP
    Then Click on the USERMENU
    Then Select institutinsDropDown
    Then Select rolesDropDown
    Then Select enrollmentStatusDropDown
    Then Slect searchByDropDown
    And Click ON GO BUTTON
