@Regression @EmployeeSearch
Feature: Employee Search

  Background:
    When User enters valid credentials for login form
    And User navigates to employee search page

  @Smoke @TC1
  Scenario: Fill keyword search field with valid keyword and check is searching successful
    When User enters valid keyword and search candidates
    Then Check that appropriate profile is shown

    @TC2
  Scenario: Fill keyword search field with invalid keyword and check error message
    When User enters invalid keyword and search candidates
    Then Check that appropriate error message is shown for invalid keyword

      @TC3
    Scenario: Fill advanced search fields with valid data and check is searching successful
      When  User enters valid data and search candidates
      Then Check that appropriate profiles are shown

