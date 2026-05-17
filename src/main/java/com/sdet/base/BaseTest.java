package com.sdet.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;

/**
 * Base test class with common setup and teardown
 */
public class BaseTest {
    protected WebDriver driver;
    protected static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                logger.info("Firefox driver initialized");
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("Chrome driver initialized");
        }
        
        driver.manage().window().maximize();
        logger.info("Browser window maximized");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver quit successfully");
        }
    }

    /**
     * Navigate to URL
     */
    protected void navigateTo(String url) {
        driver.get(url);
        logger.info("Navigated to: " + url);
    }
}
