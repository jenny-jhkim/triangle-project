package org.comit.project.pageObjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.comit.project.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public class BasePage {
	
	@Autowired
	DriverManager driverManager;
	
	By errorAlert = By.cssSelector(".MuiAlert-message.css-zioonp-MuiAlert-message");
	By tableRows = By.cssSelector("table tbody tr");
	By tableCol = By.tagName("td");
	
	public void openURL(String url) {
		this.driverManager.getDriver().get(url);
	}
	
	public boolean verifyMessage(String result) {

		try {
			this.driverManager.getDriver().findElement(By.xpath("//*[contains(text(), '" + result + "')]"));
		} 
		catch (NoSuchElementException e) {
			return false;
		}

		return true;
	}
	
	public boolean verifyMessageWithWait(String result) {

		try {
			WebDriverWait wait = new WebDriverWait(this.driverManager.getDriver(), Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[text()=\"" + result + "\"]")));
			//System.out.println("Waiting for result: " + result);
		} 
		catch (TimeoutException e) {
			return false;
		}

		return true;
	}
	
	
	public void verifyPageTitle(String expectedTitle) {
		assertThat(this.verifyMessage(expectedTitle))
	    .as("Expected title not found: %s", expectedTitle)
	    .isTrue();
	}
	
	public boolean verifyErrorMessage(String expectedErrorMessage) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(10));
			WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert));
			//System.out.println("Actual error message: " +  alert.getText());
			
			return alert.getText().trim().contains(expectedErrorMessage);
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	public void clickOnLink(String linkText) {
		this.driverManager.getDriver().findElement(By.linkText(linkText)).click();
	}
	
	public void clickOnButton(String buttonText) {
		WebDriver driver = this.driverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement button = null;
		
		
		if(buttonText.equals("CHECK"))
		{
			button = driver.findElement(By.id("checkButton"));
			
		} else if (buttonText.equals("SAVE")) {
			//Wait for the button to be clickable
			button = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(text())='" + buttonText + "']")));
		}
		
		if(button != null) {
			try {
				button.click();
			} catch (ElementClickInterceptedException e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
			}
		} else {
			throw new RuntimeException("Button not found for text: " + buttonText);
		}
	}
	
	public boolean isButtonEnabled(String buttonText) {
		WebDriver driver = this.driverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//find the button element by id
		WebElement button = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(text())='" + buttonText + "']")));
		
		if(button.isEnabled()) {
			 System.out.println(buttonText + " button is enabled.");

			return true;
		} 
		return false;
	}
	
	public List<Map<String, String>> getTableRows(){
		List<Map<String, String>> triangles = new ArrayList<>();
		
		
		List<WebElement> rows = this.driverManager.getDriver().findElements(this.tableRows);
		
		for(WebElement row : rows) {
			List<WebElement> cells = row.findElements(this.tableCol);
			
			if(cells.size()<5) {
				continue; //skip rows that don't have enough cells
			}
			
			Map<String,String> triangle = new HashMap<>();
			triangle.put("ID", cells.get(0).getText().trim());
			triangle.put("Input Type", cells.get(1).getText().trim());
			triangle.put("A", cells.get(2).getText().trim());
			triangle.put("B", cells.get(3).getText().trim());
			triangle.put("C", cells.get(4).getText().trim());
			triangle.put("Triangle Type", cells.get(5).getText().trim());

			triangles.add(triangle);
		}
		
		return triangles;
		
	}
	
	
}
