package ProjectDemo.com.junitTests;

import ProjectDemo.com.listeners.Listener;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(Listener.class)
@Log4j2
public class BaseTests {

    protected WebDriver driver;

    @BeforeAll
    public void setup() {
        log.info("-----Tests are starting-----");
        Driver.getDriver().get(ConfigurationReader.getProperty("beymen.url"));
        log.debug("-----Navigating main page-----");
        Driver.getDriver().manage().timeouts().getPageLoadTimeout();
        Driver.getDriver().manage().window().maximize();
    }

    @BeforeEach
    public void callDriver(){
        driver = Driver.getDriver();
        log.debug("-----Driver is started-----");
    }

    /**
     * To take screenshot properly
     * this annotation is closed and distributed to the Listeners
     */
//    @AfterEach
//    public void tearDown(){
//        Driver.quitDriver();
//        System.out.println("Browser closed");
//    }

    @AfterAll
    public static void terminateTest(){
        Driver.removeThreadPool();
        log.info("-----Tests are completed-----");
    }
}
