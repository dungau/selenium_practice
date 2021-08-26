import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

import java.util.List;

public class MailMainPage {
    WebDriver driver;

    public MailMainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator for new message button
    By btnNewMessage = By.cssSelector("[data-testid='sidebar:compose']");
    By btnDrafts = By.cssSelector("[data-testid='navigation-link:Drafts']");
    By btnSent = By.cssSelector("[data-testid='navigation-link:Sent']");
    By lstEmailSubject = By.cssSelector("[data-testid='message-column:subject']");
    By lstEmailSender = By.cssSelector("[data-testid='message-column:sender-address']");
    By lstEmail = By.cssSelector("[data-testid='message-list:message']");
    By lblNotificationPopup = By.cssSelector("div.notifications-container > div > span");
    By logoLink = By.className("logo-link");
    By btnUser = By.className("user-dropdown-button");
    By btnLogout = By.cssSelector("[data-cy-header-user-dropdown='logout']");

    //Method to click on expected button by name
    public void clickExpectedButtonByName(String buttonName) throws Exception {
        switch (buttonName.trim().toLowerCase()) {
            case "new message" -> Utils.clickElement(btnNewMessage);
            case "drafts" -> Utils.clickElement(btnDrafts);
            case "sent" -> Utils.clickElement(btnSent);
            default -> System.out.println("Button name is incorrect");
        }
    }

    public boolean verifyMainPageDisplay() throws Exception {
        if(Utils.wait.until(ExpectedConditions.elementToBeClickable(logoLink)) == null) {
            return false;
        }
        else return true;
    }

    public WebElement findEmailMatchSubjectAndTo(String subject, String to) {
        List<WebElement> elements = Utils.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lstEmail));
        for (WebElement element: elements) {
            String itemSubject = element.findElement(lstEmailSubject).getText();
            String itemSender = element.findElement(lstEmailSender).getText();
            if (itemSender.trim().equals(to) &&
                    itemSubject.trim().equals(subject)) {
                return element.findElement(lstEmailSubject);
            }
        }
        return null;
    }

    public void emailNotificationPopup(String strData) {
        Utils.wait.until(ExpectedConditions.textToBePresentInElementLocated(lblNotificationPopup, strData));
    }

    public void logOut() throws Exception {
        Utils.clickElement(btnUser);
        Utils.clickElement(btnLogout);
    }

}
