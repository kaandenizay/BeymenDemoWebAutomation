package ProjectDemo.com.pages;

import com.utilities.TextProcessManager;
import com.utilities.Utils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;


@Log4j2
public class ProductDetailPage extends BasePage {

    @FindBy(className = "o-productDetail__brandLink")
    private WebElement productBrand;
    @FindBy(className = "o-productDetail__description")
    private WebElement productDetailDescription;
    @FindBy(className = "m-price__lastPrice")
    private List<WebElement> productLastPrice;
    @FindBy(className = "m-price__new")
    private WebElement productNewPrice;
    @FindBy(xpath = "//div[@id='sizes']/div[@class='m-variation']/span[not(contains(@class,'-disabled'))]")
    private List<WebElement> productSizes;
    @FindBy(id = "addBasket")
    private WebElement addToCartButton;
    @FindBy(xpath = "//div[@class='m-notification success']//button[last()]")
    private WebElement goToCartButton;


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    private String getElementText(String parameter){
        return switch (parameter) {
            case "Product Description" -> productDetailDescription.getText().trim();
            case "Product Brand" -> productBrand.getText().trim();
            case "Product Price" -> productNewPrice.getText().trim();
            default -> "";
        };
    }
    public void writeProductDetailToText(String parameter){
        if(!parameter.equalsIgnoreCase("Product Price")){
            String finalText = parameter + " : " + getElementText(parameter);
            TextProcessManager.writeToFile(finalText);
        }else{
            writeProductSuitablePriceToText(parameter);
        }
    }
    private void writeProductSuitablePriceToText(String parameter){
        if(productLastPrice.isEmpty()){
            TextProcessManager.writeToFile(parameter + " : " + productNewPrice.getText().trim());
        }
        else{
            TextProcessManager.writeToFile(parameter + " : " + productLastPrice.getFirst().getText().trim());
        }
    }

    public void selectRandomSize(){
        Random random = new Random();
        int randomIndex = random.nextInt(productSizes.size());
        log.trace("Selected product size :" + productSizes.get(randomIndex).getText().trim());
        productSizes.get(randomIndex).click();
        Utils.sleep(3);
    }

    public void clickAddToCart(){
        Utils.clickWithJS(addToCartButton);
    }

    public void clickGoToCart(){
        Utils.click(goToCartButton);
    }


}
