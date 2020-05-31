package pages.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReviewYourBookingPage extends CommonWebPage {


    public ReviewYourBookingPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "fName")
    private WebElement firstName;

    @FindBy(id = "lName")
    private WebElement lName;

    @FindBy(id = "mNo")
    private WebElement mobileNumber;

    @FindBy(css = "._SpecialRequest li")
    private List<WebElement> specialRequestCKBx;

    @FindBy(css = "div.donationOuter input")
    private WebElement donationChkBx;

    @FindBy(css = "a.btnPayNow")
    private WebElement payNowBtn;


    public void addTravellerInformation() {
        clearAndType(firstName, "Test FirstName", "Firstname", SHORTWAIT);
        clearAndType(lName, "Test lName", "lName", SHORTWAIT);
        clearAndType(mobileNumber, "9876543210", "mobileNumber", SHORTWAIT);

    }

    public void selectTwoSpecialRequestChkBx() {

        waitForPageLoad();
        javascriptScrollToElement(specialRequestCKBx.get(0));
            click(specialRequestCKBx.get(0), "SpecialRequest1", SHORTWAIT);
            click(specialRequestCKBx.get(1), "SpecialRequest2", SHORTWAIT);

    }

    public void uncheckDonation() {
        waitForPageLoad();
        javascriptScrollToElement(specialRequestCKBx.get(0));
        if (specialRequestCKBx.get(0).isSelected()) {
            click(donationChkBx, "donationChkBx", SHORTWAIT);
        }
    }

    public void clickPayNow(){
        click(payNowBtn,"payNowBtn",SHORTWAIT);
    }

}
