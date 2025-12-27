
package pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Locators;

/**
 * Page Object for OrangeHRM Admin Page
 */
public class AdminPage extends BasePage {
    
    private static final Logger log = LoggerFactory.getLogger(AdminPage.class);
    
    public AdminPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Check if Admin page is displayed
     */
    public boolean isAdminPageDisplayed() {
        log.info("Verifying Admin page is displayed");
        waitForPageLoad();
        return isDisplayed(Locators.Admin.ADMIN_HEADER);
    }
    
    /**
     * Click Add User button to open user creation form
     */
    public UserManagementPage clickAddUser() {
        log.info("Clicking Add User button");
        click(Locators.Admin.ADD_USER_BUTTON);
        return new UserManagementPage(driver);
    }
}