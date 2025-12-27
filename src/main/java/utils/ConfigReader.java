package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads configuration from config.properties file
 */
public class ConfigReader {
    
    private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);
    private static Properties properties;
    
    static {
        loadProperties();
    }
    
    /**
     * Load properties from config.properties file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            
            if (input == null) {
                log.error("Unable to find config.properties");
                throw new RuntimeException("config.properties not found");
            }
            
            properties.load(input);
            log.info("Configuration loaded successfully");
            
        } catch (IOException e) {
            log.error("Error loading configuration", e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }
    
    /**
     * Get property value with optional default
     */
    private static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            log.warn("Property '{}' not found or empty", key);
        }
        return value;
    }
    
    private static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    private static int getIntProperty(String key) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.error("Invalid integer value for property '{}': {}", key, value);
            throw new RuntimeException("Invalid configuration: " + key, e);
        }
    }
    
    private static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
    
    // Application Configuration
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
    
    public static String getUsername() {
        return getProperty("username");
    }
    
    public static String getPassword() {
        return getProperty("password");
    }
    
    // Browser Configuration
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    public static boolean isHeadless() {
        return getBooleanProperty("headless");
    }
    
    public static int getImplicitWait() {
        return getIntProperty("implicit.wait");
    }
    
    public static int getExplicitWait() {
        return getIntProperty("explicit.wait");
    }
    
    public static int getPageLoadTimeout() {
        return getIntProperty("page.load.timeout");
    }
    
    // Test Data
    public static String getTestUserRole() {
        return getProperty("test.user.role");
    }
    
    public static String getTestUserStatus() {
        return getProperty("test.user.status");
    }
}