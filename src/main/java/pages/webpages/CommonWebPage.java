package pages.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Actions;

public class CommonWebPage extends Actions {
    public CommonWebPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
