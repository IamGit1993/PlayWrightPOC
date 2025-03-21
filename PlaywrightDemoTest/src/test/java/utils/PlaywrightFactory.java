package utils;


import com.microsoft.playwright.*;

public class PlaywrightFactory {
	
	
	    private static Playwright playwright;
	    private static Browser browser;
	    private static BrowserContext context;
	    private static Page page;

	    public static Page initBrowser(String browserName) {
	        playwright = Playwright.create();
	        
	        switch (browserName.toLowerCase()) {
	            case "chrome":
	                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	                break;
	            case "firefox":
	                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid Browser Name: " + browserName);
	        }

	        context = browser.newContext();
	        page = context.newPage();
	        return page;
	    }

	    public static void closeBrowser() {
	    	
	     //   context.close();
	     //   browser.close();
	      //  playwright.close();
	    }
	}


