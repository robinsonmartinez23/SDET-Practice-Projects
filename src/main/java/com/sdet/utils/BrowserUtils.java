package com.sdet.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

/**
 * Browser utility methods for common Selenium operations
 */
public class BrowserUtils {

    /**
     * Wait for element to be clickable and click it
     */
    public static void clickElement(WebDriver driver, WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    /**
     * Send keys to element with explicit wait
     */
    public static void sendKeys(WebDriver driver, WebElement element, String text, int timeoutSeconds) {
        WebElement el = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
        el.clear();
        el.sendKeys(text);
    }

    /**
     * Get text from element with wait
     */
    public static String getText(WebDriver driver, WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element))
                .getText();
    }

    /**
     * Wait for element visibility
     */
    public static void waitForElementVisibility(WebDriver driver, WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Select option by visible text
     */
    public static void selectByText(WebDriver driver, WebElement dropdown, String visibleText) {
        new Select(dropdown).selectByVisibleText(visibleText);
    }

    /**
     * Select option by value
     */
    public static void selectByValue(WebDriver driver, WebElement dropdown, String value) {
        new Select(dropdown).selectByValue(value);
    }

    /**
     * Switch to iframe by WebElement
     */
    public static void switchToIframe(WebDriver driver, WebElement iframeElement) {
        driver.switchTo().frame(iframeElement);
    }

    /**
     * Switch back to main content
     */
    public static void switchToMainContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    /**
     * Accept JavaScript alert
     */
    public static void acceptAlert(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.alertIsPresent())
                .accept();
    }

    /**
     * Get JavaScript alert text
     */
    public static String getAlertText(WebDriver driver, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.alertIsPresent())
                .getText();
    }

    /**
     * Scroll to element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Execute JavaScript
     */
    public static Object executeJavaScript(WebDriver driver, String script, Object... args) {
        return ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script, args);
    }

    /**
     * Wait for page to load (document ready state)
     */
    public static void waitForPageLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }
}
