@Regression @Login
Feature: Login process

  @Smoke @TC1
  Scenario: Fill login with valid credentials and check is the login successful
    When User enters valid credentials for login form
    Then Check that user is logged in successfully

  Scenario Outline: Fill negative scenarios and check error messages
    When User enters "<email>" email and "<password>" password for login form
    Then Check that appropriate error message is shown for "<email>" email
    @TC2
    Examples:
      | email   | password |
      | invalid | valid    |
    @TC3
    Examples:
      | email   | password |
      | empty   | empty    |
