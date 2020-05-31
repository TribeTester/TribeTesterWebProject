package pages.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonWebPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "close")
    private WebElement closeIcon;

    @FindBy(css = ".userSection li[data-cy=account]")
    private WebElement loginOrCreateAccount;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "//form//button")
    private WebElement continueBtn;

    @FindBy(xpath = "//form//button")
    private WebElement loginBtn;

    @FindBy(className = "popupCrossIcon")
    private WebElement closeModalDialog;

    public void loginToMakeMyTrip(String sUsername, String sPassword) {
        if (isElementDisplayed(closeIcon, "closeIcon", 1)) {
            click(closeIcon, "closeIcon", SHORTWAIT);
        }
        click(loginOrCreateAccount, "loginOrCreateAccount", SHORTWAIT);
        clearAndType(username, sUsername, "Username", LONGWAIT);
        sleep(2);
        click(continueBtn,"continueBtn",SHORTWAIT);
        clearAndType(password, sPassword, "password", LONGWAIT);
        sleep(2);
        click(loginBtn,"loginBtn",SHORTWAIT);
        if (isElementDisplayed(closeModalDialog, "closeModalDialog", 1)) {
            click(closeModalDialog, "closeModalDialog", SHORTWAIT);
        }


    }

}
