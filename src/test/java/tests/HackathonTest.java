package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.webpages.HomePage;
import utils.ReadProperties;

public class HackathonTest extends BaseTest {


    String sURL;

    HomePage homePage=new HomePage(driver);

    @BeforeMethod
    public void loadData() {

        sURL = ReadProperties.getConfigProperties("WebAppUrl");

    }


    @Test
    public void WebMakeMyTripTest() {
        step("Navigate to URL ");
        navigateToURL(sURL);

        step("Login to application");
        homePage.loginToMakeMyTrip("saurabhfromautomation@gmail.com","TribeTester@123");



    }

}
