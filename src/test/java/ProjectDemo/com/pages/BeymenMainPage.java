package ProjectDemo.com.pages;

import com.utilities.Driver;
import com.utilities.ExcelProcessManager;
import com.utilities.Utils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.utilities.ExcelProcessManager.excelRowData;
import static org.junit.Assert.assertEquals;


@Log4j2
public class BeymenMainPage extends BasePage {

    @FindBy(className = "ot-sdk-container")
    private WebElement cookiesContainer;
    @FindBy(xpath = "//div[@id='onetrust-consent-sdk']//div[@class='accept-btn-container']/button")
    private WebElement acceptCookiesButton;
    @FindBy(xpath = "//div[@id='onetrust-consent-sdk']//div[@class='reject-btn-container']/button")
    private WebElement rejectCookiesButton;
    @FindBy(xpath = "(//input[@class='o-header__search--input'])[last()]")
    private WebElement searchInput;
    @FindBy(className = "o-header__search--close")
    private WebElement clearTextButton;
    @FindBy(className = "//div[contains(@class,'genderPopup')]")
    private List<WebElement> genderPopup;


    public BeymenMainPage(WebDriver driver) {
        super(driver);
    }
    private void waitForCookiesContainer(){
        Utils.waitForVisibility(cookiesContainer,15);
    }

    public void clickAcceptCookiesButton() {
        waitForCookiesContainer();
        Utils.click(acceptCookiesButton);
    }

    public void rejectCookiesButton() {
        waitForCookiesContainer();
        Utils.click(rejectCookiesButton);
        log.debug("-----Cookies are rejected-----");
    }

    public void selectGenderForCustomization(String gender){
        if(!genderPopup.isEmpty()){
            String xpath = "//div[contains(@class,'genderPopup')]//button[contains(text(),'" + gender + "')]";
            Utils.waitForVisibility(By.xpath(xpath), 10);
            WebElement genderButton = Driver.getDriver().findElement(By.xpath(xpath));
            Utils.click(genderButton);
            log.debug("-----"+gender+" is selected-----");
        }
    }

    public void verifyPageIsOpened(String url) {
        assertEquals(url, Utils.getPageUrl());
        log.info("-----Main page is opened.-----");
    }

    public void searchProduct(String productName) {
        Utils.fillInput(searchInput, productName);
        Utils.sleep(10);
    }

    public void searchProductByExcelData(int ordinal){
        log.debug("ordinal = " + excelRowData.get(ordinal-1));
        Utils.fillInput(searchInput, excelRowData.get(ordinal-1));
        Utils.sleep(5);
    }

    public void searchProductByExcelData(int rowNumber, int columnNumber){
        String selectedData = ExcelProcessManager.getData(rowNumber - 1, columnNumber - 1);
        log.debug("data = " + selectedData);
        Utils.fillInput(searchInput, selectedData);
        Utils.sleep(5);
    }

    public void clearSearchText(){
        Utils.click(clearTextButton);
    }

    public void pressEnterAfterSearchFilled(){
        Utils.performKeyboardSendKey(searchInput, "\uE007");
    }
}
