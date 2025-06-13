package main.java.org.example.pages;

import main.utility.CommonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends CommonUtility {

    public CartPage(WebDriver driver){
        super(driver);
    }

    public By checkOutButton = By.id("checkout");
    public By shoppingCart = By.className("shopping_cart_link");
}
