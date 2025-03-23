package stepdefinitions;

import java.util.List;

import org.junit.Assert;

import com.microsoft.playwright.Page;

import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;

public class DashboardSteps {

	// ******************************************************************************
	// ******************************************************************************
	// Author Name : Abhishek Raj
	// Function : Step Definitions for Dashboard Page Verification
	// Created Date: 22nd March 2025
	// Modified Date:
	// Comments: This class contains step definitions for verifying elements on the
	// dashboard page, including checking the presence of tabs and buttons,
	// clicking buttons, and ensuring the user session is terminated after logout.
	// Each method includes proper exception handling and logging for test execution
	// tracking.
	// *******************************************************************************
	// *******************************************************************************

	// Initialize the Playwright Page object using Hooks to interact with the
	// browser
	private final Page page = Hooks.getPage();

	// Retrieve the current test scenario from Hooks to log test execution details
	public Scenario scenario = Hooks.getScenario();

	// Create an instance of Hooks to use its utility methods (e.g., taking
	// screenshots)
	public Hooks hook = Hooks.getInstance();

	// Declare a reference for the DashboardPage object, which will be initialized
	// when needed
	private DashboardPage dashboardPage;

	@Then("Verify different tabs are present")
	public void verify_different_tabs_are_present(DataTable table) {
		try {
			// Retrieve the first row from the data table as a list of tab names
			List<String> data = table.row(0);

			// Ensure the list is not empty before proceeding with verification
			Assert.assertTrue(data.size() > 0);

			// Initialize the DashboardPage object
			dashboardPage = new DashboardPage(page);

			// Iterate through each tab name and verify its presence on the dashboard
			for (String tab : data) {
				// Re-initialize the DashboardPage object to ensure a fresh instance
				dashboardPage = new DashboardPage(page);

				// Verify if the tab is present on the dashboard
				dashboardPage.verifyTabIsPresent(tab);
			}

			// Capture a screenshot after verifying the tabs
			hook.takeScreenshot();

			// Log the successful verification of tabs
			scenario.log("User is able to verify tabs " + data.toString());
		} catch (Exception e) {
			// Log the failure message in case of an exception
			scenario.log("User is not able to verify tabs ");

			// Rethrow the exception to indicate test failure
			throw e;
		}
	}

	@Then("User should verify {string} button is present")
	public void user_should_verify_button_is_present(String btnName) {
		try {
			// Initialize the DashboardPage object to interact with the UI
			dashboardPage = new DashboardPage(page);

			// Check if the specified button is present on the dashboard
			dashboardPage.IsElementPresent(btnName);

			// Log a message indicating that the button is found
			scenario.log(btnName + " is present");
		} catch (Exception e) {
			// Log an error message if the button is not found
			scenario.log(btnName + " is not present");

			// Throw a runtime exception with a detailed error message
			throw new RuntimeException("Element not found: " + btnName, e);
		}
	}

	@Then("User click on {string} button")
	public void user_click_on_button(String btnName) {
		try {
			// Initialize the DashboardPage object to interact with UI elements
			dashboardPage = new DashboardPage(page);

			// Click on the logout button (currently hardcoded for logout)
			dashboardPage.clickLogOutButton();

			// Log success message indicating the button was clicked
			scenario.log("User able to click on " + btnName);
		} catch (Exception e) {
			// Log failure message if button click action fails
			scenario.log("User not able to click on " + btnName);

			// Throw a runtime exception with a detailed error message
			throw new RuntimeException("Element not found: " + btnName, e);

		}
	}

	@Then("User should verify the session is terminated")
	public void user_should_verify_the_session_is_terminated() throws Exception {
		try {

			// Verify that the current URL contains "practice-test-login" indicating a
			// successful logout
			Assert.assertTrue("Logout failed!", page.url().contains("practice-test-login"));

			// Log success message indicating that the user has been logged out successfully
			scenario.log("User able to verify that application logged out ");
		} catch (Exception e) {
			// Log failure message if the session termination verification fails
			scenario.log("User is not able to verify that application logged out ");

			// Rethrow the exception to mark the test as failed
			throw e;
		}
	}

}
