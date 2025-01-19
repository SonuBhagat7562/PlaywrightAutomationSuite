package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class TestUtils {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;


    public static void setUp() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        if (browser == null) {

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(true);
            browser = playwright.chromium().launch(options);
        }
        if (context == null) {
            context = browser.newContext();
        }
        if (page == null) {
            page = context.newPage();
        }
    }


    public static void tearDown() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }


    public static void takeScreenshot(String testName) {
        try {
            String screenshotPath = "target/screenshots/" + testName + "_" + UUID.randomUUID().toString() + ".png";
            Files.createDirectories(Paths.get("target/screenshots"));
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
            System.out.println("Screenshot taken: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void waitForElementToBeVisible(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
    }


    public static void waitForElementToBeClickable(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


    public static void navigateTo(String url) {
        page.navigate(url);
    }


    public static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void assertPageTitle(String expectedTitle) {
        String actualTitle = page.title();
        if (!actualTitle.equals(expectedTitle)) {
            throw new AssertionError("Expected title: " + expectedTitle + " but got: " + actualTitle);
        }
    }


    public static void assertElementText(String selector, String expectedText) {
        String actualText = page.locator(selector).textContent();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError("Expected text: '" + expectedText + "' but got: '" + actualText + "'");
        }
    }


    public static void assertElementVisible(String selector) {
        if (!page.locator(selector).isVisible()) {
            throw new AssertionError("Element with selector: '" + selector + "' is not visible.");
        }
    }


    public static void assertElementNotVisible(String selector) {
        if (page.locator(selector).isVisible()) {
            throw new AssertionError("Element with selector: '" + selector + "' should not be visible.");
        }
    }
}
