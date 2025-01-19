package utils;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    public Page initBrowser(String browserType) {

        System.setProperty("PLAYWRIGHT_BROWSERS_PATH", "C:\\Users\\Sonu\\AppData\\Local\\ms-playwright");


        playwright = Playwright.create();

        browser = switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setTimeout(300000)
            );
            case "webkit" -> playwright.webkit().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setTimeout(300000)
            );
            default -> playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setTimeout(300000)
            );
        };


        context = browser.newContext();
        return context.newPage();
    }

    public void closeBrowser() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
