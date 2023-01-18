package SeleniumPageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Product_ItemsPageObjects extends ResuableMethods {

	WebDriver Driver;	
	
	
	public Product_ItemsPageObjects(WebDriver Driver)
	{		
		 
		super(Driver);	
		this.Driver = Driver;	
		PageFactory.initElements(Driver,this);
		
	}
	


	@FindBy(css = ".card-body b")
	List<WebElement> AllProducts;

	@FindBy(css = ".card-body button:last-of-type")
	List<WebElement> AddSelected;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement Cart;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> ItemProducts;

	
	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement Checkout;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement Orders;
	
	
	public void AddItemstoCart(String ItemtobeAdded)
	{			
		for (int i = 0; i < AllProducts.size(); i++) 
		{
			if (AllProducts.get(i).getText().equalsIgnoreCase(ItemtobeAdded))
			{

				AddSelected.get(i).click();
				
			
				super.WaitForSpecificElement(15,By.cssSelector("#toast-container"));
				
				break;

			}

		}
	}


	public void ClickOnCart()
	{
		Cart.click();
	

	}
	
	public boolean  VerifyItemsinCart(String ProductName)
	{
				
		 Boolean FindProd =  ItemProducts.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(ProductName));
		 
		 
		 return FindProd;
		
			
	}
	
	public void ClickOnOrders()
	{
		
		Orders.click();
		
		WaitForSpecificElement(15 ,By.cssSelector("tbody"));
		
		
	}
	
	public void CheckOut()
	{
		
		Checkout.click();
	}
	
}
