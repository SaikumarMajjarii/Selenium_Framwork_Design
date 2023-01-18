package SeleniumPageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPageObjects extends ResuableMethods {
	
	
		public WebDriver Driver; 
		
		public OrderPageObjects(WebDriver Driver)
		{
			super(Driver);
			this.Driver = Driver;
			
			PageFactory.initElements(Driver,this);
			
		}
		
		@FindBy(css = "tbody tr td:nth-child(3)")	
		List<WebElement> TotalOrders;	
		
		public int GetTotalOrders()
		{
			
			int OrderSize = TotalOrders.size();
			return OrderSize;
			
		}
		
		
}



