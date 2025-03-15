@beymen
Feature:Beymen Test Cases

  Background:
    Given User goes to the Beymen Main Page on web
    Given User rejects the cookies on Beymen Main Page
#    Given  User accepts the cookies on Beymen Main Page
    Given User selects "ERKEK" for customized gender option on Beymen Main Page
#    Given User selects "KADIN" for customized gender option on Beymen Main Page
    Then User verifies the "https://www.beymen.com/tr" page is opened successfully

  @BeymenSearchProduct
  Scenario: As a user, I want to search product name
#    When User enters "şort" to the search bar on Beymen Main Page
    When User reads data from excel file
    When User enters 1 th product name from Excel file to the search bar on Beymen Main Page
    And  User clicks the delete button for cleaning search text on Beymen Main Page
    When User enters 2 th product name from Excel file to the search bar on Beymen Main Page
    And  User clicks the enter button on keyboard after search input filled on Beymen Main Page
    And  User selects random product on after product search on Search Page

    And  User saves "Product Description" to the text file on Product Detail Page
    And  User saves "Product Price" to the text file on Product Detail Page
    And  User selects random product size on Product Detail Page
    And  User clicks the add to cart button on Product Detail Page
    And  User clicks the go to cart button on Product Detail Page

    Then User should see the product price is correctly shown on Shopping Cart Page
    When User increments the quantity as "2" for product on Shopping Cart Page
    Then User should see the quantity as "2" for product on Shopping Cart Page
    When User clicks the remove button for product on Shopping Cart Page
    Then User should see the shopping cart is empty on Shopping Cart Page
#    When User deletes the created product data file
