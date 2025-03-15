package ProjectDemo.com.step_definitions.gui;

import ProjectDemo.com.pages.*;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import com.utilities.TextProcessManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * This class is designed for Cucumber run option
 * If we run in Junit, we don't need this class
 *
 */

@Log4j2
@AllArgsConstructor
public class BeymenStepDefinition {
     BeymenMainPage beymenMainPage;
     ProductSearchPage productSearchPage;
     ProductDetailPage productDetailPage;
     ShoppingCartPage shoppingCartPage;

    @Given("User goes to the Beymen Main Page on web")
    public void userGoesToTheBeymenMainPageOnWeb() {
        Driver.getDriver().get(ConfigurationReader.getProperty("beymen.url"));
    }

    @Given("User rejects the cookies on Beymen Main Page")
    public void userRejectsTheCookiesOnBeymenMainPage() {
        beymenMainPage.rejectCookiesButton();
    }

    @Given("User accepts the cookies on Beymen Main Page")
    public void userAcceptsTheCookiesOnBeymenMainPage() {
        beymenMainPage.clickAcceptCookiesButton();
    }

    @Given("User selects {string} for customized gender option on Beymen Main Page")
    public void userSelectsForCustomizedGenderOptionOnBeymenMainPage(String genderOption) {
        beymenMainPage.selectGenderForCustomization(genderOption);

    }

    @Then("User verifies the {string} page is opened successfully")
    public void userVerifiesThePageIsOpenedSuccessfully(String url) {
        beymenMainPage.verifyPageIsOpened(url);
    }

    @When("User enters {string} to the search bar on Beymen Main Page")
    public void userEntersToTheSearchBarOnBeymenMainPage(String searchText) {
        beymenMainPage.searchProduct(searchText);
    }

    @When("User enters {int} th product name from Excel file to the search bar on Beymen Main Page")
    public void userEntersThProductNameFromExcelFileToTheSearchBarOnBeymenMainPage(int ordinal) {
        beymenMainPage.searchProductByExcelData(ordinal);
    }

    @And("User clicks the delete button for cleaning search text on Beymen Main Page")
    public void userClicksTheDeleteButtonForCleaningSearchTextOnBeymenMainPage() {
        beymenMainPage.clearSearchText();
    }

    @And("User selects random product on after product search on Search Page")
    public void userSelectsRandomProductOnAfterProductSearchOnSearchPage() {
        productSearchPage.selectRandomProduct();
    }

    @And("User clicks the enter button on keyboard after search input filled on Beymen Main Page")
    public void userClicksTheEnterButtonOnKeyboardAfterSearchInputFilledOnBeymenMainPage() {
        beymenMainPage.pressEnterAfterSearchFilled();
    }


    @And("User saves {string} to the text file on Product Detail Page")
    public void userSavesToTheTextFileOnProductDetailPage(String parameter) {
        productDetailPage.writeProductDetailToText(parameter);
    }

    @And("User clicks the add to cart button on Product Detail Page")
    public void userClicksTheAddToCartButtonOnProductDetailPage() {
        productDetailPage.clickAddToCart();
    }

    @And("User selects random product size on Product Detail Page")
    public void userSelectsRandomProductSizeOnProductDetailPage() {
        productDetailPage.selectRandomSize();
    }

    @And("User clicks the go to cart button on Product Detail Page")
    public void userClicksTheGoToCartButtonOnProductDetailPage() {
        productDetailPage.clickGoToCart();
    }

    @Then("User should see the product price is correctly shown on Shopping Cart Page")
    public void userShouldSeeTheProductPriceIsCorrectlyShownOnShoppingCartPage() {
        shoppingCartPage.verifyProductPrice();

    }

    @When("User deletes the created product data file")
    public void userDeletesTheCreatedProductDataFile() {
        TextProcessManager.deleteFile();
    }

    @When("User increments the quantity as {string} for product on Shopping Cart Page")
    public void userIncrementsTheQuantityAsForProductOnShoppingCartPage(String quantity) {
        shoppingCartPage.selectQuantityOption(quantity);
    }

    @Then("User should see the quantity as {string} for product on Shopping Cart Page")
    public void userShouldSeeTheQuantityAsForProductOnShoppingCartPage(String quantity) {
        shoppingCartPage.verifyQuantityNumber(quantity);
    }

    @When("User clicks the remove button for product on Shopping Cart Page")
    public void userClicksTheRemoveButtonForProductOnShoppingCartPage() {
        shoppingCartPage.removeProductFromCart();
    }

    @Then("User should see the shopping cart is empty on Shopping Cart Page")
    public void userShouldSeeTheShoppingCartIsEmptyOnShoppingCartPage() {
        shoppingCartPage.verifyEmptyShoppingCart();
    }
}