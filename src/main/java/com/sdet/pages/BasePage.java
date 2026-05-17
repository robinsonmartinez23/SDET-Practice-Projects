package com.sdet.pages;

import com.sdet.utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;

/**
 * Base Page Object class with common page operations
 * All page objects should extend this class
 */
public class BasePage {
    protected WebDriver driver;
    protected static Logger logger = Logger.getLogger(BasePage.class);

    /**
     * Constructor - initializes driver for page
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Approach 1: Simple wait for element visibility
     * Used when you just need to verify element is visible before proceeding
     */
    protected void waitForElement(WebElement element, int timeout) {
        BrowserUtils.waitForElementVisibility(driver, element, timeout);
        logger.info("Element is visible after waiting " + timeout + " seconds");
    }

    /**
     * Approach 2: Click element with built-in wait
     * Combines wait + click in single method for cleaner page methods
     */
    protected void clickElementSafely(WebElement element, int timeout) {
        BrowserUtils.clickElement(driver, element, timeout);
        logger.info("Element clicked successfully");
    }

    /**
     * Approach 3: Type text with wait
     * Clears existing text and enters new text with explicit wait
     */
    protected void typeTextSafely(WebElement element, String text, int timeout) {
        BrowserUtils.sendKeys(driver, element, text, timeout);
        logger.info("Text entered: " + text);
    }

    /**
     * Approach 4: Get text from element
     * Returns text with built-in visibility wait
     */
    protected String getElementText(WebElement element, int timeout) {
        String text = BrowserUtils.getText(driver, element, timeout);
        logger.info("Text retrieved: " + text);
        return text;
    }

    /**
     * Approach 5: Check if element is displayed
     * Simple visibility check without wait
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.error("Element not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Approach 6: Wait for page title
     * Useful when navigating to new pages
     */
    protected void waitForPageTitle(String expectedTitle, int timeout) {
        BrowserUtils.waitForPageLoad(driver, timeout);
        String actualTitle = driver.getTitle();
        logger.info("Page title: " + actualTitle);
    }

    /**
     * Approach 7: Scroll to element
     * Used for elements below fold
     */
    protected void scrollToElement(WebElement element) {
        BrowserUtils.scrollToElement(driver, element);
        logger.info("Scrolled to element");
    }

    /**
     * Approach 8: Switch to iframe
     * Common pattern for iframe interaction
     */
    protected void switchToFrame(WebElement iframeElement) {
        BrowserUtils.switchToIframe(driver, iframeElement);
        logger.info("Switched to iframe");
    }

    /**
     * Approach 9: Switch back from iframe
     * Always call after iframe interaction complete
     */
    protected void switchBackFromFrame() {
        BrowserUtils.switchToMainContent(driver);
        logger.info("Switched back to main content");
    }

    /**
     * Approach 10: Get element attribute
     * Useful for getting href, value, data-* attributes
     */
    protected String getElementAttribute(WebElement element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        logger.info("Attribute '" + attributeName + "' value: " + attributeValue);
        return attributeValue;
    }

    /**
     * Example Page Object extending BasePage
     * 
     * public class LoginPage extends BasePage {
     *     private WebElement emailInput;
     *     private WebElement passwordInput;
     *     private WebElement loginButton;
     *
     *     public LoginPage(WebDriver driver) {
     *         super(driver);
     *     }
     *
     *     public void enterEmail(String email) {
     *         typeTextSafely(emailInput, email, 10);
     *     }
     *
     *     public void enterPassword(String password) {
     *         typeTextSafely(passwordInput, password, 10);
     *     }
     *
     *     public AccountsPage clickLogin() {
     *         clickElementSafely(loginButton, 10);
     *         return new AccountsPage(driver);
     *     }
     * }
     */
}
