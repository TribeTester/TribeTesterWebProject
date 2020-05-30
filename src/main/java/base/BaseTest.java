package base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ExtentTestManager;
import utils.ReadProperties;
import utils.TestListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Listeners({utils.TestListener.class})
public class BaseTest {

    protected WebDriver driver = null;

    protected File file = new File("");
    public static String sSeperator = System.getProperty("file.separator");

    public static final String CONFIG_FILE_PATH = "/src/main/resources/config/config.properties";

    protected FileInputStream configFis;
    Properties configProp = new Properties();

    Properties properties;
    Logger log = Logger.getLogger(BaseTest.class);

    String remoteAddress = "";

    @BeforeSuite
    public void setUp() throws Exception {

        // Log4j ImplementationS
        String propertiesFilePath = System.getProperty("user.dir") + "/src/main//resources/log4j/log4j.properties";

        PropertiesConfiguration log4jProperties = new PropertiesConfiguration(propertiesFilePath);
        log4jProperties.setProperty("log4j.appender.HTML.File",
                "Reports/" + TestListener.sLatestReportFolder + "/Logs/Htmllogs.html");
        log4jProperties.setProperty("log4j.appender.Appender2.File",
                "Reports/" + TestListener.sLatestReportFolder + "/Logs/Logs.log");
        log4jProperties.save();

        PropertyConfigurator.configure(propertiesFilePath);

        configFis = new FileInputStream(file.getAbsoluteFile() + CONFIG_FILE_PATH);
        configProp.load(configFis);

    }
//

    /**
     * this method creates the driver depending upon the passed parameter (android
     * or iOS) and loads the properties files (config and test data properties
     * files).
     * <p>
     * //     * @param os         android or iOS
     *
     * @param methodName - name of the method under execution
     * @throws Exception issue while loading properties files or creation of driver.
     */
    @Parameters({"ModeOfExecution", "browser"})
    @BeforeMethod
    public void createDriver(@Optional("") String sModeOfExecution, @Optional("") String sBrowser, Method methodName) throws Exception {

        sModeOfExecution = sModeOfExecution.toLowerCase().isEmpty() ? ReadProperties.getConfigProperties("ModeOfExecution") : sModeOfExecution;
        sBrowser = sBrowser.toLowerCase().isEmpty() ? ReadProperties.getConfigProperties("Browser") : sBrowser;


        if (sModeOfExecution.toLowerCase().contains("local")) {
            if (methodName.getName().toLowerCase().startsWith("web"))
                this.driver = new InvokeBrowser().setDriver(sBrowser.toLowerCase());
            else if (methodName.getName().startsWith("mobile")) {

            }
        } else if (sModeOfExecution.toLowerCase().contains("remote")) {
            if (methodName.getName().toLowerCase().startsWith("web"))
                this.driver = new InvokeBrowser().setRemoteDriver(sBrowser.toLowerCase(), remoteAddress);
            else if (methodName.getName().startsWith("mobile")) {

            }
        } else {
            Assert.fail("Please define mode of execution in either config or testng xml file");
        }
    }


    /**
     * Reusable method to get value using key provided in testcase properties file.
     *
     * @param sKey
     * @return
     */
    public String getProperty(String sKey) {
        String sValue = properties.getProperty(sKey);
        return sValue;

    }

    /**
     * this method quit the driver after the execution of test(s)
     */
    @AfterMethod
    public void teardown() {
        log.info("-----------Shutting down driver-----------");
        driver.quit();

    }


    public void step(String sStepMessage) {
        log.info(sStepMessage);
        ExtentTestManager.getTest().log(Status.INFO, sStepMessage);
    }

    public String takeScreenshot(String sText) {

        String sScreenshotPath = null;
        try {
            if (driver != null) {
                String destDir = TestListener.sLatestReportFolderPath + sSeperator + "Screenshots";
                System.out.println("destDir : " + destDir);

                System.out.println("driver : " + driver);
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                System.out.println("Taking screenshot");
                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ssaa");
                String destFile = dateFormat.format(new Date()) + ".png";
                System.out.println("File name " + destFile);
                new File(destDir).mkdirs();
                sScreenshotPath = destDir + sSeperator + destFile;
                try {
                    // Copy paste file at destination folder location
                    FileUtils.copyFile(scrFile, new File(sScreenshotPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ExtentTest test = ExtentTestManager.getTest();
                test.addScreenCaptureFromPath(sScreenshotPath);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sScreenshotPath;
    }


    public WebDriver getDriver() {
        return driver;
    }


    public void navigateToURL(String sURL) {
        log.info("Navigating to URL");
        driver.get(sURL);
        log.info("Navigated to URL");

    }

}
