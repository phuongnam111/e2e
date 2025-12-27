# OrangeHRM UI Automation Testing

Automated UI testing project for OrangeHRM using Selenium WebDriver, Cucumber BDD, and Page Object Model pattern.

## 🏗️ Project Structure

```
orangehrm-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/              # Page Object Model classes
│   │       │   ├── BasePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── DashboardPage.java
│   │       │   ├── AdminPage.java
│   │       │   └── UserManagementPage.java
│   │       ├── utils/              # Utility classes
│   │       │   ├── DriverManager.java
│   │       │   └── ConfigReader.java
│   │       └── models/             # Data models
│   │           └── User.java
│   └── test/
│       ├── java/
│       │   ├── runners/
│       │   │   └── TestRunner.java
│       │   └── steps/
│       │       ├── UserManagementSteps.java
│       │       └── Hooks.java
│       └── resources/
│           ├── features/
│           │   └── user_management.feature
│           └── config.properties
├── pom.xml
└── README.md
```

## 🛠️ Technology Stack

- **Java 11**
- **Selenium WebDriver 4.16.1**
- **Cucumber 7.14.1** (BDD Framework)
- **TestNG 7.8.0** (Test Execution)
- **WebDriverManager 5.6.2** (Browser Driver Management)
- **Maven** (Build Tool)
- **SLF4J** (Logging)

## ✨ Key Features

- **Page Object Model (POM)**: Clean separation of page structure and test logic
- **BDD with Cucumber**: Human-readable test scenarios in Gherkin
- **Reusable Components**: BasePage class with common WebDriver operations
- **Configuration Management**: Externalized configuration via properties file
- **Logging**: Comprehensive logging with SLF4J
- **Screenshot on Failure**: Automatic screenshot capture for failed tests
- **Thread-safe Driver Management**: Support for parallel execution

## 📋 Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd orangehrm-automation
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Configuration

Edit `src/test/resources/config.properties` to customize settings:

```properties
# Application Configuration
base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
username=Admin
password=admin123

# Browser Configuration
browser=chrome          # Options: chrome, firefox, edge
headless=false         # Set to true for headless execution
implicit.wait=10
explicit.wait=20
page.load.timeout=30
```

## 🧪 Running Tests

### Run All Tests

```bash
mvn test
```

### Run with Specific Browser

```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Run in Headless Mode

```bash
mvn test -Dheadless=true
```

### Run Specific Feature

```bash
mvn test -Dcucumber.filter.tags="@user_management"
```

## 📊 Test Reports

After test execution, reports are generated in:

- **HTML Report**: `target/cucumber-reports/cucumber.html`
- **JSON Report**: `target/cucumber-reports/cucumber.json`
- **XML Report**: `target/cucumber-reports/cucumber.xml`

Open the HTML report in your browser:

```bash
open target/cucumber-reports/cucumber.html    # macOS
start target/cucumber-reports/cucumber.html   # Windows
```

## 🎯 Test Scenarios

### User Management

**Feature**: Create and verify user in OrangeHRM

**Scenario**: Create and verify a new user
1. Login to OrangeHRM with valid credentials
2. Navigate to Admin → User Management
3. Create a new user with valid data
4. Verify user creation success message
5. Search for the newly created user
6. Verify user appears in search results

## 📝 Code Quality Features

### Clean Code Principles

- **Single Responsibility**: Each class has one clear purpose
- **DRY (Don't Repeat Yourself)**: Reusable methods in BasePage
- **Meaningful Names**: Clear, descriptive method and variable names
- **Small Methods**: Methods are focused and concise
- **Fluent Interface**: Method chaining for readable test code

### Design Patterns

- **Page Object Model**: Encapsulates page elements and actions
- **Factory Pattern**: DriverManager for browser instantiation
- **Singleton Pattern**: ThreadLocal driver instances
- **Builder Pattern**: Fluent method chaining in page objects

### Best Practices

- Explicit waits for element interactions
- Centralized configuration management
- Comprehensive logging for debugging
- Exception handling and error reporting
- Screenshot capture on test failures

## 🔧 Troubleshooting

### Issue: Browser driver not found

**Solution**: WebDriverManager automatically downloads drivers. Ensure internet connection.

### Issue: Element not found

**Solution**: 
- Check if page is fully loaded
- Verify element locators are correct
- Increase explicit wait timeout in config.properties

### Issue: Tests fail in headless mode

**Solution**:
- Some elements may behave differently in headless mode
- Try with `headless=false` first
- Check browser console logs

## 📈 Extending the Framework

### Adding New Page Objects

1. Create a new class extending `BasePage`
2. Define locators as private final fields
3. Implement page-specific methods
4. Use method chaining for fluent interface

```java
public class NewPage extends BasePage {
    private final By element = By.id("element-id");
    
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    public NewPage performAction() {
        click(element);
        return this;
    }
}
```

### Adding New Test Scenarios

1. Create/update `.feature` file in `src/test/resources/features/`
2. Implement step definitions in `steps/` package
3. Run tests using TestRunner

## 🎥 Demo Video

A demo video showing test execution is available at: [link-to-video]

## 👤 Author

Created for ABBANK Quality Engineer Assessment

## 📄 License

This project is created for assessment purposes.

---

**Note**: This framework demonstrates professional automation testing practices suitable for mid to senior-level QE positions.