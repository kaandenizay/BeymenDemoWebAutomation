package com.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static com.utilities.Driver.getDriver;
import static com.utilities.Constant.GLOBAL_TIME_OUT;
public class Utils {

    public static void clearEnterText(WebElement element, String inputText) {
        element.clear();
        element.sendKeys(inputText);
    }

    public static String getPageUrl() {
       return getDriver().getCurrentUrl();
    }

    public static void fillInput(WebElement element, String text) {
        element.clear();
        Utils.sendKeys(element, text);
    }

    public static void click(WebElement element) {
        waitForVisibility(element, GLOBAL_TIME_OUT);
        highlightElement(element);
        waitForClickablility(element).click();
        waitForPageToLoad();
    }

    public static void clickWithJS(WebElement element) {
        highlightElement(element);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void clickWithActions(WebElement element) {
        highlightElement(element);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).click(element).build().perform();
    }

    public static WebElement getElement(Object element) {
        if(element instanceof WebElement) {
            return new WebDriverWait(getDriver(), Duration.ofSeconds(GLOBAL_TIME_OUT)).until(ExpectedConditions.visibilityOf((WebElement) element));
        } else {
            return new WebDriverWait(getDriver(), Duration.ofSeconds(GLOBAL_TIME_OUT)).until(ExpectedConditions.visibilityOfElementLocated((By) element));
        }
    }

    public static String getElementValueByJS(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        String elementText = (String) js.executeScript("return arguments[0].value;", webElement);
        return elementText;
    }

    public static String getScreenshot(String name) throws IOException {
        var date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        var ts = (TakesScreenshot) getDriver();
        var source = ts.getScreenshotAs(OutputType.FILE);
        var target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        var finalDestination = new File(target);
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static void highlightElement(WebElement element) {
        scrollToElement(element);
        var highlightScript = "arguments[0].style.border='3px solid green';";
        ((JavascriptExecutor) getDriver()).executeScript(highlightScript, element);
    }

    public static void performKeyboardSendKey(WebElement webElement, String keys) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(webElement).click().sendKeys(keys)
                .build()
                .perform();
        sleep(1);
    }

    public static void refreshThePage() {
        getDriver().navigate().refresh();
        waitForPageToLoad();
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getElement(element));
    }

    public static void scrollDown(int scrollSize) {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0," + scrollSize + ")", "");
    }

    public static void scrollBottom() {
        JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
        jsx.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

    public static void sendKeys(WebElement element, String data) {
        getElement(element).sendKeys(data);
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void switchToWindowByTitle(String targetTitle) {
        var origin = getDriver().getWindowHandle();
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
            if (getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        getDriver().switchTo().window(origin);
    }

    public static void switchToWindowNew(int number) {
        List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tumWindowHandles.get(number));
        Driver.getDriver().manage().window().maximize();
        sleep(3);
    }

    public static void switchToLastWindow() {
        List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tumWindowHandles.getLast());
    }

    public static WebElement waitForClickablility(WebElement element) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(GLOBAL_TIME_OUT)).until(ExpectedConditions.elementToBeClickable(getElement(element)));
    }

    public static void waitForInvisibilityOf(WebElement webElement) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(GLOBAL_TIME_OUT)).until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(GLOBAL_TIME_OUT)).until(expectation);
        } catch (Throwable ignored) {}
    }
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void makeElementVisible(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('style','visibility:visible;');", element);
    }

}
