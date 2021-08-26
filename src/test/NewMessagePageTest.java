import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.GlobalVariables;
import utils.Utils;

import static org.testng.Assert.*;

public class NewMessagePageTest {

    @BeforeClass
    public void initAndOpenBrowser() {
        Utils.openBrowser("chrome");
        Utils.navigateToURL(GlobalVariables.URL);
    }

    @AfterClass
    public void closeBrowser() {
        Utils.closeBrowser();
    }

    @Test
    public void VerifyUserCanCreateNewMessage() throws Exception {
        LoginPage loginPage = new LoginPage(Utils.driver);
        MailMainPage mailMainPage = new MailMainPage(Utils.driver);
        HomePage homePage = new HomePage(Utils.driver);
        NewMessagePage newMessagePage = new NewMessagePage(Utils.driver);

        //Click log in button
        homePage.clickLogin();

        //Enter username and password then click Sign in button
        assertTrue(loginPage.verifyLoginPageDisplay(), "Login page is not display");
        loginPage.fillLoginForm(GlobalVariables.USERNAME, GlobalVariables.PASSWORD);
        loginPage.clickSignIn();

        //Verify main page
        boolean isMainPageDisplay = mailMainPage.verifyMainPageDisplay();
        assertTrue(isMainPageDisplay, "Main Page is not display");

        //Create a new message
        mailMainPage.clickExpectedButtonByName("New Message");
        newMessagePage.fillEmail(GlobalVariables.EMAIL_SUBJECT, GlobalVariables.EMAIL_TO, GlobalVariables.EMAIL_BODY);
        newMessagePage.clickClosed();

        //Verify the draft email is displayed under draft folder
        mailMainPage.clickExpectedButtonByName("Drafts");
        mailMainPage.emailNotificationPopup("Draft saved.");
        assertEquals(mailMainPage.findEmailMatchSubjectAndTo(
                GlobalVariables.EMAIL_SUBJECT, GlobalVariables.EMAIL_TO), null, "Cannot find the expected draft email");
        mailMainPage.findEmailMatchSubjectAndTo(GlobalVariables.EMAIL_SUBJECT, GlobalVariables.EMAIL_TO).click();

        //Verify draft email content
        boolean isEmailContentCorrect = newMessagePage.verifyEmailContent(GlobalVariables.EMAIL_SUBJECT, GlobalVariables.EMAIL_TO, GlobalVariables.EMAIL_BODY);
        assertTrue(isEmailContentCorrect, "Email content is incorrect");

        //Send email
        newMessagePage.clickSend();

        //Verify on Drafts folder
        assertEquals(mailMainPage.findEmailMatchSubjectAndTo(
                GlobalVariables.EMAIL_SUBJECT, GlobalVariables.EMAIL_TO), null, "Email is not sent");

        //Verify on Sent folder
        mailMainPage.clickExpectedButtonByName("Sent");
        mailMainPage.emailNotificationPopup("Message sent");
        assertEquals(mailMainPage.findEmailMatchSubjectAndTo(
                GlobalVariables.EMAIL_SUBJECT, GlobalVariables.EMAIL_TO), null, "Sent email is not display");

        //Log off
        mailMainPage.logOut();
        assertTrue(loginPage.verifyLoginPageDisplay(), "Logout was unsuccessful");
    }

}