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

    @FindBy(css="[data-cy='continueBtn']")
    private WebElement continueButton;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(css="[data-cy=\"login\"]")
    private WebElement loginButton;

    @FindBy(className = "modalMain ")
    private WebElement mainModal;

    @FindBy(css="[data-cy=\"modalClose\"]")
    private WebElement closeModal;

    @FindBy(css = "[data-cy=\"menu_Hotels\"]")
    private WebElement hotels;

    @FindBy(css = "label[for='city']")
    private WebElement searchbar;

    @FindBy(css = "[data-section-index=\"0\"]")
    private WebElement location;

    @FindBy(id = "checkin")
    private WebElement checkinDate;

    @FindBy(className = "DayPicker-Month")
    private WebElement datePicker;


    public void loginToMakeMyTrip(String sUsername, String sPassword) {
        if (isElementDisplayed(closeIcon, "closeIcon", 1)) {
            click(closeIcon, "closeIcon", SHORTWAIT);
        }
        click(loginOrCreateAccount, "loginOrCreateAccount", SHORTWAIT);
        clearAndType(username, sUsername, "sUsername", LONGWAIT);

        click(continueButton,"continue button",LONGWAIT);
        clearAndType(password, sPassword, "sPassword", LONGWAIT);
        click(loginButton,"login button",SHORTWAIT);

        if (isElementDisplayed(mainModal, "main modal", 1)) {
            click(closeModal, "closeModal", SHORTWAIT);
        }

    }

    public void clickOnHotel() {
        click(hotels, "hotels", SHORTWAIT);
    }

    public void selectLocation(String slocation) {
        clearAndType(searchbar, slocation, "location", LONGWAIT);
        click(location, slocation, SHORTWAIT);
    }




}
