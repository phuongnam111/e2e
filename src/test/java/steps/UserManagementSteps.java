package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.User;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.UserManagementPage;
import utils.ConfigReader;
import utils.DriverManager;

import java.util.List;
import java.util.Map;

public class UserManagementSteps {
    
    private static final Logger log = LoggerFactory.getLogger(UserManagementSteps.class);
    
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;
    private UserManagementPage userManagementPage;
    private User createdUser;
    
    @Given("I am on the OrangeHRM login page")
    public void iAmOnTheLoginPage() {
        driver = DriverManager.getDriver();
        log.info("Navigating to OrangeHRM login page");
        driver.get(ConfigReader.getBaseUrl());
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
    }
    
    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        log.info("Logging in with valid credentials");
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        dashboardPage = loginPage.login(username, password);
    }
    
    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        log.info("Verifying dashboard is displayed");
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), 
            "Dashboard should be displayed after login");
    }
    
    @Given("I navigate to Admin User Management page")
    public void iNavigateToAdminUserManagementPage() {
        log.info("Navigating to Admin User Management page");
        adminPage = dashboardPage.navigateToAdmin();
        Assert.assertTrue(adminPage.isAdminPageDisplayed(), 
            "Admin page should be displayed");
        userManagementPage = adminPage.clickAddUser();
    }
    
    @When("I create a new user with the following details:")
    public void iCreateANewUserWithFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0);
        
        // Generate unique username with timestamp
        String timestamp = String.valueOf(System.currentTimeMillis());
        String username = data.get("Username").replace("<timestamp>", timestamp);
        
        // Create user object
        createdUser = new User();
        createdUser.setEmployeeName(data.get("Employee Name"));
        createdUser.setUsername(username);
        createdUser.setUserRole(data.get("User Role"));
        createdUser.setStatus(data.get("Status"));
        createdUser.setPassword("Test@123");
        
        log.info("Creating user: {}", createdUser);
        userManagementPage.createUser(createdUser);
    }
    
    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        log.info("Verifying user creation success");
        Assert.assertTrue(userManagementPage.isSuccessMessageDisplayed(), 
            "Success message should be displayed after user creation");
    }
    
    @When("I search for the user by username {string}")
    public void iSearchForUserByUsername(String usernameTemplate) {
        String username = createdUser.getUsername();
        log.info("Searching for user: {}", username);
        userManagementPage.searchUserByUsername(username);
    }
    
    @Then("the user should appear in the Records Found table")
    public void theUserShouldAppearInRecordsFoundTable() {
        log.info("Verifying user appears in search results");
        String username = createdUser.getUsername();
        log.info("Looking for user '{}' in table", username);
        
        boolean userExists = userManagementPage.isUserInSearchResults(username);
        
        if (!userExists) {
            log.error("User '{}' NOT found in search results!", username);            
            try {
                int count = userManagementPage.getRecordsFoundCount();
                log.info("Records count: {}", count);
            } catch (Exception e) {
                log.warn("Could not get records count: {}", e.getMessage());
            }
        } else {
            log.info("✓ User '{}' successfully found in table", username);
        }
        
        Assert.assertTrue(userExists, 
            "User '" + username + "' should appear in search results table");
    }
    
    @Then("the user details should be correct")
    public void theUserDetailsShouldBeCorrect() {
        log.info("Verifying user details are correct");
        User foundUser = userManagementPage.getUserFromSearchResults(createdUser.getUsername());
        Assert.assertEquals(foundUser.getUsername(), createdUser.getUsername(), 
            "Username should match");
    }
}