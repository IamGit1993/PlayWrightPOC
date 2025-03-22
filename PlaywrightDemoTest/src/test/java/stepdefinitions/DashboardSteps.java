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
	private final Page page = Hooks.getPage();
	public Scenario scenario = Hooks.getScenario();
	public Hooks hook = new Hooks();

	private DashboardPage dashboardPage;

	@Then("Verify different tabs are present")
	public void verify_different_tabs_are_present(DataTable table) {
		try {
			List<String> data = table.row(0);
			Assert.assertTrue(data.size() > 0);
			dashboardPage = new DashboardPage(page);
			for (String tab : data) {
				dashboardPage = new DashboardPage(page);
				dashboardPage.verifyTabIsPresent(tab);
			}
			hook.takeScreenshot();
			scenario.log("User is able to verify tabs " + data.toString());
		} catch (Exception e) {
			scenario.log("User is not able to verify tabs ");
			throw e;
		}
	}

	@Then("User should verify {string} button is present")
	public void user_should_verify_button_is_present(String btnName) {
		try {
			dashboardPage = new DashboardPage(page);
			dashboardPage.IsElementPresent(btnName);
			scenario.log(btnName+" is present");
		} catch (Exception e) {
			scenario.log(btnName+" is not present");
			throw new RuntimeException("Element not found: " + btnName, e);
		}
	}

	@Then("User click on {string} button")
	public void user_click_on_button(String btnName) {
		try {
			dashboardPage = new DashboardPage(page);
			dashboardPage.clickLogOutButton();
			scenario.log("User able to click on "+btnName);
		} catch (Exception e) {
			scenario.log("User not able to click on "+btnName);
			throw new RuntimeException("Element not found: " + btnName, e);
			// TODO: handle exception
		}
	}

	@Then("User should verify the session is terminated")
	public void user_should_verify_the_session_is_terminated() throws Exception {
		try {
			Thread.sleep(4000);
			Assert.assertTrue("Logout failed!", page.url().contains("practice-test-login"));
			scenario.log("User able to verify that application logged out ");
		} catch (Exception e) {
			scenario.log("User is not able to verify that application logged out ");
			throw e;
		}
	}

}
