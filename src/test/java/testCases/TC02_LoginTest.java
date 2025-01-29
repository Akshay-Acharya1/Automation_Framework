package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verify_Login() {
        logger.info("***** Starting Test Case: TC02_LoginTest *****"); // Log test start

        try {
            // Home Page
            logger.info("Navigating to the Home Page.");
            HomePage HP = new HomePage(driver);

            logger.info("Clicking on 'My Account'.");
            HP.ClickOnMYaccount();

            logger.info("Clicking on 'Login'.");
            HP.clickLogin();

            // Login Page
            logger.info("Navigating to the Login Page.");
            LoginPage LP = new LoginPage(driver);

            logger.info("Entering email address: " + UserEmail);
            LP.setEmailAddress(UserEmail);

            logger.info("Entering password: [hidden]");
            LP.setPassword(password);

            logger.info("Clicking on 'Login' button.");
            LP.ClickLogin();

            // My Account Page
            logger.info("Navigating to the 'My Account' page.");
            MyAccountPage MA = new MyAccountPage(driver);

            logger.info("Validating if 'My Account' page is displayed.");
            boolean text = MA.isMyaccountPage();
            Assert.assertTrue(text, "Failed to navigate to 'My Account' page.");

            logger.info("'My Account' page validated successfully. Logging out.");
            MA.ClickLogout();

        } catch (Exception e) {
            logger.error("An exception occurred during the login test: " + e.getMessage());
            Assert.fail("Test case failed due to an exception.");
        }

        logger.info("***** Test Case Completed: TC02_LoginTest *****"); // Log test completion
    }
}
