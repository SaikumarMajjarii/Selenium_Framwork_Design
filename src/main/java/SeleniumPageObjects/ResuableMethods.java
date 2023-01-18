package SeleniumPageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class ResuableMethods {

	
	
	 WebDriver Driver;

	public ResuableMethods(WebDriver Driver)
	{
		
		this.Driver = Driver;
		
	}
	
	
	
	public void WaitForSpecificElement(int Time ,By Locator)
	{
		
		WebDriverWait EW = new WebDriverWait(Driver, Duration.ofSeconds(Time));
		
		EW.until(ExpectedConditions.visibilityOfElementLocated(Locator));

		
	}
	
	
	public void WaittoDisappearforSpecificElement(int Time ,WebElement Locator)
	{
		
		WebDriverWait EW = new WebDriverWait(Driver, Duration.ofSeconds(Time));
		
		EW.until(ExpectedConditions.invisibilityOf(Locator));

		
	}
	 
	
	public void GoToTargetWebsite(String Website)
	{
		
		Driver.get(Website);
	
	}
	
	
	
	
}
