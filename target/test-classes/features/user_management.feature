Feature: User Management
  As an Admin user
  I want to manage users in the system
  So that I can control access to the application

  Background:
    Given I am on the OrangeHRM login page
    When I login with valid credentials
    Then I should be redirected to the dashboard

  Scenario: Create and verify a new user
    Given I navigate to Admin User Management page
    When I create a new user with the following details:
      | Employee Name | Username          | User Role | Status  |
      | Ranga Akunuri  | testuser_<timestamp> | Admin     | Enabled |
    Then the user should be created successfully
    When I search for the user by username "testuser_<timestamp>"
    Then the user should appear in the Records Found table
    And the user details should be correct