#Author: your.email@your.domain.com
Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given User navigate to the login page
    When User enter username "student"
    And User enter password "Password123"  
    And User click on login button
    Then User should be logged in successfully