package pageObjects;

import org.junit.Assert;

import com.microsoft.playwright.Page;

public class DashboardPage {
	private static Page page;

	private static String btnLogOut = "//a[text()='Log out']";
	
	private static String elementLocator(String tabName) {
		return "//a[text()='"+tabName+"']";
	}


	private static String getTabLocators(String tabName) {
		return "//nav[@class='menu']//li/a[text()='" + tabName + "']";
	}

	public DashboardPage(Page page) {
		this.page = page;
	}

	public static void verifyTabIsPresent(String tabName) {
		boolean isElementPresent = false;
		try {
			isElementPresent = page.locator(getTabLocators(tabName)).count() > 0;
			Assert.assertTrue(isElementPresent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Element present: " + isElementPresent);
	}

	public void IsElementPresent(String locator) {
		boolean isElementPresent = false;
		try {
			isElementPresent = page.locator(elementLocator(locator)).count() > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Element present: " + isElementPresent);
	}

	public void clickLogOutButton() {	
			page.click(btnLogOut);
	}
}
