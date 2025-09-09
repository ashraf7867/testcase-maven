package pages;
import com.microsoft.playwright.*;

public class LoginPage {
    Playwright playwright;
    Browser browser;
    Page page;

    private String usernameField = "#username";
    private String passwordField = "#password";
    private String loginButton = "button.radius";

    public void initBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void login(String username, String password) {
        page.fill(usernameField, username);
        page.fill(passwordField, password);
        page.click(loginButton);
    }

    public boolean isLoggedIn() {
        return page.textContent("div.flash.success") != null;
    }

    public void closeBrowser() {
        browser.close();
        playwright.close();
    }
}