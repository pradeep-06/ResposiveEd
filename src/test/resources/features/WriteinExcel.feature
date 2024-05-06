@WriteinExcel
Feature: WriteintheExcel

  Scenario: Log the results
    Given Write in Excelfile

  Scenario Outline: Validate the Title and Bread Crumb in AccountDetailsScreen
    Given Open the Rafe screen
    Then Store all results UI "<TestCaseName>"

    Examples: 
      | TestCaseName | FilePath                            |
      | TC_01        | src/main/resources/data/RafeUI/.xls |
