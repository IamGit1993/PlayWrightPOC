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

	// private Page page;
	private LoginPage loginPage;
	public Hooks hook = new Hooks();
	// public Scenario scenario;

	@Given("I navigate to the login page")
	public void iNavigateToLoginPage() throws IOException {

		// page = PlaywrightFactory.initBrowser("chrome");
		try {
			//page.setViewportSize(1920, 1080);
			loginPage = new LoginPage(page);
			loginPage.navigateToLoginPage("https://practicetestautomation.com/practice-test-login/");
			hook.takeScreenshot();
			hook.scenario.log("Navigated to Url Successfully !!");
		} catch (Exception e) {
			throw e;
		}
	}

	@When("I enter username {string}")
	public void iEnterUsername(String username) {
		loginPage.enterUsername(username);
	}

	@When("I enter password {string}")
	public void iEnterPassword(String password) {
		loginPage.enterPassword(password);
	}

	@When("I click on login button")
	public void iClickLoginButton() {
		loginPage.clickLogin();
	}

	@Then("I should be logged in successfully")
	public void iShouldBeLoggedInSuccessfully() {
		Assert.assertTrue("Login failed!", page.url().contains("logged-in-successfully"));
		PlaywrightFactory.closeBrowser();
	}

}
