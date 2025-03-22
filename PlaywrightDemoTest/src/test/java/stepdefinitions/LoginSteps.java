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

	private final Page page = Hooks.getPage();
	public Scenario scenario = Hooks.getScenario();

	// private Page page;
	private LoginPage loginPage;
	public Hooks hook = new Hooks();
	// public Scenario scenario;

	@Given("User navigate to the login page")
	public void iNavigateToLoginPage() throws IOException {

		// page = PlaywrightFactory.initBrowser("chrome");
		try {
			// page.setViewportSize(1920, 1080);
			loginPage = new LoginPage(page);
			loginPage.navigateToLoginPage("https://practicetestautomation.com/practice-test-login/");
			hook.takeScreenshot();
			scenario.log("Navigated to Url Successfully !!");
		} catch (Exception e) {
			scenario.log("Not able to navigate to given url " + e);
			throw e;
		}
	}

	@When("User enter username {string}")
	public void iEnterUsername(String username) {
		try {
			loginPage.enterUsername(username);
			scenario.log("User is able to enter username successfully");
		} catch (Exception e) {
			scenario.log("User is not able to enter username " + e);
			throw e;
		}
	}

	@When("User enter password {string}")
	public void iEnterPassword(String password) {
		try {
			loginPage.enterPassword(password);
			scenario.log("User is able to enter password successfully");
		} catch (Exception e) {
			scenario.log("User is not able to enter password " + e);
			throw e;
		}
	}

	@When("User click on login button")
	public void iClickLoginButton() {
		try {
			loginPage.clickLogin();
			scenario.log("user is able to click on Submit button successfully");

		} catch (Exception e) {
			scenario.log("User is not able to click on Submit button");
			throw e;
		}
	}

	@Then("User should be logged in successfully")
	public void iShouldBeLoggedInSuccessfully() {
		try {
			Assert.assertTrue("Login failed!", page.url().contains("logged-in-successfully"));
			//PlaywrightFactory.closeBrowser();
			scenario.log("User is able to login successfully");
		} catch (Exception e) {
			scenario.log("User is not able to login");
			throw e;
		}
	}

}
