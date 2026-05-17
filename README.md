# SDET Practice Projects

A **professional-grade collection of reusable utilities, base classes, and design patterns** for advanced test automation. This library demonstrates production-ready SDET practices for both **UI automation** (Selenium) and **API testing** (REST Assured).

Perfect for building scalable, maintainable automation frameworks from scratch.

## Features

✅ **Reusable Utilities**
- BrowserUtils: WebDriver management, waits, actions
- ScreenshotUtils: Automatic capture on failure
- FileUtils: Excel, CSV, JSON operations
- DateUtils: Date/time handling
- DBUtils: Database connectivity & queries

✅ **Base Classes**
- BaseTest: Common setup/teardown, browser initialization
- BasePage: Page Object fundamentals
- BaseAPI: REST client patterns

✅ **Advanced Listeners**
- TestListener: Custom test execution reporting
- RetryListener: Automatic retry on flaky tests
- Screenshot integration on failure

✅ **Design Patterns**
- Singleton (Driver, Database)
- Factory (Driver creation)
- Builder (Test data construction)
- Page Object Model (POM)

✅ **Test Data Management**
- Excel-based parameterization
- JSON configuration files
- CSV data readers
- Database query providers

✅ **Multi-Framework Support**
- Selenium WebDriver 4.15.0 (UI)
- REST Assured 5.3.2 (API)
- TestNG 7.8.1 (Test execution)
- WebDriverManager (Automatic driver management)

✅ **Production Ready**
- Parallel test execution
- Comprehensive logging (Log4j)
- Error handling & recovery
- CI/CD integration ready
- Apache Commons utilities

## Tech Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Java | 11+ | Core language |
| Selenium | 4.15.0 | UI automation |
| REST Assured | 5.3.2 | API testing |
| TestNG | 7.8.1 | Test framework |
| WebDriverManager | 5.6.3 | Driver management |
| Gson | 2.10.1 | JSON parsing |
| Log4j | 1.2.17 | Logging |
| Apache Commons | 2.11.0 | File operations |
| Maven | 3.6+ | Build tool |

## Prerequisites

- **Java 11** or higher
- **Maven 3.6.0+**
- **Git**

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/robinsonmartinez23/SDET-Practice-Projects.git
cd SDET-Practice-Projects
```

### 2. Install Dependencies

```bash
mvn clean install
```

This downloads all dependencies and compiles the utility library.

### 3. Verify Installation

```bash
mvn -version
java -version
```

## Project Structure

```
SDET-Practice-Projects/
├── src/
│   ├── main/
│   │   └── java/com/sdet/
│   │       ├── base/                 # Base classes
│   │       │   ├── BaseTest.java     # Common test setup
│   │       │   ├── BasePage.java     # Page Object base
│   │       │   └── BaseAPI.java      # API client base
│   │       │
│   │       ├── utils/                # Utility classes
│   │       │   ├── BrowserUtils.java         # Selenium helpers
│   │       │   ├── ScreenshotUtils.java     # Screenshot capture
│   │       │   ├── FileUtils.java           # Excel/CSV/JSON
│   │       │   ├── DateUtils.java           # Date operations
│   │       │   ├── StringUtils.java         # String helpers
│   │       │   └── DBUtils.java             # Database operations
│   │       │
│   │       ├── factory/              # Factory classes
│   │       │   └── DriverFactory.java        # WebDriver creation
│   │       │
│   │       ├── listeners/            # TestNG listeners
│   │       │   ├── TestListener.java         # Test reporting
│   │       │   └── RetryListener.java        # Retry mechanism
│   │       │
│   │       ├── assertions/           # Custom assertions
│   │       │   └── CustomAssertions.java     # Enhanced asserts
│   │       │
│   │       ├── enums/                # Enumerations
│   │       │   ├── Browser.java              # Browser types
│   │       │   └── WaitType.java             # Wait strategies
│   │       │
│   │       └── constants/            # Constants
│   │           └── AppConstants.java         # App-wide constants
│   │
│   └── test/
│       └── java/com/sdet/
│           └── tests/                # Example test classes
│
├── pom.xml                           # Maven configuration
├── README.md                         # This file
└── .gitignore

```

## Core Classes & Usage

### BaseTest: Common Test Setup

```java
import com.sdet.base.BaseTest;
import org.testng.annotations.Test;

public class MyTests extends BaseTest {
    
    @Test
    public void testExample() {
        // driver is initialized automatically
        driver.navigate().to("https://opencart.com");
        
        // WebDriverWait is pre-configured
        // Screenshots captured automatically on failure
        // Logging integrated
    }
}
```

**BaseTest provides:**
- WebDriver initialization
- Browser configuration
- Explicit waits setup
- Screenshot on failure
- Test logging
- Teardown & cleanup

### BrowserUtils: Selenium Helpers

```java
import com.sdet.utils.BrowserUtils;
import org.openqa.selenium.By;

public class MyTest {
    
    public void testBrowserUtils() {
        // Wait for element visibility
        BrowserUtils.waitForElement(driver, By.id("element"), 15);
        
        // Click element with wait
        BrowserUtils.clickElement(driver, By.id("button"));
        
        // Scroll to element
        BrowserUtils.scrollToElement(driver, By.id("target"));
        
        // Type text with clear
        BrowserUtils.typeText(driver, By.name("input"), "test data");
        
        // Get element attribute
        String value = BrowserUtils.getAttribute(driver, By.id("elem"), "value");
        
        // Wait for multiple elements
        BrowserUtils.waitForElements(driver, By.xpath("//li"), 10);
        
        // Switch to frame
        BrowserUtils.switchToFrame(driver, By.id("iframe"));
        
        // Switch to alert & accept
        BrowserUtils.acceptAlert(driver);
    }
}
```

**BrowserUtils methods:**
- `waitForElement()` - Explicit wait with timeout
- `clickElement()` - Safe click with wait
- `typeText()` - Clear & type text
- `getAttribute()` - Get element property
- `scrollToElement()` - Scroll into view
- `switchToFrame()` - Switch to iframe
- `acceptAlert()` / `dismissAlert()` - Handle alerts
- `getWindowHandles()` - Multi-window support

### ScreenshotUtils: Automatic Capture

```java
import com.sdet.utils.ScreenshotUtils;
import org.openqa.selenium.TakesScreenshot;

public class MyTest {
    
    public void testScreenshots() {
        // Capture screenshot with timestamp
        String path = ScreenshotUtils.captureScreenshot(driver, "test_failure");
        // Returns: screenshots/test_failure_2026-05-17-04-16-45.png
        
        // Capture with custom path
        ScreenshotUtils.captureScreenshot(driver, "custom/path", "screenshot");
    }
}
```

**Automatically called on:**
- Test failure in listener
- Exception catching
- Manual invocation

### TestListener: Custom Reporting

```java
import com.sdet.listeners.TestListener;
import org.testng.ITestListener;

// Registered in testng.xml or via @Listeners annotation
@Listeners(TestListener.class)
public class MyTests {
    
    @Test
    public void testWithReporting() {
        // All test events logged automatically
        // Screenshots captured on failure
        // Timestamps recorded
    }
}
```

**TestListener handles:**
- Test start/success/failure
- Screenshot attachment
- Logging to console & file
- Execution timing
- Failure reporting

### RetryListener: Automatic Retry

```java
import com.sdet.listeners.RetryListener;
import org.testng.annotations.Test;

@Test(retryAnalyzer = RetryListener.class)
public void testFlakeyElement() {
    // Test will automatically retry on failure
    // Default: 2 retries (configurable)
}
```

Configure retry count:
```java
public int getMaxRetryCount() {
    return 2;  // Number of retries
}
```

### DriverFactory: Browser Initialization

```java
import com.sdet.factory.DriverFactory;

public class TestSetup {
    
    public WebDriver setupDriver(Browser browser) {
        WebDriver driver = DriverFactory.createDriver(browser);
        // Browser launched with optimal options
        return driver;
    }
}
```

**Supported browsers:**
- CHROME
- FIREFOX
- EDGE
- SAFARI

### Custom Assertions

```java
import com.sdet.assertions.CustomAssertions;

public class MyTest {
    
    public void testCustomAssertions() {
        // Element visibility assertion
        CustomAssertions.assertElementVisible(driver, By.id("elem"));
        
        // Element enabled assertion
        CustomAssertions.assertElementEnabled(driver, By.id("button"));
        
        // Text present assertion
        CustomAssertions.assertTextPresent(driver, "Expected Text");
        
        // Page title assertion
        CustomAssertions.assertPageTitle(driver, "Expected Title");
    }
}
```

## Data-Driven Testing

### Excel Data Provider

```java
import com.sdet.utils.FileUtils;

public class DataDrivenTest {
    
    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return FileUtils.readExcel("testdata/users.xlsx", "Sheet1");
    }
    
    @Test(dataProvider = "userData")
    public void testWithExcelData(String email, String password) {
        // Test with parameterized data
    }
}
```

### CSV Data Provider

```java
import com.sdet.utils.FileUtils;

@DataProvider(name = "csvData")
public Object[][] getCsvData() {
    return FileUtils.readCSV("testdata/data.csv");
}
```

### Database Data Provider

```java
import com.sdet.utils.DBUtils;

@DataProvider(name = "dbData")
public Object[][] getDatabaseData() {
    return DBUtils.executeQuery("SELECT * FROM users WHERE active=1");
}
```

## Example Usage in Projects

### UI Automation Example

```java
import com.sdet.base.BaseTest;
import com.sdet.utils.BrowserUtils;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    
    @Test
    public void testLogin() {
        // Navigate
        driver.navigate().to("https://opencart.com");
        
        // Wait & interact
        BrowserUtils.clickElement(driver, By.xpath("//a[contains(text(),'Login')]"));
        BrowserUtils.typeText(driver, By.name("email"), "user@test.com");
        BrowserUtils.typeText(driver, By.name("password"), "password123");
        BrowserUtils.clickElement(driver, By.xpath("//button[@type='submit']"));
        
        // Assert
        assert driver.findElement(By.xpath("//h1[contains(text(),'My Account')]")).isDisplayed();
    }
}
```

### API Testing Example

```java
import com.sdet.base.BaseAPI;
import io.restassured.response.Response;

public class APITests extends BaseAPI {
    
    @Test
    public void testGetUsers() {
        Response response = getRequest("https://api.example.com", "/users");
        
        response.then()
            .statusCode(200)
            .body("users.size()", Matchers.greaterThan(0));
    }
    
    @Test
    public void testCreateUser() {
        String payload = "{\"name\": \"John\", \"email\": \"john@test.com\"}";
        
        Response response = postRequest(
            "https://api.example.com",
            "/users",
            payload
        );
        
        response.then().statusCode(201);
    }
}
```

## Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn test -Dtest=LoginTests
```

### Run Tests in Parallel

```bash
mvn test -DforkCount=3 -DreuseForks=true
```

### Run with Specific Browser

```bash
mvn test -Dbrowser=firefox
```

### Generate Reports

After test execution, view reports in:
- **Console:** Direct output
- **Logs:** `target/logs/`
- **Screenshots:** `target/screenshots/`

## Best Practices Demonstrated

### 1. DRY Principle (Don't Repeat Yourself)
All common operations centralized in utilities. No code duplication.

```java
// ✅ GOOD - Using BrowserUtils
BrowserUtils.waitForElement(driver, By.id("elem"), 15);

// ❌ BAD - Duplicating wait logic
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("elem")));
```

### 2. SOLID Principles

**Single Responsibility:** Each utility handles one concern
- BrowserUtils: Browser actions
- ScreenshotUtils: Screenshots
- FileUtils: File I/O

**Open/Closed:** Easy to extend without modifying existing code
```java
// Extend BaseTest for custom setup
public class MyCustomTest extends BaseTest {
    @Override
    public void setup() {
        super.setup();
        // Custom initialization
    }
}
```

### 3. Factory Pattern
DriverFactory isolates driver creation complexity:

```java
// Client doesn't know HOW driver is created
WebDriver driver = DriverFactory.createDriver(Browser.CHROME);
```

### 4. Singleton Pattern
Database connection as singleton:

```java
Connection conn = DBUtils.getConnection();
// Always returns same instance
```

### 5. Clear Naming Conventions

| Component | Naming | Example |
|-----------|--------|---------|
| Utilities | `*Utils.java` | `BrowserUtils.java` |
| Base Classes | `Base*.java` | `BaseTest.java` |
| Listeners | `*Listener.java` | `TestListener.java` |
| Factories | `*Factory.java` | `DriverFactory.java` |

## CI/CD Integration

### Jenkins Pipeline Example

```groovy
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test -DforkCount=3'
            }
        }
        
        stage('Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/screenshots',
                    reportFiles: 'index.html'
                ])
            }
        }
    }
}
```

## Troubleshooting

### WebDriver Initialization Issues

**Problem:** Driver is null in test
**Solution:** Ensure BaseTest setup method is called

```java
public class MyTest extends BaseTest {
    @BeforeMethod
    public void setup() {
        super.setup();  // Don't forget super
    }
}
```

### Element Not Found

**Problem:** `NoSuchElementException`
**Solution:** Use BrowserUtils with explicit waits

```java
// ❌ NOT RECOMMENDED
WebElement elem = driver.findElement(By.id("elem"));

// ✅ RECOMMENDED
BrowserUtils.waitForElement(driver, By.id("elem"), 15);
```

### Flaky Tests

**Problem:** Tests pass/fail intermittently
**Solution:** Use RetryListener and explicit waits

```java
@Test(retryAnalyzer = RetryListener.class)
public void testFlakeyElement() {
    BrowserUtils.waitForElement(driver, By.id("elem"), 15);
    // More reliable
}
```

### Screenshot Directory Missing

**Problem:** Screenshots not being saved
**Solution:** Create screenshots folder

```bash
mkdir -p target/screenshots
```

Or configure in properties:

```properties
screenshot.dir=target/screenshots
```

## Advanced Features

### Thread-Local WebDriver

BaseTest uses ThreadLocal for parallel execution:

```java
// Each thread gets isolated driver
public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

// In parallel execution
@Test
public void test1() {
    // Uses thread-specific driver
}

@Test
public void test2() {
    // Uses different driver instance
}
```

### Logging Integration

```java
import org.apache.log4j.Logger;

public class MyTest {
    private static Logger logger = Logger.getLogger(MyTest.class);
    
    @Test
    public void testWithLogging() {
        logger.info("Test started");
        logger.debug("Debug information");
        logger.error("Error occurred");
    }
}
```

## Contributing to This Library

To add new utilities:

1. **Create utility class** in `com.sdet.utils`
2. **Follow naming convention:** `*Utils.java`
3. **Write reusable methods** with clear Javadoc
4. **Add example usage** in this README

Example:

```java
package com.sdet.utils;

public class CustomUtils {
    
    /**
     * Performs custom operation
     * @param driver WebDriver instance
     * @param param Parameter description
     * @return Result description
     */
    public static String customMethod(WebDriver driver, String param) {
        // Implementation
        return result;
    }
}
```

## Resources & Learning

- [Selenium WebDriver Docs](https://www.selenium.dev/documentation/)
- [TestNG Tutorial](https://testng.org/doc/)
- [REST Assured Guide](https://rest-assured.io/)
- [Page Object Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [Design Patterns](https://www.digitalocean.com/community/tutorials/design-patterns-java)
- [Java Best Practices](https://www.oracle.com/java/technologies/javase/codeconventions-136091.html)

## Author

**Robinson Martinez** - SDET & QA Automation Engineer

- LinkedIn: [linkedin.com/in/robinsonmartinezm](https://www.linkedin.com/in/robinsonmartinezm)
- GitHub: [@robinsonmartinez23](https://github.com/robinsonmartinez23)

## License

This project is provided as-is for educational and professional use.

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0.0 | May 2026 | Initial release with utilities, base classes, listeners |

---

**Last Updated:** May 2026  
**Current Version:** 1.0.0  
**Status:** Production Ready
