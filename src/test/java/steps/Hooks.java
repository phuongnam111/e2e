package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DriverManager;

/**
 * Cucumber hooks for test setup and teardown
 */
public class Hooks {
    
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    
    @Before
    public void setUp(Scenario scenario) {
        log.info("Starting scenario: {}", scenario.getName());
        DriverManager.initializeDriver();
    }
    
    @After
    public void tearDown(Scenario scenario) {
        log.info("Finishing scenario: {}", scenario.getName());
        
        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            log.error("Scenario failed, taking screenshot");
            takeScreenshot(scenario);
        }
        
        DriverManager.quitDriver();
    }
    
    /**
     * Take screenshot and attach to report
     */
    private void takeScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            log.info("Screenshot captured successfully");
        } catch (Exception e) {
            log.error("Failed to capture screenshot", e);
        }
    }
}