package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class HomePage
{
    WebDriver driver;

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
        String baseURL = "https://stimulushelping.com/inderaca10june24?vl_click_id=w8uge0s5jhn3gbm3jmdsikms&generator=internal&type=inbuilt&utm_source=google&utm_medium=%7Bnetwork%7D&utm_campaign=%7Bcampaignid%7D&utm_adset=%7Badgroupid%7D&utm_ad=%7Bcreative%7D&site_id=%7Bplacement%7D&placement=%7Bpubfeed%7D_%7Boriginal_subid%7D&externalclickid=%7Bgclid%7D&device=DESKTOP&browser=Chrome&os=Windows";
        driver.get(baseURL);
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void checkLogoExists()
    {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='sc-fQpRED lkLXph CMS_image']")).isDisplayed(),"Logo Doesn't Exist!!");
    }

    @Test(priority = 2)
    public void validateText1()
    {
        String actual_Text = driver.findElement(By.xpath("//*[@id='Ele-f4613486-291b-410e-a344-1254581ae7de']/h5")).getText();
        String expect_Text = "68,872 Americans Benefited Last Week";
        Assert.assertEquals(actual_Text,expect_Text,"Text Doesn't Match!!");
    }

    @Test(priority = 3)
    public void validateText2()
    {
        String actual_Text = driver.findElement(By.xpath("//*[@id='Ele-a20387ed-97a9-4a92-bbdd-9a347fea9f6e']/h1")).getText();
        String expect_Text = "Enhanced Subsidy Program for Low-Income Americans";
        Assert.assertEquals(actual_Text,expect_Text,"Text Doesn't Matches!!");
    }

    @Test(priority = 4)
    public void validateText3()
    {
        String actual_Text = driver.findElement(By.xpath("//*[@id='Ele-63cb25e0-486e-4a19-a0e3-b209cf30342d']/h4")).getText();
        String expect_Text = "Hurry, offer ends soon!";
        Assert.assertEquals(actual_Text,expect_Text,"Text Doesn't Matches!!");
    }

    @Test(priority = 5)
    public void validateText4()
    {
        String actual_Text = driver.findElement(By.xpath("//*[@id='Ele-b197a206-14c2-4768-bf90-e55996f70c9f']/div")).getText();
        String expect_Text = "Tap To Claim";
        Assert.assertEquals(actual_Text,expect_Text,"Text Doesn't Matches!!");
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}