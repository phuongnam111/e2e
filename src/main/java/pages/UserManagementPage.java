package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Locators;

public class UserManagementPage extends BasePage {
    
    private static final Logger log = LoggerFactory.getLogger(UserManagementPage.class);
    private boolean successMessageAppeared = false;
    
    public UserManagementPage(WebDriver driver) {
        super(driver);
    }
    
    public UserManagementPage selectUserRole(String role) {
        log.info("Selecting user role: {}", role);
        selectDropdownOption(Locators.UserManagement.USER_ROLE_DROPDOWN, role);
        return this;
    }
    
    public UserManagementPage selectEmployeeName(String name) {
        log.info("Selecting employee: {}", name);
        waitForAllLoadersToDisappear();
        scrollIntoView(Locators.UserManagement.EMPLOYEE_NAME_INPUT);
        type(Locators.UserManagement.EMPLOYEE_NAME_INPUT, name);
        selectFirstAutocompleteOption(Locators.UserManagement.EMPLOYEE_NAME_INPUT);
        return this;
    }
    
    public UserManagementPage selectStatus(String status) {
        log.info("Selecting status: {}", status);
        selectDropdownOption(Locators.UserManagement.STATUS_DROPDOWN, status);
        return this;
    }
    
    public UserManagementPage enterUsername(String username) {
        log.info("Entering username: {}", username);
        type(Locators.UserManagement.USERNAME_INPUT, username);
        return this;
    }
    
    public UserManagementPage enterPassword(String password) {
        log.info("Entering password");
        type(Locators.UserManagement.PASSWORD_INPUT, password);
        return this;
    }
    
    public UserManagementPage enterConfirmPassword(String password) {
        log.info("Confirming password");
        type(Locators.UserManagement.CONFIRM_PASSWORD_INPUT, password);
        return this;
    }
    
    public UserManagementPage clickSave() {
        log.info("Saving user...");
        waitForAllLoadersToDisappear();
        safeClick(Locators.UserManagement.SAVE_BUTTON);
        
        // Capture success message immediately before it disappears
        successMessageAppeared = isDisplayed(Locators.UserManagement.SUCCESS_MESSAGE);
        log.info(successMessageAppeared ? "✓ Success" : "✗ Failed");
        
        waitForPageLoad();
        waitForAllLoadersToDisappear();
        return this;
    }
    
    public UserManagementPage createUser(User user) {
        log.info("Creating user: {}", user.getUsername());
        successMessageAppeared = false;
        
        return selectUserRole(user.getUserRole())
               .selectEmployeeName(user.getEmployeeName())
               .selectStatus(user.getStatus())
               .enterUsername(user.getUsername())
               .enterPassword(user.getPassword())
               .enterConfirmPassword(user.getPassword())
               .clickSave();
    }
    
    public boolean isSuccessMessageDisplayed() {
        return successMessageAppeared;
    }
    
    public UserManagementPage searchUserByUsername(String username) {
        log.info("Searching user: {}", username);
        waitForAllLoadersToDisappear();
        waitForElementVisible(Locators.UserManagement.SEARCH_USERNAME_INPUT);
        
        type(Locators.UserManagement.SEARCH_USERNAME_INPUT, username);
        safeClick(Locators.UserManagement.SEARCH_BUTTON);
        
        waitForPageLoad();
        waitForAllLoadersToDisappear();
        
        sleep(2000);
        
        return this;
    }
    
    public int getRecordsFoundCount() {
        log.info("Getting records count...");
        
        // Tim kiem thu nhieu record xem co the get dc
        By[] xpathStrategies = {
            Locators.Common.RECORDS_TEXT_RECORD,
            Locators.Common.RECORDS_TEXT_FOUND,
            Locators.Common.RECORDS_TEXT_TABLE_PRECEDING,
            Locators.Common.RECORDS_TEXT_SPAN
        };
        
        String recordsText = null;
        
        for (By locator : xpathStrategies) {
            try {
                if (isDisplayed(locator)) {
                    recordsText = getText(locator);
                    log.info("Found records text using locator: {}", locator);
                    log.info("Records text: {}", recordsText);
                    break;
                }
            } catch (Exception e) {
                log.debug("Locator {} did not work, trying next...", locator);
            }
        }
        
        if (recordsText == null) {
            log.warn("Could not find records count text with any strategy");
            // Check if user exists in table instead
            return 0;
        }
        
        // Extract number from text
        int count = extractNumberFromText(recordsText);
        log.info("Extracted count: {}", count);
        return count;
    }
    
    public boolean isUserInSearchResults(String username) {
        log.info("Checking if user exists: {}", username);        
        waitForPageLoad();
        waitForAllLoadersToDisappear();
        sleep(1500);
        
        boolean exists = false;        
        //way 1: check if user name in tble
        try {
            exists = isDisplayed(Locators.UserManagement.userInTable(username));
            if (exists) {
                log.info("✓ Found user '{}' using direct match", username);
                return true;
            }
        } catch (Exception e) {
            log.debug("Strategy 1 failed: {}", e.getMessage());
        }
        //way 2: check if table has data
        try {
            By tableRows = Locators.Common.TABLE_BODY_ROWS;
            exists = isDisplayed(tableRows);
            if (exists) {
                log.info("✓ Table has data, checking for username '{}'", username);
                
                By userCell = Locators.Common.userInRowByText(username);
                exists = isDisplayed(userCell);
                
                if (exists) {
                    log.info("✓ User '{}' found in table", username);
                    return true;
                }
            }
        } catch (Exception e) {
            log.debug("Strategy 2 failed: {}", e.getMessage());
        }
        
        // way3 3: find name or user name in t.ble
        try {
            By anyMatch = Locators.Common.anyElementContainingText(username);
            exists = isDisplayed(anyMatch);
            if (exists) {
                log.info("✓ User '{}' found using flexible match", username);
                return true;
            }
        } catch (Exception e) {
            log.debug("Strategy 3 failed: {}", e.getMessage());
        }
        
        log.warn("✗ User '{}' NOT found in table with any strategy", username);
        return false;
    }
    
    public User getUserFromSearchResults(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }
    
    //to extract number from text
    private int extractNumberFromText(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        
        try {
            String numbers = text.replaceAll("[^0-9]", "");
            return numbers.isEmpty() ? 0 : Integer.parseInt(numbers);
        } catch (Exception e) {
            log.error("Error extracting number from: {}", text, e);
            return 0;
        }
    }
}