#Author: abhishek20009@gmail.com
Feature: Dashboard Functionality

Scenario: Verify Dashboard functionilty tabs
    Given User navigate to the login page
    When User enter username "student"
    And User enter password "Password123"
    And User click on login button
    Then Verify different tabs are present
    |Home|Practice|Courses|Blog|Contact|
    
 Scenario: Verify Logout button is Present
    Given User navigate to the login page
    When User enter username "student"
    And User enter password "Password123"
    And User click on login button
 		Then User should verify "Log out" button is present
 		Then User click on "Log out" button
 		Then User should verify the session is terminated