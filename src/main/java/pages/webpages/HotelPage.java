package pages.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(css = "#hlistpg_fr_user_rating input")
    private WebElement userRatingFilter;


    /**
     * Selects the room for adults and children
     * @param sAdultGuestNo
     * @param sChildrenGuestNo
     */
    @FindBy(id = "RoomType")
    private WebElement roomSection;

    @FindBy(xpath = "//*[@id='RoomType']//*[text()='SELECT ROOM']")
    private WebElement firstRoom;

    public void selectAdultAndChildrenGuest(String sAdultGuestNo, String sChildrenGuestNo) {
        click(guest, "guest", SHORTWAIT);
        click(adultGuest.get(Integer.parseInt(sAdultGuestNo) - 1), "adultGuest", MEDIUMWAIT);
        click(childrenGuest.get(Integer.parseInt(sChildrenGuestNo) - 1), "childrenGuest", MEDIUMWAIT);
        clickApplyBtn();
    }


    /**
     * CLicks on the apply button after selecting the number of adults and children
     */
    public void clickApplyBtn() {
        click(applyBtn, "applyBtn", MEDIUMWAIT);
        waitForPageLoad();
    }

    /**
     * Selects the location
     * @param slocation
     */
    public void selectLocation(String slocation) {
        click(searchbar, "location", LONGWAIT);
        clearAndType(searchbarInbox, slocation, "location", LONGWAIT);
        click(location, slocation, SHORTWAIT);
    }

    /**
     * Select the check-in and check-out dates
     * @param aCheckinDate
     * @param aCheckOutDate
     */
    public void selectCheckInCheckOutDate(String aCheckinDate, String aCheckOutDate) {
        click(checkinDate, "checkinDate", SHORTWAIT);
        sleep(1);
        getWebElement(By.cssSelector("div[class*='DayPicker-Day'][aria-label*='" + aCheckinDate + "']")).click();
//        click(checkinDate, "checkinDate", SHORTWAIT);
        sleep(1);
        getWebElement(By.cssSelector("div[class*='DayPicker-Day'][aria-label*='" + aCheckOutDate + "']")).click();
    }


    /**
     * Selects the options for travelling
     */
    public void selectTravellingFor() {
        click(travellingFor, "travellingFor", SHORTWAIT);
        click(travellingForOption, "travellingForOption", SHORTWAIT);
    }

    /**
     * Searches for the hotel after selecting the location, check-in & check-out dates, rooms and travelling for options
     */
    public void searchHotel() {
        click(searchHotel, "searchHotel", SHORTWAIT);
        waitForPageLoad();
        getDriver().navigate().refresh();
    }

    /**
     * Applies the filter by setting the minimum price range to rs.1000
     * @param aAmount
     */
    public void setPriceRange(int aAmount) {
        sleep(2);
        int xOffsetValue = (int) (aAmount / 500 * 3.5);
        WebElement slider = getWebElement(By.cssSelector("span[class*='input-range__slider']"));
        Actions sliderAction = new Actions(driver);
        sliderAction.clickAndHold(slider)
                .moveByOffset(xOffsetValue, 0)
                .release().perform();
        log.info("Selected Minimum price range");
    }

    /**
     * Applies filter based on the user ratings
     */
    public void checkUserRatingChkbox() {
        waitForPageLoad();
        click(userRatingFilter, "userRatingFilter", SHORTWAIT);
    }


    /**
     * Selects the fifth hotel after applying the filters
     * @return
     */
    public String selectFifthHotel() {
        List<WebElement> hotelElements = getWebElements(By.cssSelector("[id^=Listing_hotel_]"));
        javascriptScrollToElement(hotelElements.get(4));
        String sHotelName = getText(hotelElements.get(4).findElement(By.cssSelector("span[id^='htl_id_seo']")), "FifthHotelName", SHORTWAIT);
        click(hotelElements.get(4), "Fifth hotel", 5);
        return sHotelName;
    }

    public void scrollToRoomSection() {
        javascriptScrollToElement(roomSection);
    }

    public void selectRoom() {
        click(firstRoom, "firstRoom", MEDIUMWAIT);
    }
}
