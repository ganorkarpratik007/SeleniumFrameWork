package Cucmber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/resources/SubmitOrder.feature",
        glue = "StepDefinations",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber.html"
        }
)
public class testNGRunner extends AbstractTestNGCucumberTests {

}