package SeleniumE2E_TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumPageObjects.LoginPageObjects;

public class BaseTest {

	public WebDriver Driver;

	public String Website;// ="https://rahulshettyacademy.com/client";

	public LoginPageObjects LP;

	public WebDriver IntializeDriver() throws IOException {

		FileInputStream FIS = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\Selenium_E2E_Resources\\GlobalData.properties");

		Properties GloablProp = new Properties();
		GloablProp.load(FIS);

		String Browser = GloablProp.getProperty("Browser");

		Website = GloablProp.getProperty("URL");

		switch (Browser.toLowerCase()) {
		case "firefox":
			Driver = new FirefoxDriver();
			break;

		case "chrome":
			Driver = new ChromeDriver();
			
			break;

		default:
			System.out.println("browser : " + Browser + " is invalid, Launching Chrome as browser of choice..");
			Driver = new ChromeDriver();
			
		}

		Driver.manage().window().maximize();

		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return Driver;

	}

	public List<HashMap<String, String>> ConvertJson(String FileName) throws IOException {

		String JsonContent = FileUtils.readFileToString(new File(FileName), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> FinalData = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return FinalData;

	}

	public String getScreenshot(String TestCaseName, WebDriver Driver) throws IOException {

		TakesScreenshot TS = (TakesScreenshot) Driver;

		File Source = TS.getScreenshotAs(OutputType.FILE);

		File Store = new File(System.getProperty("user.dir") + "//Test Reports//" + TestCaseName + ".png");

		FileUtils.copyFile(Source, Store);

		return System.getProperty("user.dir") + "//Test Reports//" + TestCaseName + ".png";

	}

	@AfterMethod(alwaysRun = true)
	public void CloseBrowser()

	{
		Driver.quit();
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPageObjects LaunchApplication() throws IOException {

		Driver = IntializeDriver();

		LP = new LoginPageObjects(Driver);
		LP.GoToTargetWebsite(Website);
//		LP.GoToTargetWebsite("https://rahulshettyacademy.com/client");
		return LP;

	}
}
