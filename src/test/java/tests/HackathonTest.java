package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.webpages.HomePage;
import pages.webpages.HotelPage;
import utils.ReadProperties;

public class HackathonTest extends BaseTest {

    String sURL;
    String sUsername;
    String sPassword;

    HomePage homePage;
    HotelPage hotelPage;

    @BeforeMethod
    public void loadData() {
        homePage = new HomePage(driver);
        hotelPage=new HotelPage(driver);
        sURL = ReadProperties.getConfigProperties("WebAppUrl");


        setTestDataProperties("WebMakeMyTripTestData");
        sUsername= getProperty("EmailID");
        sPassword= getProperty("Password");

    }

    @Test
    public void WebMakeMyTripTest() {
        step("Navigate to URL ");
        navigateToURL(sURL);

        step("Login to application");
        homePage.loginToMakeMyTrip(sUsername, sPassword);
        navigateToURL(sURL);

        step("click on hotel");
        hotelPage=homePage.clickOnHotel();

        step("select location");
        hotelPage.selectLocation("Goa");

        step("Select checkin and checkout date");
        hotelPage.selectCheckInCheckOutDate("Jun 14","Jun 16");

        step("Select rooms and guests");
        hotelPage.selectAdultAndChildrenGuest("2","2");

        step("Select travelling reason");
        hotelPage.selectTravellingFor();

        step("Search for the hotels");
        hotelPage.searchHotel();

        step("");

        hotelPage.checkUserRatingChkbox();







    }
}
