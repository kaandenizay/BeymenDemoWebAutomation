package ProjectDemo.com.pages;

import com.utilities.Utils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;


@Log4j2
public class ProductSearchPage extends BasePage {
    @FindBy(className = "m-productCard__desc")
    private List<WebElement> productTitleDescs;


    public ProductSearchPage(WebDriver driver) {
        super(driver);
    }
    private void waitForProductsListed(){
        Utils.waitForVisibility(By.className("m-productCard__title"), 15);
    }

    public void selectRandomProduct(){
        waitForProductsListed();
        Random random = new Random();
        log.trace("productTitles.size() = " + productTitleDescs.size());
        int randomIndex = random.nextInt(productTitleDescs.size());
        log.debug("Selected product = " + productTitleDescs.get(randomIndex).getText().trim());
        productTitleDescs.get(randomIndex).click();
        Utils.sleep(5);
    }

}
