Feature: Employee Claims Management
  As an HR administrator
  I want to manage employee claims
  So that I can process travel allowances efficiently

  Background:
    Given I am logged into the claims management system

  Scenario: Create and verify employee travel claim
    When I navigate to the Claims page from the left sidebar
    And I click on Assign Claim
    And I create a new Assign Claim with the following details:
      | Employee Name | yytest wang        |
      | Event         | Travel Allowance   |
      | Currency      | Euro                |
    Then I should see a success message for claim creation
    And I should be redirected to the Assign Claim details page
    And the claim details should match the entered information

    When I click the Add Expense button
    And I add an expense with the following details:
      | Expense Type | Accommodation       |
      | Date         | 2025-01-01   |
      | Amount       | 150.00       |
    Then I should see a success message for expense submission
    And the expense details should match the entered information

    When I click the Back button
    Then I should see the expense record in the claims list