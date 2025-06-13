package main.java.org.example.pages;

import main.utility.CommonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonUtility {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public By username = By.xpath("//input[@id ='user-name']");
    public By password = By.id("password");
    public By loginButton = By.name("login-button");

}
