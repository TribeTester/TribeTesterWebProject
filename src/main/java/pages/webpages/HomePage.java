package pages.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(css="button[data-cy$='continueBtn']")
    private WebElement continueButton;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css="[data-cy=\"login\"]")
    private WebElement loginButton;

    @FindBy(className = "modalMain")
    private WebElement mainModal;

    @FindBy(css = "[data-cy='modalClose']")
    private WebElement closeModal;

    @FindBy(css = "[data-cy='menu_Hotels']")
    private WebElement hotels;



    @FindBy(id = "checkin")
    private WebElement checkinDate;

    @FindBy(className = "DayPicker-Month")
    private WebElement datePicker;


    /**
     * Login to MMT web application by providing username and password
     * @param sUsername
     * @param sPassword
     */
    public void loginToMakeMyTrip(String sUsername, String sPassword) {
        if (isElementDisplayed(closeIcon, "closeIcon", 1)) {
            click(closeIcon, "closeIcon", SHORTWAIT);
        }
        click(loginOrCreateAccount, "loginOrCreateAccount", SHORTWAIT);
        clearAndType(username, sUsername, "sUsername", LONGWAIT);
        waitForElementToClick(continueButton,"continue button").submit();
        clearAndType(password, sPassword, "sPassword", LONGWAIT);
        waitForElementToClick(loginButton,"login button").submit();
        waitForPageLoad();
        sleep(5);


    }

    /**
     * Click on hotels in the top menu bar
     * @return
     */
    public HotelPage clickOnHotel() {
        click(hotels, "hotels", SHORTWAIT);
        waitForPageLoad();
        return new HotelPage(driver);
    }






}
