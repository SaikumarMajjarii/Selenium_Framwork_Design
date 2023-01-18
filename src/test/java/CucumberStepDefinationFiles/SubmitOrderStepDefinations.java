package CucumberStepDefinationFiles;

import org.junit.Assert;
import org.openqa.selenium.By;

import SeleniumE2E_TestComponents.BaseTest;
import SeleniumPageObjects.LoginPageObjects;
import SeleniumPageObjects.PaymentPageObjects;
import SeleniumPageObjects.Product_ItemsPageObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SubmitOrderStepDefinations extends BaseTest {
	public Product_ItemsPageObjects AI;	
	public LoginPageObjects LP;
	public PaymentPageObjects PaymentPage;
	
		@Given("^I need to Land on Website$")
	    public void NavigatetoWebiste()  throws Throwable  {		
			
			LP  = LaunchApplication();
	    }
		
		
		
		@When("^I need to Enter (.+) and (.+)$")
	    public void EnterCredentials(String username, String password) {
			
			System.out.println(username);
			LP.EnterCredentials(username,password);
			//LP.WaitForSpecificElement(15, By.cssSelector(".card-body b"));
			
	     
	    }

	    @And("^I Add (.+) to Cart$")
	    public void AddItemstoCart(String productname) throws Throwable {
	       
	    	AI = new Product_ItemsPageObjects(Driver);

	    	AI.AddItemstoCart(productname);

	    	AI.WaittoDisappearforSpecificElement(15, Driver.findElement(By.cssSelector("#toast-container")));

	    	AI.ClickOnCart();

	    	AI.WaitForSpecificElement(15, By.xpath("//*[text()='My Cart']"));

	    	boolean FindProd = AI.VerifyItemsinCart(productname);

	    	Assert.assertTrue(FindProd);

	    	AI.CheckOut();

	    	AI.WaitForSpecificElement(15, By.xpath("//div[normalize-space()='Payment Method']"));
	    }

	  

	    @And("^Checkout (.+) and Submit the Order$")
	    public void checkout_and_submit_the_order(String productname) throws Throwable {
	        
	    	 PaymentPage = new PaymentPageObjects(Driver);

	    	 PaymentPage.SelectCountryFromDD("China");
 
	    	 PaymentPage.PlaceOrder();

	    	 LP.WaitForSpecificElement(15, By.cssSelector(".hero-primary"));
	    	
	    }

	  
	    @Then("{string}  Success Msg should be Validated")
	    public void ValidateSucessMsg (String SuccessMsg) throws Throwable {
	        
	    	String ConfirmMsg = PaymentPage.GetConfirmationMsg();

	    	Assert.assertEquals(SuccessMsg, ConfirmMsg);
	    	super.CloseBrowser();
	    	
	    }
	    
	    
	    
	        @Then("{string}  Error Msg should be Validated")
	    	public void ValidateErrorMsg (String ErrorMessage) throws Throwable {
	        
			String ErrorMsg = LP.IncorrectLogin();		
			Assert.assertEquals(ErrorMessage, ErrorMsg);
			
			super.CloseBrowser();
	    	
	    }
	    
		
	
}


