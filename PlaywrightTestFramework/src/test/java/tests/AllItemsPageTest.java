package tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import elm.pages.AllItemsPage;
import elm.pages.CheckoutPage;
import elm.pages.LoginPage;
//import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllItemsPageTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private AllItemsPage allItemsPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() throws InterruptedException {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();


        loginPage = new LoginPage(page);
        allItemsPage = new AllItemsPage(page);
        checkoutPage = new CheckoutPage(page);


        loginPage.navigateTo();
        Thread.sleep(1000);
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);
    }

    @Test (priority = 1)
    public void testSortByNameDescending() throws InterruptedException {

        allItemsPage.sortItems("za");
        Thread.sleep(1000);

        List<String> names = allItemsPage.getItemNames();
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(Collections.reverseOrder());

        Assert.assertEquals(names, sortedNames, "Items are not sorted Z-A");
        Thread.sleep(1000);
    }

    @Test (priority = 2)
    public void testSortByPriceHighToLow() throws InterruptedException {

        allItemsPage.sortItems("hilo");
        Thread.sleep(1000);

        List<Double> prices = allItemsPage.getItemPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(prices, sortedPrices, "Prices are not sorted High to Low");
        Thread.sleep(1000);
    }

    @Test (priority = 3)
    public void testAddToCartAndCheckout() throws InterruptedException {

        allItemsPage.addItemsToCart(2);
        Thread.sleep(1000);


        checkoutPage.completeCheckout("John", "Doe", "12345");
        Thread.sleep(1000);


        Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout did not complete successfully");
        Thread.sleep(1000);
    }

    @AfterClass
    public void tearDown() {
        try {
            if (page != null) {
                page.close();
                System.out.println("Page closed successfully.");
            }
            if (context != null) {
                context.close();
                System.out.println("Context closed successfully.");
            }
            if (browser != null) {
                browser.close();
                System.out.println("Browser closed successfully.");
            }
            if (playwright != null) {
                playwright.close();
                System.out.println("Playwright closed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
