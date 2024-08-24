package Bases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.time.Duration;

public class homePage
{
    protected WebDriver driver = null ;
    @BeforeClass
    @Parameters("browser")
    public void launchBrowser(String browser)
    {
        switch (browser.toLowerCase())
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid Browser !!");
                System.exit(1);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("http://gotracking.stimulushelping.com/26abc9c6-90bb-475e-8132-b79b5adcf95a?zoneid={pubfeed}_{original_subid}&creative={creative}&placement={placement}&campaignid={campaignid}&device={device}&region={state}&adgroupid={adgroupid}&source=google&medium={network}&loc_interest_ms={loc_interest_ms}&gclid={gclid}");
    }
    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}