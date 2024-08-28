package Tests.General;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class checkIncome extends checkHealthInsurance
{
    checkHealthInsurance ch = new checkHealthInsurance();
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
    public void validateAgeGroup() throws InterruptedException
    {
        driver.findElement(By.linkText("Tap To Claim")).click();
        Thread.sleep(5000);
        String actual_Validate_Age_Question = driver.findElement(By.xpath("//*[@class='sc-irEpRR jkDlqm']")).getText();
        String expect_Validate_Age_Question = "What is your age group ?";
        Assert.assertEquals(actual_Validate_Age_Question,expect_Validate_Age_Question,"Age Group Does Not Matched!!");
        ArrayList<String> options = new ArrayList<>();
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[1]")).getText());
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[2]")).getText());
        Assert.assertTrue(options.contains("Below 65"),"Age Not Verified Successfully!!");

    }
    @Test(priority = 2)
    public void validateCoverage() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[text()='Below 65']")).click();
        String actual_Validate_Coverage_Question = driver.findElement(By.xpath("//*[@class='sc-irEpRR jkDlqm']")).getText();
        Thread.sleep(5000);
        String expect_Validate_Coverage_Question = "Do you have Medicaid, Medicare, VA, or Tricare Coverage?";
        Assert.assertEquals(actual_Validate_Coverage_Question,expect_Validate_Coverage_Question);
        ArrayList<String> options = new ArrayList<>();
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[1]")).getText());
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[2]")).getText());
        Assert.assertTrue(options.contains("No"),"Coverage Verified Successfully!!");
        Assert.assertTrue(options.contains("Yes"),"Coverage Verified Successfully!!");
    }
    @Test(priority = 3)
    public void validateHealthInsurance() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[text()='No']")).click();
        Thread.sleep(5000);
        String actual_Health_Insurance_Question = driver.findElement(By.xpath("//*[@class='sc-irEpRR jkDlqm']")).getText();
        String expect_Health_Insurance_Question = "Do you have health Insurance?";
        Assert.assertEquals(actual_Health_Insurance_Question,expect_Health_Insurance_Question);
        ArrayList<String> options = new ArrayList<>();
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[1]")).getText());
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[2]")).getText());
        Assert.assertTrue(options.contains("No"),"Health Insurance Verified Successfully!!");
        Assert.assertTrue(options.contains("Yes"),"Health Insurance Verified Successfully!!");
    }
    @Test(priority = 4)
    public void checkIncome() throws InterruptedException
    {
        driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[1]")).click();
        Thread.sleep(5000);
        String actual_Question = driver.findElement(By.xpath("//*[@class='sc-irEpRR jkDlqm']")).getText();
        String expect_Question = "Do you earn over $15,000 annually?";
        Assert.assertEquals(actual_Question,expect_Question);
        ArrayList<String> options = new ArrayList<>();
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[1]")).getText());
        options.add(driver.findElement(By.xpath("(//*[@class='sc-dwYcXH kAnXKn option-button'])[2]")).getText());
        Assert.assertTrue(options.contains("No"),"Income Verified Successfully!!");
        Assert.assertTrue(options.contains("Yes"),"Income Verified Successfully!!");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
