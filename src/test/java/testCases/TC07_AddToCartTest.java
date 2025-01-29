package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.ProductPage;
import testBase.BaseClass;

public class TC07_AddToCartTest extends BaseClass {

    @Test
    public void testCartItemDetails() throws InterruptedException {
        logger.info("Starting test case: TC07_AddToCartTest"); // Log test start

        try {
            // Search for a product
            logger.info("Navigating to Home Page and searching for 'iPhone'");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iPhone");
            logger.info("'iPhone' search initiated");

            // Add product to the cart
            logger.info("Navigating to Product Page to add 'iPhone' to the cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.clickAddToCart();
            logger.info("'iPhone' added to the cart successfully");

            // Wait for the cart to update
            Thread.sleep(3000);

            // Verify the cart contains the added product
            logger.info("Navigating to Cart Page to verify cart details");
            CartPage cartPage = new CartPage(driver);
            String cartDetails = cartPage.getCartItemDetails();
            logger.info("Cart details retrieved: " + cartDetails);

            Thread.sleep(3000);

            // Assertion to verify the product in the cart
            Assert.assertTrue(cartDetails.contains("iPhone"), "Cart does not contain the expected product!");
            
            logger.info("Verification successful: 'iPhone' is present in the cart");

        } catch (Exception e) {
            logger.error("An error occurred during the Add to Cart test: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test in case of an error
        }

        logger.info("Test case completed: TC07_AddToCartTest"); // Log test completion
    }
}
