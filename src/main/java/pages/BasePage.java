package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public abstract class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }
    
    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    protected void click(By locator) {
        waitForElementClickable(locator).click();
    }
    
    protected void safeClick(By locator) {
        try {
            click(locator);
        } catch (Exception e) {
            // Fallback to JavaScript click
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    
    protected void type(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    protected String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }
    
    protected boolean isDisplayed(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    protected boolean isDisplayed(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            WebElement element = customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    protected void waitForPageLoad() {
        wait.until(driver -> 
            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }
    
    protected void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(300); // Animation time
    }
    
    protected void selectDropdownOption(By dropdown, String option) {
        click(dropdown);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
        click(By.xpath("//div[@role='listbox']//span[text()='" + option + "']"));
    }
    
    protected void selectFirstAutocompleteOption(By inputLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
        sleep(500); // Wait for suggestions to stabilize
        click(By.xpath("//div[@role='listbox']//span"));
    }
    
    protected void waitForAllLoadersToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class='oxd-loading-spinner']")
            ));
        } catch (Exception e) {
        }
    }
    
    protected int extractNumberFromText(By locator) {
        try {
            String text = getText(locator).replaceAll("[^0-9]", "");
            return text.isEmpty() ? 0 : Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }
    
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}