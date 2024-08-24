package Tests;

import Bases.homePage;
import Pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTests  extends homePage
{
    @Test(priority = 1)
    public void validateLogo() throws InterruptedException
    {
        HomePage HP = new HomePage(driver);
        HP.logoExists();
        Thread.sleep(2500);
    }
}