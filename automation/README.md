# 🧪 Comit project - Triangle Automation

This is a Selenium-based test automation project that uses **Spring Boot**, **Cucumber**, and **JUnit 5** to automate and validate triangle data on a practice site.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot**
- **Cucumber** (BDD)
- **JUnit 5 Platform**
- **Selenium WebDriver**
- **AssertJ** for fluent assertions
- **Maven** for build and dependency management
- **Allure** for reporting

---

## 📂 Project Structure

```
src/test/java/
├── org.comit.project.config                 # Spring cucumber, jackson config
├── org.comit.project.contexts               # TestContext for sharing data
├── org.comit.project.drivers                # WebDriver management (DriverManager)
├── org.comit.project.hooks                  # WebDriver screenshot
├── org.comit.project.pageObjects            # Page Object Model (for Selenium)
├── org.comit.project.provider               # YML-based test property loader
├── org.comit.project.restObjects            # EndPoint Object Model (for Rest Assured)
├── org.comit.project.step.definitions.page  # Step definitions for cucumber
├── org.comit.project.runners                # Cucumber test runner
```

---

## ⚙️ Configuration

The `config.yml` file located in `src/test/resources/` defines the test parameters:

```yaml
# config.yml
test:
  backend: "http://localhost:8080"
  frontend: "http://localhost:3000"
  browser: chrome
  headless: false  
  timeout: 30
```

Make sure your active browser driver (e.g., ChromeDriver) is set up in your system `PATH`.

---

## 🧪 Running Tests

Run all tests with:

```bash
mvn test
```

Or directly from your IDE using the `RunCucumberTest` class:

```java
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("org/comit/project/features/page/triangleHome.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.comit.project")
public class RunCucumberTest {}
```

---

## 🧾 Sample Feature

```gherkin
Feature: Login in Practice Site

  Scenario: Home page default login
    Given User is on the login page "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
    When User login into application with "user" and password "user"
    And User clicks Submit
    Then User sees the message "Login successful"
```

---

## 📊 Reports

Generate Allure reports:

```bash
mvn allure:report
```

Then view the report:

```bash
allure serve target/allure-results
```

---

## Spring & Cucumber Setup

Ensure you have the context configuration for Cucumber:

```java
@SpringBootTest
@CucumberContextConfiguration
public class CucumberConfig {}
```

---

## 🧠 Notes

- The test browser is dynamically selected via the `config.yml`.
- Headless mode is supported for CI environments.
- Environment loading is handled by `PropertiesProvider`.
