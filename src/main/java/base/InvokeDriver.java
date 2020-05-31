package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class InvokeDriver {

    RemoteWebDriver driver;
    public String chromeDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\Driver\\chromedriver.exe";
    public String firefoxDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\Driver\\geckodriver.exe";

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
                    capability.setCapability("headspin:autoDownloadChromedriver",true);
                    System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                    break;
                case "chrome":
                default:
                    capability.setCapability("browserName", "Chrome");
                    capability.setCapability("browserVersion", "80.0.3987.149");
                    capability.setCapability("platform", "Windows 10");
                    capability.setCapability("headspin:initialScreenSize", "1920x1080");
                    capability.setCapability("headspin:autoDownloadChromedriver",true);


                    break;
            }
            this.driver = new RemoteWebDriver(new URL(ip), capability);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
}
