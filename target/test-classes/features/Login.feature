@tag
Feature: Login - Check the flow of login with valid credentials

  @sanity
  Scenario: Verify successful login with valid credentials
    Given Launch the application
    #Given User is on home page
    When User enters credentials to login
      | Username      | Password  |
      | srinivas.test | Welcome1! |
    Then Message displayed login successfully
