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
	private static Playwright playwright;
	private static Browser browser;
	private static BrowserContext context;
	private static Page page;
	public static Scenario scenario;

	@Before
	public void setUp(Scenario scenario) {
		Hooks.scenario = scenario;
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		// BrowserContext context = browser.newContext();
		context = browser.newContext();
		page = context.newPage();
		// page.setViewportSize(1920, 1080); // Maximize window
		System.out.println("🚀 Starting Scenario: " + scenario.getName());
	}

	@After
	public void tearDown() {
		if (scenario.isFailed()) {
			takeScreenshot();
		}
		// resetContext();
//        browser.close();
//        playwright.close();
		System.out.println("✅ Scenario Completed: " + scenario.getName());
	}

	public static BrowserContext getContext() {
		return context;
	}

	public static void resetContext() {
		context.close(); // Close only the context, not the browser
		context = browser.newContext();
		page = context.newPage();
	}

	public static Page getPage() {
		return page;
	}

	public static Scenario getScenario() {
		return scenario;
	}

	public void takeScreenshot() {
		try {

			String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/"
					+ scenario.getName().replace(" ", "_") + ".png";
			page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
			byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));
			scenario.attach(screenshotBytes, "image/png", "Screenshot");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}