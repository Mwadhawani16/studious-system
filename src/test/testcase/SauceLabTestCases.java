package test.testcase;

import main.java.org.example.pages.CartPage;
import main.java.org.example.pages.LoginPage;
import main.java.org.example.pages.ProductPage;
import main.logger.Log;
import main.utility.Assertions;
import main.utility.CreateSession;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceLabTestCases {

    public WebDriver driver;
    LoginPage login;
    ProductPage productPage;
    Assertions assertion;
    CartPage cartPage;

    @Parameters("browser")
    @BeforeClass
    public void createWebDriverSession(String browser){
        driver = CreateSession.getWebDriver(browser);
        login = new LoginPage(driver);
        productPage = new ProductPage(driver);
        assertion = new Assertions(driver);
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1)
    public void loginTest(){
        login.get("https://www.saucedemo.com/");
        String title = login.getTitle();
        Log.info("title :"+title);
        assertion.verifyEquals("Swag Labs",title,"Tile Matched");
        login.findElement(login.username).sendKeys("standard_user");
        login.findElement(login.password).sendKeys("secret_sauce");
        login.findElement(login.loginButton).click();
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void addProductsToCart(){
        productPage.waitForElementVisibility(productPage.bagpack_xapth);
        productPage.findElement(productPage.bagpack_xapth).click();
        productPage.findElement(productPage.bikelight_xpath).click();
        String noOfProducts = productPage.getText(productPage.noOfProducts);
        assertion.verifyEquals("2",noOfProducts,"No of Products added to Cart matched");
    }

    @Test(dependsOnMethods = {"addProductsToCart"})
    public void checkoutProduct(){
        String cartTitle = cartPage.getTitle();
        assertion.verifyEquals(cartTitle,"Your Cart","Cart Page Title Matched");
        cartPage.waitForElementVisibility(cartPage.shoppingCart);
        cartPage.findElement(cartPage.shoppingCart).click();
        cartPage.waitForElementVisibility(cartPage.checkOutButton);
        cartPage.findElement(cartPage.checkOutButton).click();
    }

    @AfterClass
    public void tearDown(){
        login.closeSession();
    }
}
