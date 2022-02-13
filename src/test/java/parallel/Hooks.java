package parallel;

import BaseUtilities.BrowserManager;
import BaseUtilities.DataReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hooks {

    public WebDriver driver;
    DataReader dataReader = new DataReader();

    @Before
    public void setUp() {
        BrowserManager manager = new BrowserManager();
        driver = manager.createDriver(dataReader.getPropertyValue("browser"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(dataReader.getPropertyValue("staffminerUrl"));
    }

    @After(order=1)
    public void takeScreenshot(Scenario scenario) {
     //   if (scenario.isFailed()) {
            String screenshotName = scenario.getName();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", screenshotName);
     //   }
    }

    @After(order=0)
    public void tearDown() {
        driver.quit();
    }


}