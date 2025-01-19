package tests;

import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PlaywrightFactory;

public class BaseTest {
    protected PlaywrightFactory playwrightFactory;
    protected Page page;

    @BeforeMethod
    public void setup() {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser("chromium");
        page.navigate("https://www.saucedemo.com/");
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
    }

    @AfterMethod
    public void teardown() {
        playwrightFactory.closeBrowser();
    }
}
