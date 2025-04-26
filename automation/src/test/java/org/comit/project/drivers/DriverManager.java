package org.comit.project.drivers;

import org.comit.project.provider.properties.TestProperties;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.annotation.PreDestroy;

@Component
public class DriverManager {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private WebDriver driver;
	
	private boolean initialized;
	
    @Autowired
	private TestProperties properties;
	
    private void createWebDriver() {
						
		this.driver = switch (this.properties.getBrowser().toLowerCase()) {
						case "chrome" -> new ChromeDriver(getChromeOptions());
						case "edge" -> new EdgeDriver(getEdgeOptions());
						case "firefox" -> new FirefoxDriver(getFirefoxOptions());
						default -> throw new IllegalArgumentException("Invalid browser: " + this.properties.getBrowser().toLowerCase());
    };

		this.initialized = true;

		logger.debug("Selenium WebDriver Created! - {} - Headless mode is {}", 
				StringUtils.capitalize(this.properties.getBrowser()), this.properties.isHeadless());	
    }
	
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (properties.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (properties.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (properties.isHeadless()) {
            options.addArguments("--headless");
        }

        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        options.setCapability("proxy", proxy);

        return options;
    }


	public WebDriver getDriver() {
		
		if (!this.initialized) {
			this.createWebDriver();
		}

		return this.driver;
	}

	/*
	 * Creates a new WebDriver 
	 */
	public WebDriver getDriver(boolean newDriver) {
		
		if (newDriver) {
			this.closeDriver();
		}
		
		return this.getDriver();
	}

	public void closeDriver() {
	
		if (this.initialized && this.driver != null) {
		    this.driver.quit();
		}
		
		this.initialized = false;
	}
	
    @PreDestroy
    public void preDestroy() {
        this.closeDriver();
    }
	
}