package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void openBrowser(String browserName)
    {
        System.out.println("Init browser");
        try{
            switch(browserName.trim().toLowerCase())
            {
                case "chrome": //Initialize for Chrome browser
                    System.setProperty("webdriver.chrome.driver", GlobalVariables.CHROMEDRIVER_PATH);
                    driver = new ChromeDriver();
                    wait = new WebDriverWait(driver, GlobalVariables.MEDIUM_WAIT);
                    break;
                default:
                    System.out.println("Name of the browser is incorrect");
            }
        }catch(Exception ex){System.out.println("Can't initialize the browser. "+ ex.getMessage());}
    }

    public static void navigateToURL(String strAddress)
    {
        System.out.println("Navigate to the " + strAddress);
        try{
            driver.get(strAddress); //navigate to URL
            driver.manage().window().maximize(); //maximize the window
        }catch(Exception ex){System.out.println("Can't navigate to an address URL: " + strAddress + ". " + ex.getMessage());}
    }

    //close a browser
    public static void closeBrowser()
    {
        System.out.println("Close the browser");
        try{
            driver.quit();
        }catch(Exception ex){System.out.println("Can't close the browser. "+ ex.getMessage());}
    }

    //wait for an element to be found
    public static WebElement waitElement(By strLocation) throws Exception
    {
        System.out.println("Waiting for the element with locator " + strLocation + " is present.");
        try{
            WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(strLocation));
            return e;
        }
        catch(Exception ex){
            System.out.println("Not able to find the element with locator " + strLocation + ". " + ex.getMessage());
            return null;
        }
    }

    //click on an element
    public static void clickElement(By strLocation) throws Exception //click on any element
    {
        try{
            WebElement e = waitElement(strLocation);
            System.out.println("Click the button with locator " + strLocation + ".");
            e.click();
        }
        catch(Exception ex){
            System.out.println("Not able to click the button with locator " + strLocation + ". " + ex.getMessage());
        }
    }

    //input text to an element
    public static void setTextToElement(By strLocation, String strData) throws Exception
    {
        try{
            WebElement e = waitElement(strLocation);
            System.out.println("Enter the text " + strData + " to the element with locator " + strLocation + ".");
            e.click();
            e.sendKeys(strData);
        }
        catch(Exception ex){
            System.out.println("Not able to enter the text " + strData + " to the element with locator " + strLocation + "." + ex.getMessage());
        }
    }
}
