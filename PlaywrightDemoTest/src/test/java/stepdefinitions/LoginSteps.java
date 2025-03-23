package stepdefinitions;

import com.microsoft.playwright.Page;

import hooks.Hooks;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import utils.PlaywrightFactory;
import org.junit.Assert;
import io.cucumber.java.Scenario;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.io.IOException;
import java.nio.file.Files;

public class LoginSteps {

	// *********************************************************************************
	// *********************************************************************************
	// Author Name : Abhishek Raj
	// Function : To Handle Login Page Functionality
	// Created Date: 22nd March 2025
	// Modified Date:
	// Comments: This class contains step definitions for login-related scenarios 
	// using Playwright and Cucumber BDD. It includes navigating to the login page,
	// entering credentials, clicking the login button, and verifying successful login.
	// *********************************************************************************
	// *********************************************************************************

	// Retrieve the Playwright page instance from Hooks for browser interactions
	private final Page page = Hooks.getPage(); 
	// Get the current test scenario for logging and reporting
	public Scenario scenario = Hooks.getScenario(); 
	// Declare LoginPage object, initialized when needed
	private LoginPage loginPage; 
	// Get the singleton instance of Hooks for utility functions
	public Hooks hook = Hooks.getInstance(); 

	@Given("User navigate to the login page")
	public void iNavigateToLoginPage() throws IOException {
	    try {
	        // Initialize the LoginPage object with the current Playwright page
	        loginPage = new LoginPage(page);

	        // Navigate to the specified login page URL
	        loginPage.navigateToLoginPage("https://practicetestautomation.com/practice-test-login/");

	        // Capture a screenshot for verification
	        hook.takeScreenshot();

	        // Log success message indicating successful navigation
	        scenario.log("Navigated to Url Successfully !!");
	    } catch (Exception e) {
	        // Log failure message if navigation fails
	        scenario.log("Not able to navigate to given url " + e);

	        // Rethrow the exception to mark the test as failed
	        throw e;
	    }
	}

	@When("User enter username {string}")
	public void iEnterUsername(String username) {
	    try {
	        // Enter the provided username into the username field
	        loginPage.enterUsername(username);

	        // Log success message indicating the username was entered successfully
	        scenario.log("User is able to enter username successfully");
	    } catch (Exception e) {
	        // Log failure message if unable to enter username
	        scenario.log("User is not able to enter username " + e);

	        // Rethrow the exception to mark the test as failed
	        throw e;
	    }
	}

	@When("User enter password {string}")
	public void iEnterPassword(String password) {
	    try {
	        // Enter the provided password into the password field
	        loginPage.enterPassword(password);

	        // Log success message indicating the password was entered successfully
	        scenario.log("User is able to enter password successfully");
	    } catch (Exception e) {
	        // Log failure message if unable to enter password
	        scenario.log("User is not able to enter password " + e);

	        // Rethrow the exception to mark the test as failed
	        throw e;
	    }
	}

	@When("User click on login button")
	public void iClickLoginButton() {
	    try {
	        // Click the login button to submit the credentials
	        loginPage.clickLogin();

	        // Log success message indicating the login button was clicked successfully
	        scenario.log("User is able to click on Submit button successfully");
	    } catch (Exception e) {
	        // Log failure message if unable to click the login button
	        scenario.log("User is not able to click on Submit button");

	        // Rethrow the exception to mark the test as failed
	        throw e;
	    }
	}

	@Then("User should be logged in successfully")
	public void iShouldBeLoggedInSuccessfully() {
	    try {
	        // Assert that the URL contains "logged-in-successfully" to verify login success
	        Assert.assertTrue("Login failed!", page.url().contains("logged-in-successfully"));

	        // Log success message indicating successful login
	        scenario.log("User is able to login successfully");
	    } catch (Exception e) {
	        // Log failure message if login verification fails
	        scenario.log("User is not able to login");

	        // Rethrow the exception to mark the test as failed
	        throw e;
	    }
	}
}
