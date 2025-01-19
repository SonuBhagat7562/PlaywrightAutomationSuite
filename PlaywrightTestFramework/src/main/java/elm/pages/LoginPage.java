package elm.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private final String usernameSelector = "#user-name";
    private final String passwordSelector = "#password";
    private final String loginButtonSelector = "#login-button";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateTo() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        page.fill(usernameSelector, username);
        page.fill(passwordSelector, password);
        page.click(loginButtonSelector);
    }
}
