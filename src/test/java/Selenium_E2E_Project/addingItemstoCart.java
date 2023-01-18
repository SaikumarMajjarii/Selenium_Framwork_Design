package Selenium_E2E_Project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import SeleniumE2E_TestComponents.BaseTest;
import org.openqa.selenium.By;
import SeleniumPageObjects.OrderPageObjects;
import SeleniumPageObjects.PaymentPageObjects;
import SeleniumPageObjects.Product_ItemsPageObjects;

public class addingItemstoCart extends BaseTest {

	public Product_ItemsPageObjects AI;

	// TODO Auto-generated method stub
	@Test(groups = { "CreateOrder" }, enabled = true, dataProvider = "Data Set")
	public void AddToCartScenario(HashMap<String, String> Input) throws IOException {

		LP.EnterCredentials(Input.get("EMAIL"), Input.get("PASSWORD"));
//		LP.EnterCredentials("Saiku@gmail.com", "Saikumar@123");

		LP.WaitForSpecificElement(15, By.cssSelector(".card-body b"));

		AI = new Product_ItemsPageObjects(Driver);

		AI.AddItemstoCart(Input.get("PRODUCTNAME"));

		AI.WaittoDisappearforSpecificElement(15, Driver.findElement(By.cssSelector("#toast-container")));

		AI.ClickOnCart();

		AI.WaitForSpecificElement(15, By.xpath("//*[text()='My Cart']"));

		boolean FindProd = AI.VerifyItemsinCart(Input.get("PRODUCTNAME"));

		Assert.assertTrue(FindProd);

		AI.CheckOut();

		AI.WaitForSpecificElement(15, By.xpath("//div[normalize-space()='Payment Method']"));

		PaymentPageObjects PaymentPage = new PaymentPageObjects(Driver);

		PaymentPage.SelectCountryFromDD("China");

		PaymentPage.PlaceOrder();

		LP.WaitForSpecificElement(15, By.cssSelector(".hero-primary"));

		String ConfirmMsg = PaymentPage.GetConfirmationMsg();

		Assert.assertEquals("THANKYOU FOR THE ORDER.", ConfirmMsg);

//		LP.CloseBrowser();

	}

	@Test
	public void AddToCartDifferentUser() throws IOException {

		LP.EnterCredentials("siddu@gmail.com", "Siddukumar@123");

		LP.WaitForSpecificElement(15, By.cssSelector(".card-body b"));

		AI = new Product_ItemsPageObjects(Driver);

		AI.AddItemstoCart("ADIDAS ORIGINAL");

		AI.WaittoDisappearforSpecificElement(15, Driver.findElement(By.cssSelector("#toast-container")));

		AI.ClickOnCart();

		AI.WaitForSpecificElement(15, By.xpath("//*[text()='My Cart']"));

		boolean FindProd = AI.VerifyItemsinCart("ADIDAS ORIGINAL");

		Assert.assertTrue(FindProd);

		AI.CheckOut();

		AI.WaitForSpecificElement(15, By.xpath("//div[normalize-space()='Payment Method']"));

		PaymentPageObjects PaymentPage = new PaymentPageObjects(Driver);

		PaymentPage.SelectCountryFromDD("China");

		PaymentPage.PlaceOrder();

		LP.WaitForSpecificElement(15, By.cssSelector(".hero-primary"));

		String ConfirmMsg = PaymentPage.GetConfirmationMsg();

		System.out.println(ConfirmMsg);

		Assert.assertEquals("THANKYOU FOR THE ORDER.", ConfirmMsg);

//		LP.CloseBrowser();

	}

	@Test(dependsOnMethods = { "AddToCartDifferentUser" })
	public void GettotalOrders() throws IOException {

		LP.EnterCredentials("siddu@gmail.com", "Siddukumar@123");

		LP.WaitForSpecificElement(15, By.cssSelector(".card-body b"));

		AI = new Product_ItemsPageObjects(Driver);

		AI.ClickOnOrders();
		OrderPageObjects OP = new OrderPageObjects(Driver);

		int Size = OP.GetTotalOrders();

		System.out.println(Size);

//		LP.CloseBrowser();

	}

	@DataProvider(name = "Data Set")
	public Object[][] GetData() throws IOException {

		List<HashMap<String, String>> Data = ConvertJson(
				System.getProperty("user.dir") + "\\src\\test\\java\\Selenium_ExternalDataSources\\Data.json");
		return new Object[][] { { Data.get(0) }, { Data.get(1) } };

	}

}

//@DataProvider(name = "Data Set")
//public Object[][] GetData() {
//
//	HashMap<String, String> FetchData1 = new HashMap<String, String>();
//
//	FetchData1.put("EMAIL", "Saiku@gmail.com");
//	FetchData1.put("PASSWORD", "Saikumar@123");
//	FetchData1.put("PRODUCTNAME", "ADIDAS ORIGINAL");
//
//	HashMap<String, String> FetchData2 = new HashMap<String, String>();
//
//	FetchData2.put("EMAIL", "siddu@gmail.com");
//	FetchData2.put("PASSWORD", "Siddukumar@123");
//	FetchData2.put("PRODUCTNAME", "IPHONE 13 PRO");
//
//	return new Object[][] { { FetchData1}, {FetchData2} };
//
//}

//1 Type of Data Iteration
//@DataProvider(name = "Data Set")
//public Object[][] GetData() {
//
//	return new Object[][] { {"Saiku@gmail.com","Saikumar@123"}, {"siddu@gmail.com","Siddukumar@123"} };
//}
