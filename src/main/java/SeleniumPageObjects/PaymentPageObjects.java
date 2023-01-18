package SeleniumPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPageObjects extends ResuableMethods {

	WebDriver Driver;

	public PaymentPageObjects(WebDriver Driver) {

		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);

	}

	@FindBy(css = ".hero-primary")
	WebElement ConfirmationMsg;
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement Country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')]")
	List<WebElement> Countries;
	
	
	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement PlaceOrder;
	public void SelectCountryFromDD(String CountryName) {

		Actions AC = new Actions(Driver);

		AC.sendKeys(Country, CountryName).build().perform();

		WaitForSpecificElement(15, By.xpath("//button[contains(@class,'ta-item')]"));

		for (WebElement Country : Countries) {

			if (Country.getText().equalsIgnoreCase(CountryName)) {

				Country.click();
				break;

			}
		}

	}

	
	public void PlaceOrder()
	{
		
		PlaceOrder.click();
		
	}
	
	public String GetConfirmationMsg()
	{
		
		return ConfirmationMsg.getText();
		
	}
	
	
}
