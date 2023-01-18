package CucumberTestRunnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features ="src/test/java/CucumberConcepts", glue= {"CucumberStepDefinationFiles"},

		monochrome = true, tags = "@Errors", plugin = {"pretty", "html:target/cucumber"})

public class TestRunner extends AbstractTestNGCucumberTests {

}
