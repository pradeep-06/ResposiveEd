@tag
Feature: App Question
 
  @tag1
  Scenario: Create Question with List Type and Answers
  Given LoginTo the Application
    Given Click by Question
    And Click on the GO and Create Button
    When Enter Question in Enter Question input field
    And Select Question and Enter answer fields
    Then check whether Question Created