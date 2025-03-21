#Author: your.email@your.domain.com


Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I navigate to the login page
    When I enter username "student"
    And I enter password "Password123"
    And I click on login button
    Then I should be logged in successfully