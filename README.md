# SDET Practice Projects

A **lightweight collection of core utilities and base classes** for test automation. This library provides foundational components for building Selenium-based UI automation frameworks with proper logging and test reporting infrastructure.

## What's Included

This project contains **3 core components**:

1. **BaseTest** - Common test setup/teardown with browser initialization
2. **TestListener** - Custom TestNG listener for detailed test execution logging
3. **BrowserUtils** - Reusable Selenium helper methods with explicit waits

## Tech Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Java | 11+ | Core language |
| Selenium | 4.15.0 | UI automation |
| TestNG | 7.8.1 | Test framework |
| WebDriverManager | 5.6.3 | Automatic driver management |
| Gson | 2.10.1 | JSON handling |
| Log4j | 1.2.17 | Logging |
| Apache Commons | 2.11.0 | File utilities |
| Maven | 3.6+ | Build tool |

## Installation

### Prerequisites

- **Java 11** or higher
- **Maven 3.6.0+**
- **Git**

### Setup

```bash
# Clone the repository
git clone https://github.com/robinsonmartinez23/SDET-Practice-Projects.git
cd SDET-Practice-Projects

# Install dependencies
mvn clean install

# Verify installation
mvn -version
java -version
```

## Project Structure

```
SDET-Practice-Projects/
├── src/
│   ├── main/
│   │   └── java/com/sdet/
│   │       ├── base/
│   │       │   └── BaseTest.java          # Common test setup/teardown
│   │       ├── listeners/
│   │       │   └── TestListener.java      # TestNG event listener
│   │       └── utils/
│   │           └── BrowserUtils.java      # Selenium helper methods
│   │
│   └── test/
│       └── java/                          # Test examples
│
├── pom.xml                                # Maven configuration
├── README.md                              # This file
└── .gitignore
```

## Core Components

### 1. BaseTest - Test Setup Foundation

Located in: `src/main/java/com/sdet/base/BaseTest.java`

Provides common setup and teardown for all tests with automatic browser initialization.

**Supported Browsers:**
- Chrome (default)
- Firefox

**What it does:**
- Initializes WebDriver using WebDriverManager (no manual driver download needed)
- Maximizes browser window
- Provides navigateTo() method
- Automatically quits driver after test
- Logs all actions using Log4j

**Usage:**

```java
import com.sdet.base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test
    public void testLogin() {
        // driver is initialized automatically via @BeforeMethod
        navigateTo("https://opencart.com");
        
        // Use Selenium WebDriver normally
        driver.findElement(By.linkText("Login")).click();
        
        // driver is automatically quit via @AfterMethod
    }
}
```

**Run tests with different browser:**

```bash
# Run with Firefox
mvn test -Dbrowser=firefox

# Run with Chrome (default)
mvn test -Dbrowser=chrome
```

**Methods:**
- `setUp()` - Initializes WebDriver (called before each test)
- `tearDown()` - Quits WebDriver (called after each test)
- `navigateTo(String url)` - Navigate to URL and log action

### 2. TestListener - Test Execution Logging

Located in: `src/main/java/com/sdet/listeners/TestListener.java`

Implements TestNG `ITestListener` interface to log detailed information about test execution.

**What it captures:**
- Test start (with separator)
- Test success (with duration)
- Test failure (with error message and duration)
- Test skipped status
- Test duration in milliseconds

**Usage - Register in TestNG XML:**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Test Suite">
    <listeners>
        <listener class-name="com.sdet.listeners.TestListener"/>
    </listeners>
    
    <test name="Login Tests">
        <classes>
            <class name="com.sdet.tests.LoginTest"/>
        </classes>
    </test>
</suite>
```

**Or use @Listeners annotation:**

```java
import com.sdet.listeners.TestListener;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    
    @Test
    public void testLogin() {
        // Test execution will be logged automatically
    }
}
```

**Sample Log Output:**

```
INFO  - ========== TEST START: testLogin ==========
INFO  - ✓ TEST PASSED: testLogin
INFO  - Test Duration: 5432 ms
```

On failure:
```
ERROR - ✗ TEST FAILED: testLogin
ERROR - Failure Message: java.lang.AssertionError: Expected element not found
ERROR - Test Duration: 3210 ms
```

**Methods:**
- `onTestStart()` - Logs test start
- `onTestSuccess()` - Logs pass and duration
- `onTestFailure()` - Logs failure, error message, and duration
- `onTestSkipped()` - Logs skipped status
- `onTestFailedButWithinSuccessPercentage()` - Logs partial success

### 3. BrowserUtils - Selenium Helper Methods

Located in: `src/main/java/com/sdet/utils/BrowserUtils.java`

Collection of static utility methods for common Selenium operations with **explicit waits built-in**.

**Available Methods:**

#### 1. clickElement(WebDriver driver, WebElement element, int timeoutSeconds)
Waits for element to be clickable, then clicks it.

```java
import com.sdet.utils.BrowserUtils;

BrowserUtils.clickElement(driver, loginButton, 10);
```

#### 2. sendKeys(WebDriver driver, WebElement element, String text, int timeoutSeconds)
Clears element and sends text with explicit wait.

```java
BrowserUtils.sendKeys(driver, emailField, "user@test.com", 10);
```

#### 3. getText(WebDriver driver, WebElement element, int timeoutSeconds)
Gets text from element with visibility wait.

```java
String message = BrowserUtils.getText(driver, successMessage, 10);
```

#### 4. waitForElementVisibility(WebDriver driver, WebElement element, int timeoutSeconds)
Waits for element to be visible.

```java
BrowserUtils.waitForElementVisibility(driver, loadingSpinner, 15);
```

#### 5. selectByText(WebDriver driver, WebElement dropdown, String visibleText)
Selects option from dropdown by visible text.

```java
BrowserUtils.selectByText(driver, countryDropdown, "United States");
```

#### 6. selectByValue(WebDriver driver, WebElement dropdown, String value)
Selects option from dropdown by value attribute.

```java
BrowserUtils.selectByValue(driver, countryDropdown, "US");
```

#### 7. switchToIframe(WebDriver driver, WebElement iframeElement)
Switches to iframe.

```java
BrowserUtils.switchToIframe(driver, paymentIframe);
```

#### 8. switchToMainContent(WebDriver driver)
Switches back to main page from iframe.

```java
BrowserUtils.switchToMainContent(driver);
```

#### 9. acceptAlert(WebDriver driver, int timeoutSeconds)
Waits for alert and accepts it.

```java
BrowserUtils.acceptAlert(driver, 10);
```

#### 10. getAlertText(WebDriver driver, int timeoutSeconds)
Gets alert text before accepting.

```java
String alertMessage = BrowserUtils.getAlertText(driver, 10);
```

#### 11. scrollToElement(WebDriver driver, WebElement element)
Scrolls element into view using JavaScript.

```java
BrowserUtils.scrollToElement(driver, footerElement);
```

#### 12. executeJavaScript(WebDriver driver, String script, Object... args)
Executes custom JavaScript.

```java
BrowserUtils.executeJavaScript(driver, "arguments[0].style.display='block';", element);
```

#### 13. waitForPageLoad(WebDriver driver, int timeoutSeconds)
Waits for page to load (document readyState = "complete").

```java
BrowserUtils.waitForPageLoad(driver, 15);
```

## Complete Example Test

```java
import com.sdet.base.BaseTest;
import com.sdet.listeners.TestListener;
import com.sdet.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    
    @Test
    public void testValidLogin() {
        // Navigate to app
        navigateTo("https://opencart.com");
        
        // Wait for page to load
        BrowserUtils.waitForPageLoad(driver, 15);
        
        // Find elements
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        
        // Click login link
        BrowserUtils.clickElement(driver, loginLink, 10);
        
        // Enter credentials
        BrowserUtils.sendKeys(driver, emailInput, "user@test.com", 10);
        BrowserUtils.sendKeys(driver, passwordInput, "password123", 10);
        
        // Submit form
        BrowserUtils.clickElement(driver, submitButton, 10);
        
        // Wait for success message
        WebElement successMsg = driver.findElement(By.className("success-message"));
        BrowserUtils.waitForElementVisibility(driver, successMsg, 10);
        
        // Assert
        String message = BrowserUtils.getText(driver, successMsg, 10);
        Assert.assertTrue(message.contains("Login successful"));
    }
    
    @Test
    public void testDropdownSelection() {
        navigateTo("https://example.com");
        
        WebElement countryDropdown = driver.findElement(By.id("country"));
        
        // Select by visible text
        BrowserUtils.selectByText(driver, countryDropdown, "United States");
        
        // Verify selection
        String selectedValue = countryDropdown.getAttribute("value");
        Assert.assertEquals(selectedValue, "US");
    }
    
    @Test
    public void testIframeInteraction() {
        navigateTo("https://example.com");
        
        WebElement iframe = driver.findElement(By.id("payment-iframe"));
        BrowserUtils.switchToIframe(driver, iframe);
        
        // Interact with elements inside iframe
        WebElement cardInput = driver.findElement(By.id("card-number"));
        BrowserUtils.sendKeys(driver, cardInput, "4111111111111111", 10);
        
        // Switch back to main content
        BrowserUtils.switchToMainContent(driver);
    }
    
    @Test
    public void testJavaScriptExecution() {
        navigateTo("https://example.com");
        
        WebElement element = driver.findElement(By.id("hidden-element"));
        
        // Make hidden element visible
        BrowserUtils.executeJavaScript(driver, "arguments[0].style.display='block';", element);
        
        // Now interact with it
        BrowserUtils.clickElement(driver, element, 10);
    }
}
```

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=LoginTest
```

### Run Specific Test Method

```bash
mvn test -Dtest=LoginTest#testValidLogin
```

### Run in Parallel

```bash
mvn test -DforkCount=3 -DreuseForks=true
```

### Run with Firefox

```bash
mvn test -Dbrowser=firefox
```

## Logging

Tests automatically log to console via Log4j. Configure logging in `log4j.properties` if needed.

**Log locations:**
- Console output: Real-time test execution logs
- File: Configure in `log4j.properties`

## Common Patterns

### Pattern 1: Wait + Click + Assert

```java
WebElement button = driver.findElement(By.id("submit"));
BrowserUtils.waitForElementVisibility(driver, button, 10);
BrowserUtils.clickElement(driver, button, 10);

WebElement result = driver.findElement(By.className("result"));
String text = BrowserUtils.getText(driver, result, 10);
Assert.assertTrue(text.contains("Success"));
```

### Pattern 2: Form Fill

```java
WebElement nameField = driver.findElement(By.id("name"));
WebElement emailField = driver.findElement(By.id("email"));
WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));

BrowserUtils.sendKeys(driver, nameField, "John Doe", 10);
BrowserUtils.sendKeys(driver, emailField, "john@test.com", 10);
BrowserUtils.clickElement(driver, submitBtn, 10);
```

### Pattern 3: Dropdown Selection + Verification

```java
WebElement dropdown = driver.findElement(By.id("options"));
BrowserUtils.selectByText(driver, dropdown, "Option 1");

String selectedValue = dropdown.getAttribute("value");
Assert.assertEquals(selectedValue, "option1");
```

## Troubleshooting

### NoSuchElementException

**Problem:** Element not found
**Solution:** Use BrowserUtils with explicit waits before finding element

```java
// ❌ NOT RECOMMENDED
WebElement elem = driver.findElement(By.id("elem"));

// ✅ RECOMMENDED
BrowserUtils.waitForElementVisibility(driver, elem, 15);
```

### StaleElementReferenceException

**Problem:** Element reference is stale
**Solution:** Find element again instead of reusing old reference

```java
// ❌ PROBLEMATIC
WebElement button = driver.findElement(By.id("button"));
button.click();
// Page updates...
button.click();  // Stale reference!

// ✅ CORRECT
BrowserUtils.clickElement(driver, button, 10);
// Find element again
button = driver.findElement(By.id("button"));
BrowserUtils.clickElement(driver, button, 10);
```

### Test Timeout

**Problem:** Test takes too long
**Solution:** Increase timeout or optimize waits

```java
// Increase timeout
BrowserUtils.waitForElementVisibility(driver, element, 30);  // 30 seconds

// Or use shorter timeout if element loads quickly
BrowserUtils.clickElement(driver, button, 5);  // 5 seconds
```

### Driver Not Initialized

**Problem:** `NullPointerException` on driver
**Solution:** Ensure test extends BaseTest

```java
// ✅ CORRECT
public class MyTest extends BaseTest {
    @Test
    public void testExample() {
        driver.navigate().to("...");  // driver is initialized
    }
}

// ❌ WRONG
public class MyTest {
    @Test
    public void testExample() {
        driver.navigate().to("...");  // driver is null!
    }
}
```

## Best Practices

1. **Always use BaseTest** - Ensures proper setup/teardown
2. **Use explicit waits** - All BrowserUtils methods include waits
3. **Register TestListener** - Get detailed execution logs
4. **Clear naming** - Use descriptive test and element names
5. **One assertion per test** - Keep tests focused
6. **Handle timeouts** - Set appropriate wait times (10-15 seconds typical)
7. **Log important steps** - Use logger in tests for debugging

## Resources

- [Selenium WebDriver Docs](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [WebDriverManager](https://bonigarcia.dev/webdrivermanager/)
- [Explicit Waits](https://www.selenium.dev/documentation/webdriver/waits/#explicit_wait)

## Future Enhancements

Potential additions to this library:
- BasePage class for Page Object Model
- Database utilities
- File utilities (Excel, CSV, JSON)
- Screenshot capture utility
- Additional browsers (Edge, Safari, headless)
- Configuration management
- Custom assertions

## Author

**Robinson Martinez** - SDET & QA Automation Engineer

- LinkedIn: [linkedin.com/in/robinsonmartinezm](https://www.linkedin.com/in/robinsonmartinezm)
- GitHub: [@robinsonmartinez23](https://github.com/robinsonmartinez23)

## License

This project is provided as-is for educational and professional use.

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0.0 | May 2026 | Initial release: BaseTest, TestListener, BrowserUtils |

---

**Last Updated:** May 2026  
**Current Version:** 1.0.0  
**Status:** Production Ready (3 core components)
