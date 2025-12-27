
package pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Locators;

/**
 * Page Object for OrangeHRM Dashboard Page
 */
public class DashboardPage extends BasePage {
    
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Check if dashboard is displayed
     */
    public boolean isDashboardDisplayed() {
        log.info("Verifying dashboard is displayed");
        waitForPageLoad();
        return isDisplayed(Locators.Dashboard.DASHBOARD_HEADER);
    }
    
    /**
     * Navigate to Admin page
     */
    public AdminPage navigateToAdmin() {
        log.info("Navigating to Admin page");
        click(Locators.Dashboard.ADMIN_MENU);
        return new AdminPage(driver);
    }
}