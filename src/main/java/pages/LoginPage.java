package pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Locators;

/**
 * Page Object for OrangeHRM Login Page
 */
public class LoginPage extends BasePage {
    
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Check if login page is displayed
     */
    public boolean isLoginPageDisplayed() {
        log.info("Verifying login page is displayed");
        return isDisplayed(Locators.Login.LOGIN_CONTAINER);
    }
    
    /**
     * Enter username
     */
    public LoginPage enterUsername(String username) {
        log.info("Entering username: {}", username);
        type(Locators.Login.USERNAME_FIELD, username);
        return this;
    }
    
    /**
     * Enter password
     */
    public LoginPage enterPassword(String password) {
        log.info("Entering password");
        type(Locators.Login.PASSWORD_FIELD, password);
        return this;
    }
    
    /**
     * Click login button
     */
    public DashboardPage clickLogin() {
        log.info("Clicking login button");
        click(Locators.Login.LOGIN_BUTTON);
        return new DashboardPage(driver);
    }
    
    /**
     * Perform login with credentials
     */
    public DashboardPage login(String username, String password) {
        log.info("Performing login for user: {}", username);
        return enterUsername(username)
               .enterPassword(password)
               .clickLogin();
    }
}