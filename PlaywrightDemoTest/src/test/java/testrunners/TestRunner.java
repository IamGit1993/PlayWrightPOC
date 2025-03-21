package testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", // Updated path
		glue = {"stepdefinitions","hooks"},
		// plugin = {"pretty", "html:target/cucumber-reports.html"},
		// plugin = { "pretty", "html:target/cucumber-reports.html",
		// "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
		plugin = { "pretty", "json:target/cucumber-reports/cucumber.json" }, monochrome = true)

public class TestRunner {

}
