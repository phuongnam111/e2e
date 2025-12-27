package utils;

import org.openqa.selenium.By;

public class Locators {
    
    /**
     * Login Page Locators
     */
    public static class Login {
        public static final By USERNAME_FIELD = By.name("username");
        public static final By PASSWORD_FIELD = By.name("password");
        public static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
        public static final By LOGIN_CONTAINER = By.className("orangehrm-login-container");
    }
    
    /**
     * Dashboard Page Locators
     */
    public static class Dashboard {
        public static final By DASHBOARD_HEADER = By.xpath("//h6[text()='Dashboard']");
        public static final By ADMIN_MENU = By.xpath("//span[text()='Admin']");
    }
    
    /**
     * Admin Page Locators
     */
    public static class Admin {
        public static final By ADMIN_HEADER = By.xpath("//h6[text()='Admin']");
        public static final By ADD_USER_BUTTON = By.xpath("//button[normalize-space()='Add']");
    }
    
    /**
     * User Management Page Locators
     */
    public static class UserManagement {
        // Form Add User Locators - Using label-based approach for stability
        // User Role dropdown - first dropdown in the form
        public static final By USER_ROLE_DROPDOWN = By.xpath("//label[text()='User Role']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
        
        // Employee Name input - using placeholder
        public static final By EMPLOYEE_NAME_INPUT = By.xpath("//input[@placeholder='Type for hints...']");
        
        // Status dropdown - second dropdown in the form
        public static final By STATUS_DROPDOWN = By.xpath("//label[text()='Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
        
        // Username input in Add User form - using label
        public static final By USERNAME_INPUT = By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input");
        
        // Password input - first password field
        public static final By PASSWORD_INPUT = By.xpath("//label[text()='Password']/parent::div/following-sibling::div//input[@type='password']");
        
        // Confirm Password input - second password field
        public static final By CONFIRM_PASSWORD_INPUT = By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div//input[@type='password']");
        
        // Save button - in Add User form
        public static final By SAVE_BUTTON = By.xpath("//button[@type='submit']");
        
        // Success message toast
        public static final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class,'oxd-toast--success')]");
        
        // Search Locators - User Management List Page
        // Search username input - in search form section
        public static final By SEARCH_USERNAME_INPUT = By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input");
        
        // Search button - in search form
        public static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");
        
        // Records found text - Multiple strategies for reliability
        public static final By RECORDS_FOUND_TEXT = By.xpath("//span[contains(text(),'Record')]");
        public static final By RECORDS_FOUND_TEXT_ALT = By.xpath("//span[@class='oxd-text oxd-text--span']");
        
        // Dynamic Locators
        public static By dropdownOption(String optionText) {
            return By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']");
        }
        
        public static By userInTable(String username) {
            return By.xpath("//div[@role='row']//div[text()='" + username + "']");
        }
        
        // Autocomplete suggestion - first option
        public static final By FIRST_AUTOCOMPLETE_OPTION = By.xpath("//div[@role='listbox']//span");
        
        // Loader/Spinner that appears during page operations
        public static final By FORM_LOADER = By.xpath("//div[@class='oxd-loading-spinner']");
        
        // Search form container
        public static final By SEARCH_FORM = By.xpath("//form[contains(@class,'oxd-form')]");
    }
    
    /**
     * Common/Shared Locators
     */
    public static class Common {
        public static final By AUTOCOMPLETE_SUGGESTIONS = By.xpath("//div[@role='listbox']//span");
        public static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");
        public static final By CANCEL_BUTTON = By.xpath("//button[normalize-space()='Cancel']");
        public static final By DELETE_BUTTON = By.xpath("//button[normalize-space()='Delete']");
        public static final By EDIT_BUTTON = By.xpath("//button[normalize-space()='Edit']");
        public static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");
        
        // Records count locators
        public static final By RECORDS_TEXT_RECORD = By.xpath("//span[contains(text(),'Record')]");
        public static final By RECORDS_TEXT_FOUND = By.xpath("//span[contains(text(),'Found')]");
        public static final By RECORDS_TEXT_TABLE_PRECEDING = By.xpath("//div[contains(@class,'oxd-table')]//preceding::span[last()]");
        public static final By RECORDS_TEXT_SPAN = By.xpath("//span[@class='oxd-text oxd-text--span']");
        
        // Table locators
        public static final By TABLE_BODY_ROWS = By.xpath("//div[@class='oxd-table-body']//div[@role='row']");
        
        // Dynamic locators
        public static By userInRowByText(String username) {
            return By.xpath("//div[@role='row']//div[contains(text(),'" + username + "')]");
        }
        
        public static By anyElementContainingText(String text) {
            return By.xpath("//*[contains(text(),'" + text + "')]");
        }
    }
}