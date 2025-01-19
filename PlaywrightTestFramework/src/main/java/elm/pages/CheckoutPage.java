package elm.pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;

    private final String cartIconSelector = ".shopping_cart_link";
    private final String checkoutButtonSelector = "#checkout";
    private final String firstNameSelector = "#first-name";
    private final String lastNameSelector = "#last-name";
    private final String postalCodeSelector = "#postal-code";
    private final String continueButtonSelector = "#continue";
    private final String finishButtonSelector = "#finish";
    private final String checkoutCompleteSelector = ".complete-header";

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void completeCheckout(String firstName, String lastName, String postalCode) {
        page.click(cartIconSelector);
        page.click(checkoutButtonSelector);
        page.fill(firstNameSelector, firstName);
        page.fill(lastNameSelector, lastName);
        page.fill(postalCodeSelector, postalCode);
        page.click(continueButtonSelector);
        page.click(finishButtonSelector);
    }

    public boolean isCheckoutComplete() {
        return page.locator(checkoutCompleteSelector).isVisible();
    }
}
