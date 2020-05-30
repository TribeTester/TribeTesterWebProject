package browserStack;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleHeadspinTest {

    private AndroidDriver driver;


    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("udid", "4739514632313498");
        desiredCapabilities.setCapability("deviceName", "4739514632313498");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.android.settings");
        desiredCapabilities.setCapability("appActivity", "com.android.settings.Settings");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("autoAcceptAlerts", true);

        URL remoteUrl = new URL("https://dev-de-fra-1.headspin.io:9001/v0/b0662a23426b4dbda1e9529ab190a442/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test() {
        System.out.println("Testthign");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
