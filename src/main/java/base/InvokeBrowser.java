package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class InvokeBrowser {


    RemoteWebDriver driver;

    public WebDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    public WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
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
