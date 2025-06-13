package main.java.org.example.pages;

import main.utility.CommonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends CommonUtility {

    public ProductPage(WebDriver driver){
        super(driver);
    }

    public By bagpack_xapth = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
    public By bikelight_xpath = By.id("add-to-cart-sauce-labs-bike-light");
    public By noOfProducts = By.className("shopping_cart_badge");
}
