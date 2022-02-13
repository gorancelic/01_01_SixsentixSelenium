@Regression @YourProfile
Feature: Your profile page editing

  Background:
    When User enters valid credentials for login form

  @Smoke @TC1
  Scenario: Edit employee information and fill all fields
    When User enters valid information for all fields
    Then Check that correct information are visible

    @TC2
  Scenario: Edit employee work information
    When User opens edit work section and enter valid skills
    Then Check that correct information are visible in work section
    And User should delete the job after verification