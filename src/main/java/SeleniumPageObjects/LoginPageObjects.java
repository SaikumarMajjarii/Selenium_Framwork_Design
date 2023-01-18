package SeleniumPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects extends ResuableMethods {


	 WebDriver Driver;
	 
	 
	public LoginPageObjects(WebDriver Driver)
	{
		
		super(Driver);
		this.Driver = Driver;
		
		PageFactory.initElements(Driver,this);
		
	}
	
	@FindBy(css = "input#userEmail")
	WebElement Email;
	

	@FindBy(css = "input[placeholder='enter your passsword']")
	WebElement Pass;
	

	@FindBy(css = ".login-btn")
	WebElement Login;
	
	@FindBy(css = "div[class *='flyInOut']")
	WebElement ErrorMsg;
	
	public void EnterCredentials(String EmailID,String Password)
	{
		Email.sendKeys(EmailID);
		Pass.sendKeys(Password);
		Login.click();
	}
	
	public String IncorrectLogin()
	{
		
		WaitForSpecificElement(20,By.cssSelector("div[class*='flyInOut']"));
		
		 String ErrorMessage = ErrorMsg.getText();
		 
		 return ErrorMessage;
		
	}
	
	
	
	
}
