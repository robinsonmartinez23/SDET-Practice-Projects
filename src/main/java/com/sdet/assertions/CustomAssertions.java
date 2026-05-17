package com.sdet.assertions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.log4j.Logger;

/**
 * Custom assertions for enhanced test validation
 * Extends TestNG assertions with additional methods
 * Multiple approaches for common assertion patterns
 */
public class CustomAssertions {
    private static Logger logger = Logger.getLogger(CustomAssertions.class);

    /**
     * Approach 1: Assert element is displayed
     */
    public static void assertElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
            logger.info("✓ Element is displayed");
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 2: Assert element is not displayed
     */
    public static void assertElementNotDisplayed(WebElement element) {
        try {
            Assert.assertFalse(element.isDisplayed(), "Element is displayed but should not be");
            logger.info("✓ Element is not displayed");
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 3: Assert element is enabled
     */
    public static void assertElementEnabled(WebElement element) {
        try {
            Assert.assertTrue(element.isEnabled(), "Element is not enabled");
            logger.info("✓ Element is enabled");
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 4: Assert element is disabled
     */
    public static void assertElementDisabled(WebElement element) {
        try {
            Assert.assertFalse(element.isEnabled(), "Element is enabled but should be disabled");
            logger.info("✓ Element is disabled");
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 5: Assert text present in page
     */
    public static void assertTextPresentInPage(WebDriver driver, String text) {
        try {
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains(text), 
                            "Text '" + text + "' not found in page");
            logger.info("✓ Text '" + text + "' found in page");
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 6: Assert element text equals
     */
    public static void assertElementTextEquals(WebElement element, String expectedText) {
        try {
            String actualText = element.getText();
            Assert.assertEquals(actualText, expectedText, 
                              "Element text mismatch. Expected: '" + expectedText + 
                              "' but got: '" + actualText + "'");
            logger.info("✓ Element text matches: " + expectedText);
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 7: Assert element text contains
     */
    public static void assertElementTextContains(WebElement element, String expectedText) {
        try {
            String actualText = element.getText();
            Assert.assertTrue(actualText.contains(expectedText), 
                            "Element text does not contain: '" + expectedText + 
                            "'. Actual text: '" + actualText + "'");
            logger.info("✓ Element text contains: " + expectedText);
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 8: Assert element attribute equals
     */
    public static void assertElementAttribute(WebElement element, String attributeName, String expectedValue) {
        try {
            String actualValue = element.getAttribute(attributeName);
            Assert.assertEquals(actualValue, expectedValue, 
                              "Attribute '" + attributeName + "' mismatch. " +
                              "Expected: '" + expectedValue + "' but got: '" + actualValue + "'");
            logger.info("✓ Attribute '" + attributeName + "' matches: " + expectedValue);
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 9: Assert page title
     */
    public static void assertPageTitle(WebDriver driver, String expectedTitle) {
        try {
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, 
                              "Page title mismatch. Expected: '" + expectedTitle + 
                              "' but got: '" + actualTitle + "'");
            logger.info("✓ Page title matches: " + expectedTitle);
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 10: Assert page title contains
     */
    public static void assertPageTitleContains(WebDriver driver, String expectedPartialTitle) {
        try {
            String actualTitle = driver.getTitle();
            Assert.assertTrue(actualTitle.contains(expectedPartialTitle), 
                            "Page title does not contain: '" + expectedPartialTitle + 
                            "'. Actual title: '" + actualTitle + "'");
            logger.info("✓ Page title contains: " + expectedPartialTitle);
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 11: Assert element count
     */
    public static void assertElementCount(java.util.List<WebElement> elements, int expectedCount) {
        try {
            int actualCount = elements.size();
            Assert.assertEquals(actualCount, expectedCount, 
                              "Element count mismatch. Expected: " + expectedCount + 
                              " but got: " + actualCount);
            logger.info("✓ Element count matches: " + expectedCount);
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Approach 12: Assert string equals (case-insensitive)
     */
    public static void assertStringEqualsIgnoreCase(String actual, String expected) {
        try {
            Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase(), 
                              "String mismatch (case-insensitive). Expected: '" + expected + 
                              "' but got: '" + actual + "'");
            logger.info("✓ Strings match (ignoring case): " + expected);
        } catch (AssertionError e) {
            logger.error("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Usage examples:
     *
     * // Approach 1: Element displayed
     * CustomAssertions.assertElementDisplayed(loginButton);
     *
     * // Approach 2: Element not displayed
     * CustomAssertions.assertElementNotDisplayed(errorMessage);
     *
     * // Approach 3: Element enabled
     * CustomAssertions.assertElementEnabled(submitButton);
     *
     * // Approach 4: Element disabled
     * CustomAssertions.assertElementDisabled(disabledInput);
     *
     * // Approach 5: Text in page
     * CustomAssertions.assertTextPresentInPage(driver, "Welcome");
     *
     * // Approach 6: Element text equals
     * CustomAssertions.assertElementTextEquals(heading, "Login Page");
     *
     * // Approach 7: Element text contains
     * CustomAssertions.assertElementTextContains(message, "Success");
     *
     * // Approach 8: Element attribute
     * CustomAssertions.assertElementAttribute(link, "href", "https://example.com");
     *
     * // Approach 9: Page title equals
     * CustomAssertions.assertPageTitle(driver, "Login - OpenCart");
     *
     * // Approach 10: Page title contains
     * CustomAssertions.assertPageTitleContains(driver, "Login");
     *
     * // Approach 11: Element count
     * List<WebElement> items = driver.findElements(By.className("item"));
     * CustomAssertions.assertElementCount(items, 5);
     *
     * // Approach 12: Case-insensitive string
     * CustomAssertions.assertStringEqualsIgnoreCase("HELLO", "hello");
     */
}
