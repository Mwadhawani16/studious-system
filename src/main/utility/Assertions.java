package main.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.Reporter;

public class Assertions {

    public boolean testcaseStatus = true;
    WebDriver driver ;
    File file ;
    private String testScreenshotDir;

    public Assertions(WebDriver driver){
        file = new File("");
        testScreenshotDir = file.getAbsoluteFile()+"//src//test//java//outputfiles//";
        this.driver = driver;
    }

    /**
     * method to take screenshot
     * @return path where screenshot has been saved
     */
    public String screenshot(){
        String screenshotPath = "screenshot_"+new SimpleDateFormat("MM-dd-yyyy HH:MM:SS").format(new GregorianCalendar().getTime())+".png";
        File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try{
            FileUtils.copyFile(srcFile ,new File(testScreenshotDir+screenshotPath) );
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return screenshotPath;
    }

    /**
     * method to verify the actual value with expected value
     * @param actual actual text displayed
     * @param expected expected text to be displayed
     * @param message message should be displayed on failure of assertion
     */
    public boolean verifyEquals(String actual , String expected,String message){
        boolean testStatus = true;

        Reporter.log("<br>");
        try{
            Assert.assertEquals(actual,expected,message);
        }catch(AssertionError ex){
            testStatus = false;
           Reporter.log("test failed ");
            // Reporter.log("<Font color = red>FAIL</Font>"+message+"  Actual :"+actual+"  Expected :"+expected
             //           +"Please check screenshot : "+"<a href='"+screenshot()+"'> <Font color=red> HERE</Font> </a>");
        }
    return testStatus;
    }
}
