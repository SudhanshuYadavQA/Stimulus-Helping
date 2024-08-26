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

public class LandingPage1
{
    WebDriver driver;
    String baseURL = "https://stimulushelping.com/inderaca10june24?vl_click_id=w8uge0s5jhn3gbm3jmdsikms&generator=internal&type=inbuilt&utm_source=google&utm_medium=%7Bnetwork%7D&utm_campaign=%7Bcampaignid%7D&utm_adset=%7Badgroupid%7D&utm_ad=%7Bcreative%7D&site_id=%7Bplacement%7D&placement=%7Bpubfeed%7D_%7Boriginal_subid%7D&externalclickid=%7Bgclid%7D&device=DESKTOP&browser=Chrome&os=Windows";
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
    public void validateHomePage()
    {
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,baseURL,"You Are Not at the HOME PAGE!!");
    }

    @Test(priority = 2)
    public void clickNavigateToLandingPage1() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@class='sc-fpSrms bdXGJW CMS_ctabutton undefined']")).click();
        String actualURL = driver.getCurrentUrl();
        String expectURL = "https://stimulushelping.com/inderaca10june24-quiz";
        Assert.assertEquals(actualURL,expectURL);
    }
    @Test(priority = 3)
    public void validateQuestion1()
    {
        String actual_question1 = driver.findElement(By.xpath("//*[@class='sc-irEpRR jkDlqm']")).getText();
        String expect_question1 = "What is your age group ?";
        Assert.assertEquals(actual_question1,expect_question1,"Question NOT Verified Successfully!!");
    }
    @Test(priority = 4)
    public void validateOptions() throws InterruptedException
    {
        ArrayList<String> Options = new ArrayList<>();
        Options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[1]")).getText());
        Options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[2]")).getText());
        Thread.sleep(4000);
        Assert.assertTrue(Options.contains("Below 65"),"Text Doesn't Match!!");
        Assert.assertTrue(Options.contains("Above 65"),"Text Doesn't Match!!");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}