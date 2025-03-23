package pageObjects;

import org.junit.Assert;

import com.microsoft.playwright.Page;

public class DashboardPage {
	// *****************************************************************
	// *****************************************************************
	// Author Name : Abhishek Raj
	// Function : Dashboard Page - UI Element Interactions
	// Created Date: 22nd March 2025
	// Modified Date:
	// Comments: This class provides methods to interact with elements
	// on the dashboard page, including verifying tab presence and performing
	// actions like logout.
	// *****************************************************************
	// *****************************************************************

	private static Page page; // Playwright Page instance to interact with the web page

	// XPath locator for the logout button
	private static String btnLogOut = "//a[text()='Log out']";

	// Method to dynamically generate XPath for a given tab name
	private static String elementLocator(String tabName) {
		return "//a[text()='" + tabName + "']";
	}

	// Method to generate XPath for a tab present in the navigation menu
	private static String getTabLocators(String tabName) {
		return "//nav[@class='menu']//li/a[text()='" + tabName + "']";
	}

	// Constructor to initialize the Playwright Page instance
	public DashboardPage(Page page) {
		this.page = page;
	}

	// Method to verify whether a specified tab is present on the dashboard
	public void verifyTabIsPresent(String tabName) {
		boolean isElementPresent = false;
		try {
			// Check if the tab locator is present on the page
			isElementPresent = page.locator(getTabLocators(tabName)).count() > 0;

			// Assert that the tab is present
			Assert.assertTrue(isElementPresent);
		} catch (Exception e) {
			// Print stack trace in case of an exception
			e.printStackTrace();
		}
		// Log the result in the console
		System.out.println("Element present: " + isElementPresent);
	}

	// Method to check if a specified element is present on the page
	public void IsElementPresent(String locator) {
		boolean isElementPresent = false;
		try {
			// Check if the element locator is present on the page
			isElementPresent = page.locator(elementLocator(locator)).count() > 0;
		} catch (Exception e) {
			// Print stack trace in case of an exception
			e.printStackTrace();
		}
		// Log the result in the console
		System.out.println("Element present: " + isElementPresent);
	}

	// Method to click on the logout button
	public void clickLogOutButton() {
		// Perform click action on the logout button
		page.click(btnLogOut);
	}
}
