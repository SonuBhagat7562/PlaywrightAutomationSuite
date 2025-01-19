package elm.pages;

import com.microsoft.playwright.Page;

import java.util.List;

public class AllItemsPage {
    private final Page page;

    private final String sortDropdownSelector = ".product_sort_container";
    private final String itemNameSelector = ".inventory_item_name";
    private final String itemPriceSelector = ".inventory_item_price";
    private final String addToCartButtonSelector = ".btn_inventory";

    public AllItemsPage(Page page) {
        this.page = page;
    }

    public void navigateTo() {
        page.navigate("https://www.saucedemo.com/inventory.html");
    }

    public void sortItems(String sortOption) {
        page.selectOption(sortDropdownSelector, sortOption);
    }

    public List<String> getItemNames() {
        return page.locator(itemNameSelector).allInnerTexts();
    }

    public List<Double> getItemPrices() {
        return page.locator(itemPriceSelector).allInnerTexts().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    public void addItemsToCart(int count) {
        List<String> addButtons = page.locator(addToCartButtonSelector).allTextContents();
        for (int i = 0; i < count && i < addButtons.size(); i++) {
            page.locator(addToCartButtonSelector).nth(i).click();
        }
    }
}
