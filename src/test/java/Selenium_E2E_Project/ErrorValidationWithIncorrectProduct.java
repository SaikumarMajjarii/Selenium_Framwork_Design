package Selenium_E2E_Project;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import SeleniumE2E_TestComponents.BaseTest;

public class ErrorValidationWithIncorrectProduct  extends BaseTest{

	
	@Test(enabled=true)
	public void AddToCartScenario() throws IOException
	{
		
		LP.EnterCredentials("Saiku@gmail.com", "Saikumar@121");	
		String ErrorMsg = LP.IncorrectLogin();		
		Assert.assertEquals("Incorrect email or password.", ErrorMsg);
//		LP.CloseBrowser();
		
		//tbody tr td:nth-child(3)	
	}
	
	
}
