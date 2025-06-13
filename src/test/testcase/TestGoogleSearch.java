package test.testcase;

import main.java.org.example.pages.HomePage;
import main.utility.CreateSession;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestGoogleSearch {
    public WebDriver driver ;
    HomePage homepage  ;

    @Parameters("browser")
    @BeforeClass
    public void createWebDriverSession(String browser){
        driver = CreateSession.getWebDriver(browser);
        homepage = new HomePage(driver);
    }

    @Test
    public void searchOnGoogle(){
        homepage.get("https://www.google.co.in");
        homepage.waitForPageLoad(homepage.getTitle());
        homepage.findElement(homepage.searchbox).sendKeys("selenium POM Model");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
