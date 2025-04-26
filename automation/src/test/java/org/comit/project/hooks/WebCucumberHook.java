package org.comit.project.hooks;

import org.comit.project.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class WebCucumberHook {
	@Autowired
	DriverManager driverManager;
	
	@After("@WebTest")
	public void takeScreenshotAfterScenario(Scenario scenario) {
		WebDriver driver = this.driverManager.getDriver();
		
		if(driver != null) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName() + "_screenshot");
		} else {
			System.out.println("WebDriver is null. Screenshot skipped for: " + scenario.getName());
			
		}
		
	}
}
