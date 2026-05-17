# SDET Practice Projects

A **comprehensive SDET utilities library** with reusable base classes, utilities, and design patterns for test automation. This library demonstrates production-ready practices for both **UI automation** (Selenium) and **API testing** (REST Assured).

Built with multiple approaches per utility - showing different ways to solve common test automation problems, similar to how Naveen teaches in his frameworks.

## What's Included

### Core Components (Original 3)
1. **BaseTest** - Common test setup/teardown with browser initialization
2. **TestListener** - Custom TestNG listener for detailed test execution logging
3. **BrowserUtils** - 13 reusable Selenium helper methods with explicit waits

### Extended Components (New 9)
4. **BasePage** - Page Object Model base class with 10 common page operations
5. **ScreenshotUtils** - 5 approaches to screenshot capture (file, base64, base path, file cleanup)
6. **FileUtils** - 8 approaches to file handling (CSV, JSON, write, append, delete, size, Excel placeholder)
7. **DateUtils** - 12 date/time utilities (current date, formatting, calculations, parsing, validation)
8. **StringUtils** - 15 string utility methods (empty check, case conversion, splitting, regex, random generation)
9. **DriverFactory** - 6 approaches to WebDriver creation (Chrome, Firefox, headless, custom options, browser by name)
10. **RetryListener** - 3 retry strategies (fixed count, exponential backoff, conditional retry)
11. **CustomAssertions** - 12 custom assertions (element state, text, attributes, page title, element count, case-insensitive)

**Total: 12 Java classes with 70+ utility methods and multiple approaches per utility**

## Tech Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Java | 11+ | Core language |
| Selenium | 4.15.0 | UI automation |
| REST Assured | 5.3.2 | API testing |
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
│   │       │   └── BaseTest.java              # Test setup/teardown foundation
│   │       │
│   │       ├── pages/
│   │       │   └── BasePage.java              # Page Object base class
│   │       │
│   │       ├── listeners/
│   │       │   ├── TestListener.java          # TestNG event listener
│   │       │   └── RetryListener.java         # Retry strategies (3 approaches)
│   │       │
│   │       ├── utils/
│   │       │   ├── BrowserUtils.java          # Selenium helpers (13 methods)
│   │       │   ├── ScreenshotUtils.java       # Screenshot capture (5 approaches)
│   │       │   ├── FileUtils.java             # File operations (8 approaches)
│   │       │   ├── DateUtils.java             # Date/time utilities (12 methods)
│   │       │   └── StringUtils.java           # String utilities (15 methods)
│   │       │
│   │       ├── assertions/
│   │       │   └── CustomAssertions.java      # Enhanced assertions (12 methods)
│   │       │
│   │       ├── factory/
│   │       │   └── DriverFactory.java         # WebDriver creation (6 approaches)
│   │       │
│   │       ├── enums/                         # (Placeholder for future enums)
│   │       └── constants/                     # (Placeholder for future constants)
│   │
│   └── test/
│       └── java/                              # Test examples
│
├── pom.xml                                    # Maven configuration
├── README.md                                  # This file
└── .gitignore
```

## Component Overview

### 1️⃣ BaseTest - Test Foundation

**File:** `src/main/java/com/sdet/base/BaseTest.java`

Provides common setup and teardown for all tests with automatic browser initialization.

**Features:**
- Automatic WebDriver initialization (Chrome/Firefox)
- Window maximization
- Automatic driver quit after test
- Log4j logging
- navigateTo(String url) method

```java
public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        navigateTo("https://opencart.com");
        // driver is initialized automatically
    }
}
```

### 2️⃣ TestListener - Test Execution Logging

**File:** `src/main/java/com/sdet/listeners/TestListener.java`

Logs detailed information about test execution events.

**Events Captured:**
- Test start with separator
- Test success with duration
- Test failure with error message and duration
- Test skipped status
- Partial success status

```java
@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        // Logged automatically:
        // ========== TEST START: testLogin ==========
        // ✓ TEST PASSED: testLogin
        // Test Duration: 5432 ms
    }
}
```

### 3️⃣ BrowserUtils - Selenium Helpers

**File:** `src/main/java/com/sdet/utils/BrowserUtils.java`

13 static methods for common Selenium operations with **explicit waits built-in**.

**Methods:**
1. `clickElement()` - Wait for clickable, then click
2. `sendKeys()` - Clear and send text with wait
3. `getText()` - Get text with visibility wait
4. `waitForElementVisibility()` - Wait for visible
5. `selectByText()` - Dropdown selection by text
6. `selectByValue()` - Dropdown selection by value
7. `switchToIframe()` - Switch to iframe
8. `switchToMainContent()` - Switch back from iframe
9. `acceptAlert()` - Handle JavaScript alert
10. `getAlertText()` - Get alert message
11. `scrollToElement()` - Scroll into view
12. `executeJavaScript()` - Execute custom JS
13. `waitForPageLoad()` - Wait for page ready state

```java
WebElement emailField = driver.findElement(By.name("email"));
BrowserUtils.sendKeys(driver, emailField, "user@test.com", 10);

WebElement loginButton = driver.findElement(By.id("login"));
BrowserUtils.clickElement(driver, loginButton, 10);
```

### 4️⃣ BasePage - Page Object Model Base

**File:** `src/main/java/com/sdet/pages/BasePage.java`

Base class for all page objects with 10 common page operations.

**Methods:**
1. `waitForElement()` - Wait for visibility
2. `clickElementSafely()` - Click with wait
3. `typeTextSafely()` - Type with wait
4. `getElementText()` - Get text with wait
5. `isElementDisplayed()` - Check visibility
6. `waitForPageTitle()` - Wait for title
7. `scrollToElement()` - Scroll to element
8. `switchToFrame()` - Switch to iframe
9. `switchBackFromFrame()` - Switch from iframe
10. `getElementAttribute()` - Get attribute value

```java
public class LoginPage extends BasePage {
    private WebElement emailInput;
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        typeTextSafely(emailInput, email, 10);
    }

    public void clickLogin() {
        clickElementSafely(submitButton, 10);
    }
}
```

### 5️⃣ ScreenshotUtils - Screenshot Capture

**File:** `src/main/java/com/sdet/utils/ScreenshotUtils.java`

5 approaches to screenshot capture.

**Approaches:**
1. **Basic capture** - Automatic timestamp
2. **Custom path** - Organized by folder
3. **Base64 string** - For HTML report embedding
4. **File object** - For further processing
5. **Clear old** - Clean previous runs

```java
// Approach 1: Simple capture with timestamp
ScreenshotUtils.captureScreenshot(driver, "testLogin");
// Result: target/screenshots/testLogin_2026-05-17_04-35-22.png

// Approach 2: Organized by folder
ScreenshotUtils.captureScreenshot(driver, "LoginTests", "testValidLogin");
// Result: target/screenshots/LoginTests/testValidLogin_2026-05-17_04-35-22.png

// Approach 3: Get base64 for embedding
String base64 = ScreenshotUtils.getScreenshotAsBase64(driver);

// Approach 4: Get file for processing
File screenshot = ScreenshotUtils.getScreenshotAsFile(driver);
```

### 6️⃣ FileUtils - File Operations

**File:** `src/main/java/com/sdet/utils/FileUtils.java`

8 approaches to file handling.

**Approaches:**
1. **Read CSV** - Parse CSV files
2. **Read JSON** - Load JSON as string
3. **Write file** - Create/overwrite file
4. **Append file** - Add to existing file
5. **Check existence** - Validate file exists
6. **Delete file** - Remove file
7. **Get file size** - Byte count
8. **Read Excel** - Placeholder for POI implementation

```java
// Read CSV
String[][] csvData = FileUtils.readCSV("testdata/users.csv");

// Read JSON
String json = FileUtils.readJSON("testdata/config.json");

// Write data
FileUtils.writeToFile("results/report.txt", "Test Results");

// Append to file
FileUtils.appendToFile("logs/test.log", "New log entry");
```

### 7️⃣ DateUtils - Date/Time Utilities

**File:** `src/main/java/com/sdet/utils/DateUtils.java`

12 date/time utility methods.

**Methods:**
1. `getCurrentDate()` - Current date yyyy-MM-dd
2. `getCurrentDateTime()` - Custom format
3. `getCurrentTimeMillis()` - Timestamp
4. `formatDate()` - Format custom date
5. `addDaysToCurrentDate()` - Add days
6. `subtractDaysFromCurrentDate()` - Subtract days
7. `daysBetween()` - Calculate difference
8. `parseDate()` - Parse string to date
9. `isToday()` - Check if today
10. `isFutureDate()` - Check if future
11. `getDayOfWeek()` - Day name
12. `convertToLocalDate()` - Java 8 conversion

```java
String today = DateUtils.getCurrentDate();              // "2026-05-17"
String futureDate = DateUtils.addDaysToCurrentDate(30);  // "2026-06-16"
long days = DateUtils.daysBetween(date1, date2);        // 7
```

### 8️⃣ StringUtils - String Utilities

**File:** `src/main/java/com/sdet/utils/StringUtils.java`

15 string utility methods.

**Methods:**
1. `isEmpty()` - Check null/empty
2. `isNotEmpty()` - Check not empty
3. `trim()` - Remove whitespace
4. `toLowerCase()` - Lowercase
5. `toUpperCase()` - Uppercase
6. `contains()` - Check substring
7. `replaceAll()` - Replace occurrences
8. `split()` - Split by delimiter
9. `join()` - Join array with delimiter
10. `matches()` - Regex pattern match
11. `extractNumbers()` - Get only digits
12. `concatenate()` - Safely join strings
13. `startsWith()` - Check prefix
14. `endsWith()` - Check suffix
15. `generateRandomString()` - Random alphanumeric

```java
String[] parts = StringUtils.split("a,b,c", ",");        // ["a", "b", "c"]
String random = StringUtils.generateRandomString(10);    // "aBcD1eF2gH"
String numbers = StringUtils.extractNumbers("Price: $123.45");  // "12345"
```

### 9️⃣ DriverFactory - WebDriver Creation

**File:** `src/main/java/com/sdet/factory/DriverFactory.java`

6 approaches to WebDriver creation.

**Approaches:**
1. **Basic driver** - Chrome default
2. **Chrome with options** - Headless, notifications, automation
3. **Firefox** - Headless support
4. **By browser name** - Parameterized
5. **Custom options** - Additional arguments
6. **Quit safely** - Proper cleanup

```java
// Approach 1: Default Chrome
WebDriver driver = DriverFactory.createDriver();

// Approach 2: Chrome headless
WebDriver driver = DriverFactory.createChromeDriver(true);

// Approach 3: Firefox
WebDriver driver = DriverFactory.createFirefoxDriver(false);

// Approach 4: By name (parameterized)
WebDriver driver = DriverFactory.createDriver("firefox", true);

// Approach 5: Custom options
WebDriver driver = DriverFactory.createChromeDriver("--incognito", "--disable-popup-blocking");

// Approach 6: Quit driver
DriverFactory.quitDriver(driver);
```

### 🔟 RetryListener - Test Retry Strategies

**File:** `src/main/java/com/sdet/listeners/RetryListener.java`

3 retry strategies for handling flaky tests.

**Strategies:**
1. **Fixed retry count** - Retry N times (default: 2)
2. **Exponential backoff** - Retry with increasing delays (2s, 4s, etc.)
3. **Conditional retry** - Only retry on specific exceptions

```java
// Strategy 1: Simple retry
@Test(retryAnalyzer = RetryListener.class)
public void testFlakeyElement() {
    // Retries up to 2 times on failure
}

// Strategy 2: With backoff
@Test(retryAnalyzer = RetryListener.RetryWithBackoff.class)
public void testWithBackoff() {
    // Retries with increasing delays
}

// Strategy 3: Conditional retry
@Test(retryAnalyzer = RetryListener.ConditionalRetry.class)
public void testConditional() {
    // Only retries on timeout or NoSuchElementException
}
```

### 1️⃣1️⃣ CustomAssertions - Enhanced Assertions

**File:** `src/main/java/com/sdet/assertions/CustomAssertions.java`

12 custom assertions beyond TestNG defaults.

**Assertions:**
1. `assertElementDisplayed()` - Element visible
2. `assertElementNotDisplayed()` - Element hidden
3. `assertElementEnabled()` - Element enabled
4. `assertElementDisabled()` - Element disabled
5. `assertTextPresentInPage()` - Text in page source
6. `assertElementTextEquals()` - Exact text match
7. `assertElementTextContains()` - Partial text match
8. `assertElementAttribute()` - Attribute value
9. `assertPageTitle()` - Page title equals
10. `assertPageTitleContains()` - Partial title match
11. `assertElementCount()` - Element count
12. `assertStringEqualsIgnoreCase()` - Case-insensitive

```java
CustomAssertions.assertElementDisplayed(loginButton);
CustomAssertions.assertElementTextEquals(heading, "Login Page");
CustomAssertions.assertPageTitleContains(driver, "Login");

List<WebElement> items = driver.findElements(By.className("item"));
CustomAssertions.assertElementCount(items, 5);
```

## Complete Example Test

```java
import com.sdet.base.BaseTest;
import com.sdet.pages.BasePage;
import com.sdet.utils.BrowserUtils;
import com.sdet.assertions.CustomAssertions;
import com.sdet.listeners.TestListener;
import com.sdet.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    
    @Test
    public void testCompleteLogin() {
        // Navigate
        navigateTo("https://opencart.com");
        BrowserUtils.waitForPageLoad(driver, 15);
        
        // Find elements
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        
        // Enter credentials with BrowserUtils
        BrowserUtils.sendKeys(driver, emailInput, "user@test.com", 10);
        BrowserUtils.sendKeys(driver, passwordInput, "password123", 10);
        
        // Submit form
        BrowserUtils.clickElement(driver, loginButton, 10);
        
        // Wait for success message
        WebElement successMsg = driver.findElement(By.className("success"));
        BrowserUtils.waitForElementVisibility(driver, successMsg, 10);
        
        // Assert with CustomAssertions
        CustomAssertions.assertElementDisplayed(successMsg);
        CustomAssertions.assertElementTextContains(successMsg, "Welcome");
        CustomAssertions.assertPageTitleContains(driver, "My Account");
        
        // Capture screenshot
        ScreenshotUtils.captureScreenshot(driver, "LoginTests", "testCompleteLogin_success");
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

## Key Design Patterns

### 1. Factory Pattern (DriverFactory)
Centralizes WebDriver creation logic. Easy to extend for new browsers.

### 2. Page Object Model (BasePage)
Encapsulates page-specific locators and operations. Improves maintainability.

### 3. Listener Pattern (TestListener, RetryListener)
Handles cross-cutting concerns (logging, retry) without modifying test code.

### 4. Utility Classes
Reusable static methods reduce duplication across tests.

### 5. Multiple Approaches Per Utility
Each utility shows 3-8 different ways to solve common problems (like Naveen teaches).

## Why Multiple Approaches?

Real-world automation requires flexibility. This library shows:
- **Different ways** to accomplish the same goal
- **Trade-offs** between simplicity and power
- **When to use** each approach based on context
- **Best practices** for each scenario

Example: ScreenshotUtils has 5 approaches:
1. Simple file capture (basic)
2. Organized by folder (maintainable)
3. Base64 string (for reports)
4. File object (for processing)
5. Cleanup (for fresh runs)

Use approach 1 for simple tests, approach 2 for large projects, approach 3 for HTML reports, etc.

## Troubleshooting

### NoSuchElementException

Use BrowserUtils with explicit waits:

```java
BrowserUtils.waitForElementVisibility(driver, element, 15);
```

### StaleElementReferenceException

Refind element instead of reusing old reference:

```java
BrowserUtils.clickElement(driver, button, 10);
// Refind after DOM change
button = driver.findElement(By.id("button"));
```

### Test Timeout

Increase timeout or optimize waits:

```java
BrowserUtils.waitForPageLoad(driver, 30);  // 30 seconds
```

### Driver Not Initialized

Ensure test extends BaseTest:

```java
public class MyTest extends BaseTest {
    @Test
    public void test() {
        driver.navigate().to("...");  // driver is initialized
    }
}
```

## Best Practices

1. **Always extend BaseTest** - Automatic setup/teardown
2. **Use explicit waits** - All BrowserUtils include waits
3. **Register TestListener** - Get execution logs
4. **Leverage BasePage** - Encapsulate page operations
5. **Use CustomAssertions** - Enhanced validation
6. **Clear test names** - Describe what's being tested
7. **One assertion focus** - Keep tests focused
8. **Organize by approach** - Choose right tool for job

## Resources

- [Selenium WebDriver Docs](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [WebDriverManager](https://bonigarcia.dev/webdrivermanager/)
- [Page Object Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [Explicit Waits](https://www.selenium.dev/documentation/webdriver/waits/#explicit_wait)

## Future Enhancements

Potential additions:
- BasePage extended with element interaction helpers
- Database utilities (DBUtils)
- API testing base class (BaseAPI)
- Config management (AppConstants, ConfigReader)
- Additional browsers (Edge, Safari, headless variations)
- Performance testing utilities
- Accessibility testing helpers

## Author

**Robinson Martinez** - SDET & QA Automation Engineer

- LinkedIn: [linkedin.com/in/robinsonmartinezm](https://www.linkedin.com/in/robinsonmartinezm)
- GitHub: [@robinsonmartinez23](https://github.com/robinsonmartinez23)

## License

This project is provided as-is for educational and professional use.

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.1.0 | May 2026 | Added 9 new classes: BasePage, ScreenshotUtils, FileUtils, DateUtils, StringUtils, DriverFactory, RetryListener, CustomAssertions (70+ methods total) |
| 1.0.0 | May 2026 | Initial release: BaseTest, TestListener, BrowserUtils (3 core components) |

---

**Last Updated:** May 2026  
**Current Version:** 1.1.0  
**Status:** Production Ready (12 components, 70+ methods, multiple approaches)
