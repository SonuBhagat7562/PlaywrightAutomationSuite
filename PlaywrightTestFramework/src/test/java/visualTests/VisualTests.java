package visualTests;

import com.microsoft.playwright.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VisualTests {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();


        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));


        BrowserContext context = browser.newContext();
        Page page = context.newPage();


        String url = "https://yourwebsite.com/all-items";
        page.navigate(url);


        String screenshotDir = "screenshots";
        String screenshotFile = "all-items-page.png";

        try {

            Path screenshotsPath = Paths.get(screenshotDir);
            if (!Files.exists(screenshotsPath)) {
                Files.createDirectories(screenshotsPath);
            }


            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotDir, screenshotFile)));
            System.out.println("Screenshot saved at: " + Paths.get(screenshotDir, screenshotFile).toAbsolutePath());

        } catch (Exception e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        } finally {
            
            browser.close();
            playwright.close();
        }
    }
}
