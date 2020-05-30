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

    public void loginToMakeMyTrip(String sUsername, String sPassword) {
        if (isElementDisplayed(closeIcon, "closeIcon", 1)) {
            click(closeIcon, "closeIcon", SHORTWAIT);
        }
        click(loginOrCreateAccount, "loginOrCreateAccount", SHORTWAIT);
        clearAndType(username, sUsername, "sUsername", LONGWAIT);

    }

}
