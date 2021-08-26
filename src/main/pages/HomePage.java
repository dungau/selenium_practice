import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator for login button
    By btnLogin = By.cssSelector("[href='https://mail.protonmail.com/login']");

    public void clickLogin() throws Exception {
        Utils.clickElement(btnLogin);
    }
}
