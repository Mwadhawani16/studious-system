package main.utility;

import main.logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonUtility {

    WebDriver driver = null;
    public final int timeout = 45;
    public CommonUtility(WebDriver driver){
        this.driver = driver;
    }

    /**
     * method to open specified url
     * @param url to open
     */
    public void get(String url){
        driver.get(url);
    }

    /**
     * method to navigate to specified page
     * @param url navigation url
     */
    public void navigateTo(String url){
        driver.navigate().to(url);
    }

    /**
     * method to click on an element with action class
     * @param element to be clicked
     */
    public void clickOnElementByAction(By element){
          Actions action = new Actions(driver);
          action.moveToElement(driver.findElement(element));
          action.click().perform();
    }

    /**
     * method to click on an element using javascript
     * @param element to be clicked
     */
    public void clickOnElementByJavascriptExecutor(By element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",element);
    }

    /**
     * method to get title of current webpage
     * @return String name of a webpage
     */
    public String getTitle(){
        String title =driver.getTitle();
        return title;
    }

    /**
     * method to get text of current webpage element
     * @param element to be clicked
     * @return String text of a webpage element
     */
    public String getText(By element){
        String text =driver.findElement(element).getText();
        return text;
    }

    /**
     * method to wait until page is loaded completely
     * @param PageName String name of current webpage
     */
    public void waitForPageLoad(String PageName){
        JavascriptExecutor executor;
        String pageLoadStatus;

        do{
            executor = (JavascriptExecutor) driver;
            pageLoadStatus = (String) executor.executeScript("return document.readyState");
        }while(! pageLoadStatus.equals("complete"));
        Log.info(PageName+" page loaded successfully");
    }

    /**
     *  method verify whether element is present on screen
     * @param targetElement element to be present
     * @return true if element is present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     * or otherwise occupied, and the thread is interrupted, either before
     *  or during the activity.
     */
    public boolean isElementPresent(By targetElement){
        Boolean isPresent = driver.findElements(targetElement).size() >0;
        return isPresent;
    }

    /**
     *  method verify whether element is not present on screen
     * @param targetElement element not to be present
     * @return true if element is not present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     * or otherwise occupied, and the thread is interrupted, either before
     *  or during the activity.
     */
    public boolean isElementNotPresent(By targetElement){
        Boolean isNotPresent = driver.findElements(targetElement).size() ==0;
        return isNotPresent;
    }

    /**
     * method to wait for an element to be visible
     *
     * @param targetElement element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForElementVisibility(By targetElement){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
            wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
            return true;
        }catch(TimeoutException exception){
            System.out.println("Element not visible "+exception.getMessage());
            throw new TimeoutException();
        }
    }

    /**
     *  method to wait for an element to be clickable
     * @param targetElement element to be clickable
     * @return true if element is clickable else throws TimeoutException
     */
    public boolean elementToBeClickable(By targetElement){
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(45));
            wait.until(ExpectedConditions.elementToBeClickable(targetElement));
            return true;
        }catch(TimeoutException exception){
            System.out.println("Clement not clickable "+exception.getMessage());
            throw new TimeoutException();
        }
    }

    /**
     * method to find an element
     * @param locator element to be found
     * @return WebElement if found else throws NoSuchElementException
     */
    public WebElement findElement(By locator){
        try {
            WebElement element = driver.findElement(locator);
            return element;
        }catch(NoSuchElementException ex){
            Log.logError(this.getClass().getName(),"findElement","Element not found");
            throw new NoSuchElementException(ex.getMessage());
        }
    }

    /**
     *  method to find all the elements of specific locator
     * @param locator element to be found
     * @return return the list of elements if found else throws NoSuchElementException
     */
    public List<WebElement> findElements(By locator){
        try{
            List<WebElement> elementList = driver.findElements(locator);
            return elementList;
        }catch(NoSuchElementException ex){
            Log.logError(this.getClass().getName(),"findElements","Element Not Found");
            throw new NoSuchElementException(ex.getMessage());
        }
    }

    /**
     * method to check value contained in list elements and click on it
     * @param fetchedListElements List of fetched value
     * @param valueToBeContained value to be contained in list elements
     */
    public void clickOnElementFromList(List<WebElement> fetchedListElements, String valueToBeContained){
            for(WebElement element : fetchedListElements){
                if(element.getText().toLowerCase().equals(valueToBeContained.toLowerCase())){
                    element.click();
                    return;
                }
            }
    }

    /**
     * method to accept alert,
     * exception is thrown if no alert is present
     */
    public void acceptAlert(){
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }catch(NoAlertPresentException ex){
             throw new NoAlertPresentException();
        }
    }

    /**
     *  method to get message test of alert
     * @return message text which is displayed
     */
    public String getAlertText(){
        String message ;
        try{
            Alert alert = driver.switchTo().alert();
            message = alert.getText();
        }catch(NoAlertPresentException ex){
            throw new NoAlertPresentException();
        }
        return message;
    }

    /**
     * method to select a value from dropdown with index
     * @param selectLocator element with select tag
     * @param valueToBeSelectedindex index to be selected
     */
    public void selectValueFromDropdownWithIndex(By selectLocator,int valueToBeSelectedindex){
        Select select = new Select(driver.findElement(selectLocator));
        select.selectByIndex(valueToBeSelectedindex);
    }

    /**
     * method to select a value from dropdown with value
     * @param selectLocator element with select tag
     * @param valueToBeSelected value to be selected
     */
    public void selectValueFRomDropdownWithValue(By selectLocator,String valueToBeSelected){
        Select select = new Select(driver.findElement(selectLocator));
        select.selectByValue(valueToBeSelected);
    }

    /**
     * method to close browser session
     */
    public void closeSession(){
        driver.close();
    }

    /**
     * method to quit browser session
     */
    public void quitSession(){
        driver.quit();
    }

}
