package ProjectDemo.com.step_definitions.gui;
import com.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


/**
 * This class is designed for Cucumber run option
 * If we run in Junit, we don't need this class
 *
 */
public class HooksUI {
    public static ThreadLocal<String> scenarioName;

    @Before
    public void before_all() {
        Driver.getDriver().manage().timeouts().getPageLoadTimeout();
        Driver.getDriver().manage().window().maximize();
    }




    @After
  public void tearDown(Scenario scenario) {
      try {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
           if (scenario.isFailed()) {
               scenario.attach(screenshot, "image/png", "screenshots");
            }
       } catch (Exception e) {
            e.printStackTrace();
       } finally {
          System.out.println("This is After annotation in tearDown line 38");
//         Driver.closeDriver();
        }
   }

}
