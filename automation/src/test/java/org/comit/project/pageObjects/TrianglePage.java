package org.comit.project.pageObjects;

import java.time.Duration;

import org.comit.project.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrianglePage extends BasePage{
	
	@Autowired
	DriverManager driverManager;
	
	By selInputType = By.id("select-inputType");
	
	By txtInputA = By.id("inputA");
	By txtInputB = By.id("inputB");
	By txtInputC = By.id("inputC");
	
	By btnCheck = By.id("checkButton");
	By btnSave = By.id("saveButton");
	
	public void setInputType(String type) {
		WebDriver driver = this.driverManager.getDriver();
		
		WebElement dropdown = driver.findElement(this.selInputType);
		dropdown.click();
		
		WebElement option = driver.findElement(By.xpath("//li[normalize-space(text())='" + type + "']"));
		option.click();
	}
	
	public String getSelectedInputType() {
		WebElement type = this.driverManager.getDriver().findElement(this.selInputType);
		return type.getText().trim();
	}
	
	public void setInputValues(String a, String b, String c) {
		this.driverManager.getDriver().findElement(this.txtInputA).sendKeys(a);
		this.driverManager.getDriver().findElement(this.txtInputB).sendKeys(b);
		this.driverManager.getDriver().findElement(this.txtInputC).sendKeys(c);
	}
	
	public void setInputValues(int a, int b, int c) {
		this.driverManager.getDriver().findElement(this.txtInputA).sendKeys(String.valueOf(a));
		this.driverManager.getDriver().findElement(this.txtInputB).sendKeys(String.valueOf(b));
		this.driverManager.getDriver().findElement(this.txtInputC).sendKeys(String.valueOf(c));
	}
	
	public boolean isFormClear() {
		WebDriver driver = this.driverManager.getDriver();
		WebElement saveButton = driver.findElement(this.btnSave);
		
		//verify: Save button is disabled
		if(saveButton.isEnabled()) {
			return false;
		}
		
		//verify: all input field are clear
		WebElement a = driver.findElement(this.txtInputA);
		WebElement b = driver.findElement(this.txtInputB);
		WebElement c = driver.findElement(this.txtInputC);
		
		if(a.getText().isEmpty() && b.getText().isEmpty() && c.getText().isEmpty())
			return true;
		else
			return false;
	}
	

}
