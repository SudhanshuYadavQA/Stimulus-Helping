package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.ArrayList;

public class checkCoverage
{
    WebDriver driver;
    String baseURL = "https://stimulushelping.com/inderaca10june24?vl_click_id=w8uge0s5jhn3gbm3jmdsikms&generator=internal&type=inbuilt&utm_source=google&utm_medium=%7Bnetwork%7D&utm_campaign=%7Bcampaignid%7D&utm_adset=%7Badgroupid%7D&utm_ad=%7Bcreative%7D&site_id=%7Bplacement%7D&placement=%7Bpubfeed%7D_%7Boriginal_subid%7D&externalclickid=%7Bgclid%7D&device=DESKTOP&browser=Chrome&os=Windows";
    checkAgeGroup cag = new checkAgeGroup();

    @Parameters("browser")
    @BeforeClass
    public void launchBrowser(String browser) throws InterruptedException
    {
        if(browser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(baseURL);
        Thread.sleep(5000);

    }

    @Test(priority = 1)
    public void verifyQuestion() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@class='sc-fpSrms bdXGJW CMS_ctabutton undefined']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[text()='Below 65']")).click();
        String actual_Question = driver.findElement(By.xpath("//*[@class='sc-irEpRR jkDlqm']")).getText();
        String expect_Question = "Do you have Medicaid, Medicare, VA, or Tricare Coverage?";
        Assert.assertEquals(actual_Question,expect_Question,"Question Doesn't Matches!!");
    }
    @Test(priority = 2)
    public void verifyOptionSet() throws InterruptedException
    {
        ArrayList<String> options = new ArrayList<>();
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[1]")).getText());
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[2]")).getText());
        Thread.sleep(5000);
        Assert.assertTrue(options.contains("No"),"Option Mismatched!!");
        Assert.assertTrue(options.contains("Yes"),"Options Mismatched!!");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}