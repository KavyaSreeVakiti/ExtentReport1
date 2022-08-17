package testRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features/Report.feature"
        ,glue = {"stepDefinition"}
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        ,monochrome = true
        ,publish = true
)

        public class ExtentReport extends AbstractTestNGCucumberTests
{
}
