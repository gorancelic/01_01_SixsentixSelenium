package BaseUtilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserManager {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver createDriver(String browser) {
        // Create driver

        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true);
                driver.set(new ChromeDriver(options));
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                driver.set(new FirefoxDriver(options));
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.setHeadless(true);
                driver.set(new EdgeDriver(options));
            }
            default -> {
                System.out.println("Do not know how to start: " + browser + ", starting chrome.");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            }
        }

        return driver.get();
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

}
