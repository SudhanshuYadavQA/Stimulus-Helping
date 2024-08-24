package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage
{
    WebDriver driver;
    @FindBy(xpath = "//*[@class='sc-fQpRED lkLXph CMS_image']")
    WebElement logo;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void logoExists()
    {
        Assert.assertTrue(logo.isDisplayed());
    }
}