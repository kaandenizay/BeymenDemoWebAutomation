package ProjectDemo.com.listeners;

import com.utilities.Driver;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.utilities.Utils.destFile;
import static com.utilities.Utils.src;

@Log4j2
public class Listener implements TestWatcher {

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info("--------------------------");
        log.info("This test was disabled: " + context.getTestMethod()
                + " - with reason: " + reason);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("--------------------------");
        log.info("This test was successful: " + context.getTestMethod());

    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("--------------------------");
        log.info("This test was aborted: " + cause.getMessage());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("--------------------------");
        try {
            FileUtils.copyFile(src, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("This test was failed: " + cause.getMessage());
    }
}
