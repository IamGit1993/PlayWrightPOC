package pageObjects;
import com.microsoft.playwright.Page;

public class LoginPage {
	
	// *****************************************************************
	// *****************************************************************
	// Author Name : Abhishek Raj
	// Function : Login Page - UI Element Interactions
	// Created Date: 22nd March 2025
	// Modified Date:
	// Comments: This class provides methods to interact with the login page, 
	// including entering credentials and performing the login action.
	// *****************************************************************
	// *****************************************************************

	private Page page; // Playwright Page instance to interact with the web page

	// Locators for login page elements
	private String usernameField = "//input[@id='username']"; // Locator for the username input field
	private String passwordField = "//input[@id='password']"; // Locator for the password input field
	private String loginButton = "//button[@id='submit']";   // Locator for the login button

	// Constructor to initialize the Playwright Page instance
	public LoginPage(Page page) {
	    this.page = page;
	}

	// Method to navigate to the login page using the provided URL
	public void navigateToLoginPage(String url) {
	    page.navigate(url); // Open the login page URL
	    page.waitForURL(url); // Wait until the page is fully loaded
	}

	// Method to enter the username into the username field
	public void enterUsername(String username) {
	    page.fill(usernameField, username);
	}

	// Method to enter the password into the password field
	public void enterPassword(String password) {
	    page.fill(passwordField, password);
	}

	// Method to click the login button and attempt login
	public void clickLogin() {
	    page.click(loginButton);
	}

}
