package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class TestListener extends TestListenerAdapter implements ISuiteListener {


    Logger log = Logger.getLogger("TestListener");
    public static String sReportFolderPath;
    public static String sLatestReportFolder;

    ExtentTest extentTest;
    private RemoteWebDriver driver;
    public static String sLatestReportFolderPath;
    final public static String sSeperator = System.getProperty("file.separator");

    /**
     * Purpose of this method is to perform certain action on the test execution
     * start Primarily the report is deleted, Test rail API initialization
     *
     * @param suite
     */
    public void onStart(ISuite suite) {
        createLatestReportFolder();
        FileUtils.deleteReportsMoreThanXDays(sReportFolderPath, Integer.parseInt(ReadProperties.getConfigProperties("NoOfDaysToStoreReport")));
    }

    /**
     * Purpose of this method is to create the latest report folder in the framework
     */
    public void createLatestReportFolder() {

        sReportFolderPath = System.getProperty("user.dir") + sSeperator + "Reports";
        sLatestReportFolder = DateUtils.getTimeStamp("ddMMMyyyy HHmm") + "Report";

        sLatestReportFolderPath = sReportFolderPath + sSeperator + sLatestReportFolder;


        File reportFolder = new File(sReportFolderPath);// Reports folder
        File latestResultFolder = new File(sLatestReportFolderPath);// latestresults
        if (!reportFolder.exists()) {
            reportFolder.mkdir();
        }

        if (!latestResultFolder.exists()) {
            latestResultFolder.mkdir();
            log.info("Reports folder created successfully");
        }
    }

    /**
     * Purpose of this method is to invoke the onTestStart before any test is
     * started This initializes the Extent report to have test without any result
     *
     * @param result
     */
    @Override
    public synchronized void onTestStart(ITestResult result) {
        String sMethodName = result.getMethod().getMethodName();
        log.info("on Test Start " + sMethodName);

        extentTest = ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    /**
     * This method is invoked when any Test is passed and this method has the
     * implementation of Updating Extent report, Posting test result on TestRail
     * Test case management tool
     *
     * @param result
     */
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        log.info("onTestSuccess");

        log.info("*********Test Passed*********");
        String sMethodName = result.getMethod().getMethodName();

        ExtentTestManager.getTest().log(Status.PASS, "Test passed");

        log.info("*****************************");
    }

    /**
     * * This method is invoked when any Test is Failed and this method has the
     * implementation of Updating Extent report along with the screenshot, Posting
     * test result on TestRail Test case management tool
     *
     * @param result
     */
    @Override
    public synchronized void onTestFailure(ITestResult result) {

        log.info("*********Test Failed*********");

        String sMethodName = result.getMethod().getMethodName();
        log.info("onTestFailure " + sMethodName);
        Object currentClass = result.getInstance();
        String sErrorMessage = result.getThrowable().toString();
        log.info((Supplier<String>) result.getThrowable());

        ExtentTestManager.getTest().log(Status.FAIL, sMethodName + " is failed : " + sErrorMessage);

        log.info("*****************************");

    }

    /**
     * * This method is invoked when any Test is Skipped and this method has the
     * implementation of Updating Extent report
     *
     * @param result
     */
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        log.info("onTestSkipped");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onFinish(ISuite suite) {

    }


}
