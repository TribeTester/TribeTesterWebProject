package utils;

import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Actions {
   public WebDriver driver;

    public int SHORTWAIT = 5;
    public int MEDIUMWAIT = 8;
    public int LONGWAIT = 15;
    public int VERYLONGWAIT = 25;


    protected org.apache.log4j.Logger log = Logger.getLogger(Actions.class);

    public Actions(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element, String sElement, int iTimeOut) {
        try {
            waitForElementToLoad(element, sElement,iTimeOut);
            element.click();
            log.info("Clicked on " + sElement);
        } catch (TimeoutException we) {
            log.info("Failed to retrieve the element within the time out!!");
            log.info("stack trace is" + we);
            Assert.fail("Exception found while clicking mobile element " + we);
        } catch (InvalidSelectorException ie) {
            log.error("invalid xpath/css");
            Assert.fail("Exception found while clicking mobile element " + ie);
        } catch (org.openqa.selenium.NoSuchElementException we) {
            log.error("stack trace is" + we);
            Assert.fail("Exception found while clicking mobile element " + we);
        } catch (WebDriverException we) {
            log.error("WebDriver exception is occurred" + we);
            log.error("stack trace is" + we);
//	      Assert.fail("Exception found while clicking mobile element " + we);
            // assert false:"NoSuchElement Exception is occurred";
        } catch (Exception e) {
            log.error("Failed to retrieve the element!! locator details are");
            log.error("Exception stack trace is:" + e);
            Assert.fail("Exception found while clicking mobile element " + e);
        }
    }

    /**
     * Waits till element is visible on activity.
     *
     * @param element - mobile element that should be checked.
     */
    public void waitForElementVisibility(final WebElement element, String sElement, int timeOut) {
        try {
            final Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException te) {
            log.error("Waiting for element presence on the DOM!! " + sElement + "element is not present within the Timeout !!!");
            log.error("stack trace" + te);
            Assert.fail("Waiting for element presence on the DOM!! " + sElement + "element is not present within the Timeout !!!"
                    + te);
        } catch (WebDriverException we) {
            log.error("Waiting for element presence on the DOM!! " + sElement + "element is not present within the Timeout !!!  ");
            log.error("stack trace" + we);
            Assert.fail("Waiting for element presence on the DOM!! " + sElement + "element is not present within the Timeout !!!"
                    + we);
        }
    }
    public boolean waitForElementInvisible(WebElement element,String sElement){

        log.info("waiting for "+sElement);
        int iEndTime=50;
        int iCount=0;
        boolean bFoundElement=false;

        while(iCount<iEndTime) {
            iCount++;
            try {
                if (!element.isEnabled()||!element.isDisplayed()) {
                    log.info(sElement+" is Invisible");
                    bFoundElement = true;
                    break;
                }else{
                    log.info("Waiting for "+sElement+" element Invisible");
                    sleep(1);

                }
            } catch (Exception e) {
                log.info("Waiting for "+sElement+" element Invisible");
                sleep(1);
            }

        }
        return bFoundElement;
    }



    public boolean waitForElementToLoad(WebElement element,String sElement,int iTimeOut){

        log.info("waiting for "+sElement);
        int iEndTime=iTimeOut;
        int iCount=0;
        boolean bFoundElement=false;

        while(iCount<iEndTime) {
            iCount++;
            try {
                if (element.isDisplayed()&&element.isEnabled()) {
                    log.info(sElement+" is Displayed");
                    bFoundElement = true;
                    break;
                }else{
                    log.info("Waiting for "+sElement+" element "+iCount+" seconds");
                    sleep(1);

                }
            } catch (Exception e) {
                log.info("Waiting for "+sElement+" element "+iCount+" seconds");
                sleep(1);
            }

        }
        return bFoundElement;
    }
    public WebElement waitForElementToClick(WebElement element,String sElement){

        WebDriverWait wait = new WebDriverWait(this.driver,30);

        // Explicit Wait
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));

        return webElement;
    }

    public void clearAndType(final WebElement element, final String textToType, String sElement, int iTimeOut) {
        try {
            waitForElementToLoad(element, sElement,iTimeOut);
            element.clear();
            element.sendKeys(textToType);
            log.info("Entered "+textToType+" in "+sElement+" element");
        } catch (TimeoutException we) {
            log.error("Failed to retrieve the element within the time out!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while inputting text in text box " + we);
        } catch (InvalidSelectorException ie) {
            log.error("invalid xpath/css");
            Assert.fail("Exception found while inputting text in text box " + ie);
        } catch (org.openqa.selenium.NoSuchElementException we) {
            log.error("Failed to retrieve the element, check the xpath/css selector!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while inputting text in text box " + we);
        } catch (StaleElementReferenceException se) {
            log.error("Stale element exception is occurred!!!!");
            log.error("Stack trace is" + se);
            Assert.fail("Exception found while inputting text in text box " + se);
        } catch (WebDriverException we) {
            log.error("WebDriver exception is occurred" + we);
            log.error("stack trace is" + we);
            Assert.fail("Exception found while inputting text in text box " + we);
        } catch (Exception e) {
            log.error("Failed to retrieve the element!! locator details are");
            log.error("Exception stack trace is:" + e);
            Assert.fail("Exception found while inputting text in text box " + e);
        }
    }

    public void javascriptClick(WebElement element,String sElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    public String getText(final WebElement element, String sElement, int iTimeOut) {
        String text = "";
        try {
            waitForElementVisibility(element, sElement, iTimeOut);
            text = element.getText();
        } catch (TimeoutException we) {
            log.error("Failed to retrieve the element within the time out!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while reading text of an element " + we);
        } catch (InvalidSelectorException ie) {
            log.error("invalid xpath/css");
            Assert.fail("Exception found while reading text of an element " + ie);
        } catch (org.openqa.selenium.NoSuchElementException we) {
            log.error("Failed to retrieve the element, check the xpath/css selector!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while reading text of an element " + we);
        } catch (StaleElementReferenceException se) {
            log.error("Stale element exception is occurred!!!!");
            log.error("Stack trace is" + se);
            Assert.fail("Exception found while reading text of an element " + se);

        } catch (WebDriverException we) {
            log.error("WebDriver exception is occurred" + we);
            log.error("stack trace is" + we);
            Assert.fail("Exception found while reading text of an element " + we);
            // assert false:"NoSuchElement Exception is occurred";
        } catch (Exception e) {
            log.error("Failed to retrieve the element!! locator details are");
            log.error("Exception stack trace is:" + e);
            Assert.fail("Exception found while reading text of an element " + e);
            // assert false:"Other Exception is occurred";
        }
        return text;
    }

    /**
     * Clears text in the particular mobile element.
     *
     * @param element  - mobile element that should be fetched.
     * @param sElement
     * @param iTimeOut
     */
    public void clearText(final WebElement element, String sElement, int iTimeOut) {
        try {
            waitForElementVisibility(element, sElement, iTimeOut);
            element.clear();
        } catch (TimeoutException we) {
            log.error("Failed to retrieve the element within the time out!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while clearing text of text box" + we);
        } catch (InvalidSelectorException ie) {
            log.error("invalid xpath/css");
            Assert.fail("Exception found while clearing text of text box" + ie);
        } catch (org.openqa.selenium.NoSuchElementException we) {
            log.error("Failed to retrieve the element, check the xpath/css selector!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while clearing text of text box" + we);
        } catch (StaleElementReferenceException se) {
            log.error("Stale element exception is occurred!!!!");
            log.error("Stack trace is" + se);
            Assert.fail("Exception found while clearing text of text box" + se);
        } catch (WebDriverException we) {
            log.error("WebDriver exception is occurred" + we);
            log.error("stack trace is" + we);
            Assert.fail("Exception found while clearing text of text box" + we);
            // assert false:"NoSuchElement Exception is occurred";
        } catch (Exception e) {
            log.error("Failed to retrieve the element!! locator details are");
            log.error("Exception stack trace is:" + e);
            Assert.fail("Exception found while clearing text of text box" + e);
        }
    }

    /**
     * Gets attribute of particular mobile element.
     *
     * @param element       - mobile element that should be fetched.
     * @param attributeName - attribute of mobile element.
     * @param sElement
     * @param iTimeOut
     * @return return's attribute of a mobile element.
     */
    public String getAttribute(final MobileElement element, final String attributeName, String sElement, int iTimeOut) {
        String text = "";

        try {
            waitForElementVisibility(element, sElement, iTimeOut);
            text = element.getAttribute(attributeName);
        } catch (TimeoutException we) {
            log.error("Failed to retrieve the element within the time out!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while reading attribute of element" + we);
        } catch (InvalidSelectorException ie) {
            log.error("invalid xpath/css");
            Assert.fail("Exception found while reading attribute of element" + ie);
        } catch (org.openqa.selenium.NoSuchElementException we) {
            log.error("Failed to retrieve the element, check the xpath/css selector!!");
            log.error("stack trace is" + we);
            Assert.fail("Exception found while reading attribute of element" + we);
        } catch (StaleElementReferenceException se) {

            log.error("Stale element exception is occurred!!!!");
            log.error("Stack trace is" + se);
            Assert.fail("Exception found while reading attribute of element" + se);
        } catch (WebDriverException we) {
            log.error("WebDriver exception is occurred" + we);
            log.error("stack trace is" + we);
            Assert.fail("Exception found while reading attribute of element" + we);
        } catch (Exception e) {
            log.error("Failed to retrieve the element!! locator details are");
            log.error("Exception stack trace is:" + e);
            Assert.fail("Exception found while reading attribute of element" + e);
        }
        return text;
    }

    public void sleep(int iSeconds) {
        try {
            Thread.sleep(iSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public boolean isElementDisplayed(WebElement element, String sElementName, int iTimeOut) {
        try {
            new WebDriverWait(driver, iTimeOut).until((ExpectedConditions.visibilityOf(element)));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * @param by
     * @return WebElement
     */
    public WebElement getWebElement(By by){
        return driver.findElement(by);
    }


    /**
     * @param by
     * @return WebElement
     */
    public List<WebElement> getWebElements(By by){
        return driver.findElements(by);
    }

}
