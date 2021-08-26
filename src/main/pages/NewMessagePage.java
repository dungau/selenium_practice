import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

public class NewMessagePage {
    WebDriver driver;

    public NewMessagePage(WebDriver driver) {
        this.driver = driver;
    }

    By txtAddress = By.cssSelector("[data-testid='composer:address']");
    By txtTo = By.cssSelector("[data-testid='composer:to']");
    By txtSubject = By.cssSelector("[data-testid='composer:subject']");
    By txtEmailBody = By.cssSelector("#squire > div:nth-child(1)");
    By btnSend = By.cssSelector("[data-testid='composer:send-button']");
    By lblAutoSave = By.cssSelector((".mlauto > span"));
    By btnClose = By.cssSelector("[data-testid='composer:close-button']");

    public void fillEmail (String subject, String to, String body) throws Exception {

        Utils.waitElement(txtAddress).click();
        Utils.setTextToElement(txtTo, to);
        Utils.setTextToElement(txtSubject, subject);
        setEmailBodyByJS(body);
    }

    public void clickSend() throws Exception {
        Utils.clickElement(btnSend);
    }

    public void clickClosed() throws Exception {
        Utils.clickElement(btnClose);
    }

    public boolean verifyEmailContent(String subject, String to, String body) throws Exception {
        Utils.waitElement(txtAddress).click();
        String emailTo = Utils.waitElement(txtTo).getText();
        String emailSubject = Utils.waitElement(txtSubject).getText();
        String emailBody = getEmailBodyByJS();
        System.out.println(emailBody);

        return emailSubject.trim().equals(subject) &&
                emailTo.trim().equals(to) &&
                emailBody.trim().equals(body);
    }

    public void setEmailBodyByJS(String strData) {
        driver.switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsScripts = "document.querySelector('#squire > div:nth-child(1)').innerHTML='test'";
        js.executeScript(jsScripts);
        driver.switchTo().defaultContent();
    }

    public String getEmailBodyByJS() {
        driver.switchTo().frame(0);
        String emailBody = driver.findElement(txtEmailBody).getAttribute("innerHTML");
        driver.switchTo().defaultContent();
        return emailBody;
    }
}
