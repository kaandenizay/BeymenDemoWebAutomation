package ProjectDemo.com.listeners;

import com.utilities.Driver;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Log4j2
public class Listener implements TestWatcher {

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info("--------------------------");
        log.info("This test was disabled: " + context.getTestMethod()
                + " - with reason: " + reason);
        Driver.quitDriver();
        log.info("Browser closed");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("--------------------------");
        log.info("This test was successful: " + context.getTestMethod());
        Driver.quitDriver();
        log.info("Browser closed");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("--------------------------");
        log.info("This test was aborted: " + cause.getMessage());
        Driver.quitDriver();
        log.info("Browser closed");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("--------------------------");
        log.info("This test was failed: " + cause.getMessage());
        try {
            log.info("Taking screenshot for failed assert");
            var date = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
            String screenshotPath = System.getProperty("user.dir") + "/target/Screenshots/";
            File src = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotName = "screenshot_" + context.getDisplayName().replaceAll("\\s", "") + "_" + date + ".png";
            screenshotPath = screenshotPath + File.separator + screenshotName;
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(src, destFile);
        }catch (Exception e){
            log.error("Exception while taking screenshot "+e.getMessage());
        }
        Driver.quitDriver();
        log.info("Browser closed");
    }
}
