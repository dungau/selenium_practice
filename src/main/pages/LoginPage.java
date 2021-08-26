import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator for username field
    By txtUsername = By.id("username");

    //Locator for password field
    By txtPassword = By.id("password");

    //Locator for sign in button
    By btnSignIn = By.cssSelector("form > button");

    //Method to enter username and password
    public void fillLoginForm(String username, String password) throws Exception {
        Utils.setTextToElement(txtUsername, username);
        Utils.setTextToElement(txtPassword, password);
    }

    //Method to click on Login button
    public void clickSignIn() throws Exception {
        Utils.clickElement(btnSignIn);
    }

    public boolean verifyLoginPageDisplay() throws Exception {
        return Utils.waitElement(txtUsername).isEnabled();
    }
}
