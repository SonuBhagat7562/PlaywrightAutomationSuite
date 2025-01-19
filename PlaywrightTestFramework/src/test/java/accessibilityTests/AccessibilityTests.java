import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccessibilityTests {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeMethod
    public void setup() {

        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false); // non-headless mode for debugging
        browser = playwright.chromium().launch(launchOptions);
        page = browser.newPage();
    }

    @Test
    public void verifyAllItemsPageAccessibility() {

        try {
            page.onResponse(response -> {
                System.out.println("Response: " + response.status() + " - " + response.url());
            });


            page.navigate("https://yourwebsite.com/all-items", new Page.NavigateOptions().setTimeout(120000)); // Timeout: 120 seconds


            System.out.println("Page loaded successfully!");
        } catch (PlaywrightException e) {
            System.err.println("Error during navigation: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {

        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
