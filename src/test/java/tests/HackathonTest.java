package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.webpages.HomePage;
import pages.webpages.HotelPage;
import pages.webpages.ReviewYourBookingPage;
import utils.ReadProperties;

public class HackathonTest extends BaseTest {

    String sURL;
    String sUsername;
    String sPassword;

    HomePage homePage;
    HotelPage hotelPage;

    ReviewYourBookingPage reviewYourBookingPage;
    private String sLocation;
    private String sCheckInDate;
    private String sCheckOutDate;
    private String sGuest;

    @BeforeMethod
    public void loadData() {
        homePage = new HomePage(driver);
        hotelPage = new HotelPage(driver);
        sURL = ReadProperties.getConfigProperties("WebAppUrl");


        setTestDataProperties("WebMakeMyTripTestData");
        sUsername = getProperty("EmailID");
        sPassword = getProperty("Password");

        sLocation = getProperty("Location");
        sCheckInDate = getProperty("CheckInDate");
        sCheckOutDate = getProperty("CheckOutDate");
        sGuest = getProperty("Guest");

        reviewYourBookingPage = new ReviewYourBookingPage(driver);


    }

    @Test
    public void WebMakeMyTripTest() {
        step("Navigate to URL ");
        navigateToURL(sURL);

        step("Login to application");
        homePage.loginToMakeMyTrip(sUsername, sPassword);
        navigateToURL(sURL);

        step("click on hotel");
        hotelPage = homePage.clickOnHotel();

        step("select location");
        hotelPage.selectLocation(sLocation);

        step("Select checkin and checkout date");
        hotelPage.selectCheckInCheckOutDate(sCheckInDate, sCheckOutDate);

        step("Select rooms and guests");
        hotelPage.selectAdultAndChildrenGuest(sGuest, sGuest);

        step("Select travelling reason");
        hotelPage.selectTravellingFor();

        step("Search for the hotels");
        hotelPage.searchHotel();

        step("Filter by Amount");
        hotelPage.setPriceRange(1000);

        step("Check User rating Checkbox having 4+ rating");
        hotelPage.checkUserRatingChkbox();

        step("Scroll to fifth hotel and click on it");
        String sCurrentWindow = hotelPage.getCurrentWindowHandle();
        String sHotelName = hotelPage.selectFifthHotel();

        step("Go to room section");
        hotelPage.switchToWindow(sCurrentWindow);
        hotelPage.scrollToRoomSection();

        step("Capture the information of room category ");
        hotelPage.selectRoom();

        step("Add the traveller information");
        reviewYourBookingPage.addTravellerInformation();

        step("Select two special request");
        reviewYourBookingPage.selectTwoSpecialRequestChkBx();

        step("Uncheck Donation checkbox");
        reviewYourBookingPage.uncheckDonation();

        step("Click on Paynow.");
        reviewYourBookingPage.clickPayNow();


    }
}
