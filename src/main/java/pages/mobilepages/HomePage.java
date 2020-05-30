package pages.mobilepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends CommonMobilePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "")
    private WebElement test;

    public void testMethod() {
        click(test, "test", 5);
    }
}
