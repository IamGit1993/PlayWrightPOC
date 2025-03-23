package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.io.IOException;

public class Hooks {
	// *******************************************************************************************
	// *******************************************************************************************
	// Author Name : Abhishek Raj
	// Function : Hooks for Playwright Setup and Teardown
	// Created Date: 22nd March 2025
	// Modified Date:
	// Comments: This class sets up and manages Playwright browser sessions, 
	// handles scenario execution lifecycle (before and after steps), takes screenshots 
	// for failed test cases, and provides utility methods to interact with Playwright instances. 
	// *******************************************************************************************
	// *******************************************************************************************

	private static Playwright playwright; // Playwright instance for browser automation
	private static Browser browser; // Browser instance to interact with the web pages
	private static BrowserContext context; // BrowserContext instance for isolated browser sessions
	private static Page page; // Page instance to interact with the web page
	public static Scenario scenario; // Cucumber Scenario instance for logging and reporting
	private static Hooks instance; // Singleton instance of Hooks

	@Before
	public void setUp(Scenario scenario) {
	    // Assign the current test scenario to the static variable
	    Hooks.scenario = scenario;

	    // Initialize Playwright instance
	    playwright = Playwright.create();

	    // Launch a new Chromium browser instance in non-headless mode
	    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

	    // Create a new browser context for isolated session handling
	    context = browser.newContext();

	    // Open a new page in the current browser context
	    page = context.newPage();

	    // Log the start of the test scenario execution
	    System.out.println("🚀 Starting Scenario: " + scenario.getName());
	}

	public static Hooks getInstance() {
	    // Singleton pattern implementation to return a single instance of Hooks
	    if (instance == null) {
	        instance = new Hooks();
	    }
	    return instance;
	}

	@After
	public void tearDown() {
	    // Capture a screenshot if the scenario fails
	    if (scenario.isFailed()) {
	        takeScreenshot();
	    }

	    // Log completion of the test scenario
	    System.out.println("✅ Scenario Completed: " + scenario.getName());
	}

	public static BrowserContext getContext() {
	    // Return the current browser context
	    return context;
	}

	public static void resetContext() {
	    // Close the current context without closing the entire browser
	    context.close();

	    // Create a new browser context to reset session data
	    context = browser.newContext();

	    // Open a new page in the refreshed context
	    page = context.newPage();
	}

	public static Page getPage() {
	    // Return the current Playwright page instance
	    return page;
	}

	public static Scenario getScenario() {
	    // Return the current scenario instance for logging and reporting
	    return scenario;
	}

	public void takeScreenshot() {
	    try {
	        // Define the file path for saving the screenshot
	        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/"
	                + scenario.getName().replace(" ", "_") + ".png";

	        // Capture a screenshot of the current page and save it to the specified path
	        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

	        // Read the screenshot file into a byte array
	        byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));

	        // Attach the screenshot to the Cucumber scenario report
	        scenario.attach(screenshotBytes, "image/png", "Screenshot");
	    } catch (IOException e) {
	        // Print stack trace if an error occurs while taking a screenshot
	        e.printStackTrace();
	    }
	}
}