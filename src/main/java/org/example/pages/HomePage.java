package main.java.org.example.pages;

import main.utility.CommonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends CommonUtility {

    public HomePage(WebDriver driver){
        super(driver);
    }

    public By searchbox = By.name("q");
    public By searchbutton = By.xpath("//input[@value='Google Search'][2]");
    public By searchresult = By.xpath("//div[@id='tvcap']//div//div//a");
    public By imagebutton =By.xpath("//a[@data-sc='I']");
    public By imageContainer = By.id("islmp");

}
