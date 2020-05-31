package pages.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HotelPage extends CommonWebPage {


    public HotelPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ul.guestCounter[data-cy^='adult'] li")
    private List<WebElement> adultGuest;

    @FindBy(css = "ul.guestCounter li[data-cy^=children]")
    private List<WebElement> childrenGuest;

    @FindBy(css = "button[data-cy='submitGuest']")
    private WebElement applyBtn;

    @FindBy(css = "label[for='city']")
    private WebElement searchbar;

    @FindBy(css = "[placeholder*='Enter city']")
    private WebElement searchbarInbox;

    @FindBy(css = "[data-section-index='0']")
    private WebElement location;

    @FindBy(id = "checkin")
    private WebElement checkinDate;

    @FindBy(id = "guest")
    private WebElement guest;

    @FindBy(css = "label[for='travelFor']")
    private WebElement travellingFor;

    @FindBy(css = ".travelForPopup li")
    private WebElement travellingForOption;

    @FindBy(id = "hsw_search_button")
    private WebElement searchHotel;

    @FindBy(xpath = "//div[normalize-space()='User Rating']//following-sibling::ul//li//input")
    private WebElement userRatingFilter;

    public void selectAdultAndChildrenGuest(String sAdultGuestNo, String sChildrenGuestNo) {
        click(guest, "guest", SHORTWAIT);
        click(adultGuest.get(Integer.parseInt(sAdultGuestNo) - 1), "adultGuest", MEDIUMWAIT);
        click(childrenGuest.get(Integer.parseInt(sChildrenGuestNo) - 1), "childrenGuest", MEDIUMWAIT);
        clickApplyBtn();
    }


    public void clickApplyBtn() {
        click(applyBtn, "applyBtn", MEDIUMWAIT);
    }

    public void selectLocation(String slocation) {
        click(searchbar, "location", LONGWAIT);
        clearAndType(searchbarInbox, slocation, "location", LONGWAIT);
        click(location, slocation, SHORTWAIT);
    }

    public void selectCheckInCheckOutDate(String aCheckinDate, String aCheckOutDate) {
        click(checkinDate, "checkinDate", SHORTWAIT);
        sleep(1);
        getWebElement(By.cssSelector("div[class*='DayPicker-Day'][aria-label*='" + aCheckinDate + "']")).click();
//        click(checkinDate, "checkinDate", SHORTWAIT);
        sleep(1);
        getWebElement(By.cssSelector("div[class*='DayPicker-Day'][aria-label*='" + aCheckOutDate + "']")).click();
    }


    public void selectTravellingFor() {
        click(travellingFor, "travellingFor", SHORTWAIT);
        click(travellingForOption, "travellingForOption", SHORTWAIT);
    }

    public void searchHotel() {
        click(searchHotel, "searchHotel", SHORTWAIT);
        if(isElementDisplayed(getWebElement(By.cssSelector(".mapCont")),"map",2)){
            if(isElementDisplayed(getWebElement(By.cssSelector(".mapClose")),"mapClose",2)){
                getWebElement(By.cssSelector(".mapClose")).click();
            }
        }

    }


    public void checkUserRatingChkbox() {
        click(userRatingFilter, "userRatingFilter", SHORTWAIT);
    }
}
