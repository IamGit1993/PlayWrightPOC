package pageObjects;
import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;

    // Locators
    private String usernameField = "//input[@id='username']";
    private String passwordField = "//input[@id='password']";
    private String loginButton = "//button[@id='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLoginPage(String url) {
    	page.navigate(url);
        page.waitForURL(url);
    }

    public void enterUsername(String username) {
        page.fill(usernameField, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordField, password);
    }

    public void clickLogin() {
        page.click(loginButton);
    }

}
