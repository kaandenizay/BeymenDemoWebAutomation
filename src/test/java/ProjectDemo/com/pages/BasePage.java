package ProjectDemo.com.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public abstract class BasePage {

    protected WebDriver driver;

    /**
     * This constructor should be used in Cucumber run, not Junit
     */
//    public BasePage() {
//        PageFactory.initElements(getDriver(), this);
//    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

}