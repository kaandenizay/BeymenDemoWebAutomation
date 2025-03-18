package ProjectDemo.com.junitTests;

import ProjectDemo.com.listeners.Listener;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import com.utilities.Utils;
import jdk.jshell.execution.Util;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(Listener.class)
@Log4j2
public class BaseTests {

    protected WebDriver driver;

    @BeforeAll
    public void setup() {
        log.info("-----Tests are starting-----");
        Driver.getDriver().manage().timeouts().getPageLoadTimeout();
        Driver.getDriver().manage().window().maximize();
    }

    @BeforeEach
    public void callDriver(){
        Driver.getDriver().get(ConfigurationReader.getProperty("beymen.url"));
        log.debug("-----Navigating main page-----");
        driver = Driver.getDriver();
        log.debug("-----Driver is started-----");
    }

    @AfterEach
    public static void terminateTest(TestInfo testInfo){
        Utils.takeScreenshot(testInfo.getDisplayName());
        Driver.closeDriver();
        log.info("-----Tests are completed-----");
    }
}
