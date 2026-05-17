# SDET Practice Projects

Advanced SDET (Software Development Engineer in Test) practice projects featuring professional-grade utilities, patterns, and best practices for test automation.

## Tech Stack
- **Language:** Java 11+
- **UI Testing:** Selenium WebDriver 4.x
- **API Testing:** REST Assured 5.x
- **Test Framework:** TestNG 7.x
- **Build Tool:** Maven
- **Utilities:** Apache Commons, GSON, Log4j

## Project Highlights

### 1. **Common Utilities**
Reusable utility classes for:
- File operations (ExcelUtils, CSVUtils)
- String manipulation (StringUtils)
- Date/Time handling (DateUtils)
- Screenshot capture (ScreenshotUtils)
- Browser management (BrowserUtils)
- Database operations (DBUtils)

### 2. **Base Classes**
- **BaseTest:** Common test setup/teardown
- **BasePage:** Common page object methods
- **BaseAPI:** API request/response handling

### 3. **Listeners & Reporters**
- **TestListener:** Custom test reporting
- **RetryListener:** Automatic test retry on failure
- **ScreenshotListener:** Capture on test failure

### 4. **Data Providers**
- Excel-based test data
- JSON-based test data
- Database queries for test data
- CSV file parsing

### 5. **Custom Assertions**
Enhanced assertions beyond TestNG defaults for:
- Element states (visibility, enabled, etc.)
- Page content validation
- API response assertions

### 6. **Design Patterns**
- Singleton pattern (Driver, Database)
- Builder pattern (Test data creation)
- Factory pattern (Driver creation)
- Page Object Model (POM)

## Project Structure
```
src/
├── main/java/com/sdet/
│   ├── utils/                  # Utility classes
│   │   ├── FileUtils.java
│   │   ├── BrowserUtils.java
│   │   ├── ScreenshotUtils.java
│   │   ├── DateUtils.java
│   │   └── DBUtils.java
│   ├── base/                   # Base classes
│   │   ├── BaseTest.java
│   │   ├── BasePage.java
│   │   └── BaseAPI.java
│   ├── listeners/              # TestNG listeners
│   │   ├── TestListener.java
│   │   └── RetryListener.java
│   ├── assertions/             # Custom assertions
│   │   └── CustomAssertions.java
│   ├── factory/                # Factory classes
│   │   └── DriverFactory.java
│   └── data/                   # Test data
└── test/java/com/sdet/
    ├── tests/                  # Example tests
    └── data/                   # Test data files
```

## Features
✅ Reusable utility methods
✅ Cross-browser support
✅ Database integration
✅ Excel/CSV data handling
✅ API + UI test patterns
✅ Advanced retry logic
✅ Custom reporting
✅ Professional logging
✅ CI/CD ready
✅ Best practices examples

## How to Run

### Prerequisites
- Java 11+
- Maven 3.6+

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test
```bash
mvn clean test -Dtest=SampleTest
```

## Best Practices Demonstrated
1. **DRY Principle:** Utilities eliminate code duplication
2. **SOLID Principles:** Factory pattern, dependency injection
3. **Scalability:** Modular structure for large test suites
4. **Maintainability:** Clear separation of concerns
5. **Reporting:** Custom listeners for detailed results
6. **Error Handling:** Robust exception handling
7. **Configuration:** Externalized config management

## Learning Outcomes
This project demonstrates:
- Advanced Java concepts (Generics, Annotations, Reflection)
- Design patterns in test automation
- Database connectivity and validation
- File I/O operations
- Parallel test execution
- Custom TestNG listeners
- Professional coding standards

## Author
Robinson Martinez - SDET/QA Automation Engineer

## Resources
- [Selenium WebDriver](https://www.selenium.dev/)
- [REST Assured](https://rest-assured.io/)
- [TestNG](https://testng.org/)
- [Maven](https://maven.apache.org/)
