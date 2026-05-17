package com.sdet.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.log4j.Logger;

/**
 * Factory pattern for WebDriver creation
 * Centralizes all driver initialization logic
 * Multiple approaches for different browser configurations
 */
public class DriverFactory {
    private static Logger logger = Logger.getLogger(DriverFactory.class);

    /**
     * Approach 1: Basic driver creation
     * Simple driver creation for Chrome (default)
     */
    public static WebDriver createDriver() {
        return createChromeDriver(false);
    }

    /**
     * Approach 2: Create Chrome driver with options
     */
    public static WebDriver createChromeDriver(boolean headless) {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Option 1: Standard setup
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");

            // Option 2: Headless mode
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            // Option 3: Disable automation detection
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);

            WebDriver driver = new ChromeDriver(options);
            logger.info("Chrome driver created. Headless: " + headless);
            return driver;
        } catch (Exception e) {
            logger.error("Failed to create Chrome driver: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 3: Create Firefox driver
     */
    public static WebDriver createFirefoxDriver(boolean headless) {
        try {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }

            options.addArguments("--start-maximized");
            WebDriver driver = new FirefoxDriver(options);
            logger.info("Firefox driver created. Headless: " + headless);
            return driver;
        } catch (Exception e) {
            logger.error("Failed to create Firefox driver: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 4: Create driver by browser name
     * Flexible approach for parameterized tests
     */
    public static WebDriver createDriver(String browserName, boolean headless) {
        switch (browserName.toLowerCase()) {
            case "firefox":
                return createFirefoxDriver(headless);
            case "chrome":
            default:
                return createChromeDriver(headless);
        }
    }

    /**
     * Approach 5: Create driver with additional options (Chrome)
     * For advanced configurations
     */
    public static WebDriver createChromeDriver(String... args) {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Default options
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-blink-features=AutomationControlled");

            // Additional options passed in
            for (String arg : args) {
                options.addArguments(arg);
            }

            WebDriver driver = new ChromeDriver(options);
            logger.info("Chrome driver created with custom options");
            return driver;
        } catch (Exception e) {
            logger.error("Failed to create Chrome driver: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 6: Quit driver safely
     * Always use this for proper cleanup
     */
    public static void quitDriver(WebDriver driver) {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("Driver quit successfully");
            }
        } catch (Exception e) {
            logger.error("Error quitting driver: " + e.getMessage());
        }
    }

    /**
     * Usage examples:
     *
     * // Approach 1: Basic driver (Chrome)
     * WebDriver driver = DriverFactory.createDriver();
     *
     * // Approach 2: Chrome with headless
     * WebDriver driver = DriverFactory.createChromeDriver(true);
     *
     * // Approach 3: Firefox headless
     * WebDriver driver = DriverFactory.createFirefoxDriver(true);
     *
     * // Approach 4: By browser name
     * WebDriver driver = DriverFactory.createDriver("firefox", false);
     *
     * // Approach 5: Custom options
     * WebDriver driver = DriverFactory.createChromeDriver(
     *     "--incognito",
     *     "--disable-popup-blocking"
     * );
     *
     * // Approach 6: Quit driver
     * DriverFactory.quitDriver(driver);
     *
     * // Example in test:
     * public class LoginTest {
     *     private WebDriver driver;
     *
     *     @BeforeMethod
     *     public void setUp() {
     *         driver = DriverFactory.createDriver();
     *     }
     *
     *     @Test
     *     public void testLogin() {
     *         driver.navigate().to("https://example.com");
     *         // test code
     *     }
     *
     *     @AfterMethod
     *     public void tearDown() {
     *         DriverFactory.quitDriver(driver);
     *     }
     * }
     */
}
