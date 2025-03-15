package ProjectDemo.com.junitTests;

import ProjectDemo.com.pages.BeymenMainPage;
import ProjectDemo.com.pages.ProductDetailPage;
import ProjectDemo.com.pages.ProductSearchPage;
import ProjectDemo.com.pages.ShoppingCartPage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;

@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BeymenTests extends BaseTests{

    static BeymenMainPage beymenMainPage;
    static ProductDetailPage productDetailPage;
    static ProductSearchPage productSearchPage;
    static ShoppingCartPage shoppingCartPage;

    @BeforeEach
    public void initializePages(){
        beymenMainPage = new BeymenMainPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        productSearchPage = new ProductSearchPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);

    }

    @Test
    @DisplayName("Search Product, Add To Cart and Clear Cart")
    void searchProduct(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("-----" + methodName +  " test started-----");
        beymenMainPage.rejectCookiesButton();
        beymenMainPage.selectGenderForCustomization("ERKEK");
        beymenMainPage.verifyPageIsOpened("https://www.beymen.com/tr");

//        ExcelProcessManager.getExcelRowData();
        beymenMainPage.searchProductByExcelData(1,1);
        beymenMainPage.clearSearchText();
        beymenMainPage.searchProductByExcelData(1,2);
        beymenMainPage.pressEnterAfterSearchFilled();

        log.info("-----Product Search Page is opened-----");
        productSearchPage.selectRandomProduct();

        log.info("-----Product Detail Page is opened-----");
        productDetailPage.writeProductDetailToText("Product Description");
        productDetailPage.writeProductDetailToText("Product Price");
        productDetailPage.selectRandomSize();
        productDetailPage.clickAddToCart();
        productDetailPage.clickGoToCart();

        log.info("-----Shopping Cart Page is opened-----");
        shoppingCartPage.verifyProductPrice();
        shoppingCartPage.selectQuantityOption("2");
        shoppingCartPage.verifyQuantityNumber("2");
        shoppingCartPage.removeProductFromCart();
        shoppingCartPage.verifyEmptyShoppingCart();

        log.info("-----" + methodName +  " test end-----");
    }
}
