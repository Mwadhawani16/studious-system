package main.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreateSession {

    public static WebDriver driver;

    public static WebDriver getWebDriver(String browser){
        if(browser.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-features=AutofillServerCommunication,PasswordManagerOnboarding,NotificationTriggers");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--no-first-run");
            options.setExperimentalOption("prefs",new java.util.HashMap<String,Object>(){{
                put("credentials_enable_service",false);
                put("profile.password_manager_enabled",false);
            }});
            driver = new ChromeDriver(options);
        }
        return driver;
    }
}
