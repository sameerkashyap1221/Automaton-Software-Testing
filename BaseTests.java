package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTests {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
        	    new BrowserType.LaunchOptions()
        	        .setHeadless(false)
        	);
        
        Browser.NewPageOptions options = new Browser.NewPageOptions()
        	    .setIgnoreHTTPSErrors(true);

        	page = browser.newPage(options);

        page.setDefaultTimeout(60000);
        page.setDefaultNavigationTimeout(90000);

        page.navigate(
            "https://www.saucedemo.com/",
            new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.COMMIT)
                .setTimeout(90000)
        );

        page.locator("#login-button").waitFor();
        System.out.println("SauceDemo opened successfully");
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}
