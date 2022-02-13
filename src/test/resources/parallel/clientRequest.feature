@Regression @ClientRequest
Feature: Client request

  Background:
    When User enters valid credentials for login form
    And User navigates to client request page

  @Smoke @TC1
  Scenario: Check elements on client request page
    Then Check elements on client request page

  @TC2
  Scenario: Add valid client request
    And User clicks on add button
    And User fills client request with valid data and save it
    Then Check client is saved


  Scenario Outline: Search valid data
    And User search valid requests according client or name "<input>" input
    Then Check client request is shown according to "<input>" input
    @TC3
    Examples:
      | input  |
      | client |
    @TC4
    Examples:
      | input |
      | name  |

  Scenario Outline: Search invalid data
    And User search invalid requests according client or name "<input>" input
    Then Check error message is shown
    @TC5
    Examples:
      | input  |
      | client |
    @TC6
    Examples:
      | input |
      | name  |

  @TC7
  Scenario: Cancel valid client request
    And User clicks on add button
    And User fills client request with valid data and cancel it
    Then Check client request page is present

  @TC8
  Scenario: Check outside of client request
    And User clicks outside of border for client request
    Then Check client request page is present


  Scenario Outline: Negative scenario and validation
    And User clicks on add button
    And User fills data "<combination>" combination
    And Save client request
    Then Check following "<error>" error appears

    @Smoke @TC9
    Examples:
      | combination    | error               |
      | allFieldsEmpty | requiredFieldsError |
    @TC11
    Examples:
      | combination        | error           |
      | invalidClientName  | wrongEntryError |
    @TC12
    Examples:
      | combination        | error           |
      | invalidNumber      | wrongEntryError |
    @TC13
    Examples:
      | combination        | error           |
      | invalidRequestName | wrongEntryError |
    @TC14
    Examples:
      | combination        | error           |
      | invalidLocation    | wrongEntryError |
