package ProjectDemo.com.pages;

import com.utilities.TextProcessManager;
import com.utilities.Utils;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


@Log4j2
public class ShoppingCartPage extends BasePage {

    @FindBy(xpath = "//div[@class='priceBox']/div[last()]/span[last()]")
    private WebElement productPriceText;
    @FindBy(id = "quantitySelect0-key-0")
    private WebElement quantitySelectbox;
    @FindBy(id = "removeCartItemBtn0-key-0")
    private WebElement removeButtonForProduct;
    @FindBy(id = "notifyTitle")
    private WebElement notificationTitle;
    @FindBy(className = "m-notification__close")
    private WebElement notificationCloseIcon;
    @FindBy(className = "m-empty__messageTitle")
    private WebElement emptyCartText;


    private final static String deleteSuccessMessage = "Ürün Silindi";
    private final static String emptyCartMessage = "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void verifyProductPrice(){
        Utils.waitForVisibility(productPriceText, 15);
        String savedPrice = TextProcessManager.readFile("Product Price");
        Assert.assertEquals(savedPrice, productPriceText.getText().replaceAll(",00", ""));
    }

    public void selectQuantityOption(String value){
        Select quantitySelect = new Select(quantitySelectbox);
        List<WebElement> quantityOptions = quantitySelect.getOptions();
        List<String> options = quantityOptions.stream().map(option -> option.getAttribute("value")).toList();
        if(options.contains(value)){
            quantitySelect.selectByValue(value);
            Utils.sleep(2);
        }
        else{
            log.warn("No option available for this product");
            fail();
        }
    }

    public void verifyQuantityNumber(String value){
        Assert.assertEquals(value + " adet", quantitySelectbox.getAttribute("aria-label"));
        Utils.click(notificationCloseIcon);
        Utils.sleep(3);
    }

    public void removeProductFromCart(){
        Utils.click(removeButtonForProduct);
    }

    public void verifyEmptyShoppingCart(){
        Utils.waitForVisibility(By.id("notifyTitle"), 10);
        Assert.assertEquals(deleteSuccessMessage,notificationTitle.getText().trim());
        Assert.assertEquals(emptyCartMessage, emptyCartText.getText().trim());
        log.debug(emptyCartText.getText().trim() + " message is displayed");
    }


}
