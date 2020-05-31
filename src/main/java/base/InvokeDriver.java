package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ReadProperties;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class InvokeDriver {


    RemoteWebDriver driver;
    public String chromeDriverPath = System.getProperty("user.dir")+"\\src\\main\\resources\\Driver\\chromedriver.exe";
    public String firefoxDriverPath = System.getProperty("user.dir")+"\\src\\main\\resources\\Driver\\geckodriver.exe";

    public WebDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return new ChromeDriver(options);
    }

    public WebDriver initFirefoxDriver() {

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
        options.setProfile(firefoxProfile);
        return new FirefoxDriver(options);
    }

    public WebDriver setDriver(String browserType) {
        try {
            switch (browserType) {
                case "firefox":
                    return initFirefoxDriver();
                case "chrome":
                    return initChromeDriver();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return initChromeDriver();
    }

    public AppiumDriver getAppiumDriver(String sModeOfExecution) {
        AppiumDriver appiumDriver;
        DesiredCapabilities capabilities = null;
        String sAppFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "app" + File.separator + "MakeMyTrip.apk";
        if (sModeOfExecution.equalsIgnoreCase("local")) {
            capabilities = new DesiredCapabilities();

            capabilities.setCapability("appPackage", ReadProperties.getConfigProperties("appPackage"));
            capabilities.setCapability("appActivity", ReadProperties.getConfigProperties("appActivity"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
            capabilities.setCapability("platformVersion", ReadProperties.getConfigProperties("PlatformVersion"));
            capabilities.setCapability("deviceName", ReadProperties.getConfigProperties("androidDeviceName"));
            capabilities.setCapability("app", sAppFilePath);
            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            capabilities.setCapability("unicodekeyboard", true);
            capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
            capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        } else if (sModeOfExecution.equalsIgnoreCase("Remote")) {
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.UDID, ReadProperties.getConfigProperties("RemoteUDID"));
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ReadProperties.getConfigProperties("RemoteDeviceName"));
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(AndroidMobileCapabilityType.APPLICATION_NAME, sAppFilePath);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ReadProperties.getConfigProperties("appPackage"));
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ReadProperties.getConfigProperties("appActivity"));
            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
            capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
        }
        appiumDriver = new AndroidDriver<>(getAppiumServerURL(sModeOfExecution), capabilities);
        return appiumDriver;
    }

    static URL getAppiumServerURL(String sModeOfExecution) {
        URL appiumServerURL = null;

        try {
            if (sModeOfExecution.equalsIgnoreCase("Remote")) {
                appiumServerURL = new URL("http://" + ReadProperties.getConfigProperties("LocalRemoteIPWithPort") + "/wd/hub");

            } else if (sModeOfExecution.equalsIgnoreCase("Remote")) {
                appiumServerURL = new URL("http://" + ReadProperties.getConfigProperties("RemoteIPWithPort") + "/wd/hub");
            }
        } catch (MalformedURLException ex) {
            System.err.println("Error occurred in Remote Grid URL.");
        }
        return appiumServerURL;
    }


    public RemoteWebDriver setRemoteDriver(String browserType, String ip) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities();
            switch (browserType) {
                case "firefox":
                    //Capabilities for Firefox
                    capability.setCapability("browserName", "Firefox");
                    capability.setCapability("version", "76x64");
                    capability.setCapability("platform", "Windows 10");
                    capability.setCapability("screenResolution", "1366x768");
                    break;
                case "chrome":
                default:
                    capability.setCapability("browserName", "Chrome");
                    capability.setCapability("version", "72x64");
                    capability.setCapability("platform", "Windows 10");
                    capability.setCapability("screen_resolution", "1366x768");
                    break;
            }
            this.driver = new RemoteWebDriver(new URL(ip), capability);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
}
