package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class Extent
{
    WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Java\\ExtentReport\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Given("User is on Home page")
    public void userOnHomePage()
    {

        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    @When("User enters username as {string}")
    public void entersUsername(String userName) throws InterruptedException
    {
        System.out.println("Username Entered");
        driver.findElement(By.name("txtUsername")).sendKeys(userName);

    }
    @When("User enters password as {string}")
    public void entersPassword(String passWord) throws InterruptedException
    {
        System.out.println("Password Entered");
        driver.findElement(By.name("txtPassword")).sendKeys(passWord);
        driver.findElement(By.id("btnLogin")).submit();
    }
    @Then("User should be able to login successfully")
    public void successfulLogin() throws InterruptedException
    {

        String newPageText = driver.findElement(By.id("welcome")).getText();
        System.out.println("newPageText :" + newPageText);
        assertThat(newPageText, containsString("Welcome"));

    }
    @AfterStep
    public void addScreenshot(Scenario scenario) throws IOException
    {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        scenario.attach(fileContent, "image/png", "screenshot");

    }
@After public void teardown()
{
    driver.quit();
}
}
  //New code added