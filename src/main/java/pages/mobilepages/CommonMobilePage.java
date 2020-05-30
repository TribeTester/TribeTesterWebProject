package pages.mobilepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Actions;

public class CommonMobilePage extends Actions {

    CommonMobilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
