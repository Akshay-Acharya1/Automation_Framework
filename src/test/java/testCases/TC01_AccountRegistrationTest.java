package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import PageObjects.AccountRegistrationPAge;
import PageObjects.HomePage;
import testBase.BaseClass;
import utilities.Genericutils;

public class TC01_AccountRegistrationTest extends BaseClass {
    Genericutils Ge = new Genericutils(); // Initialize Genericutils object

    @Test
    public void Verifry_Account_Registration() {
        logger.info("***** Starting Test Case: TC01_AccountRegistrationTest *****");

        try {
            // Navigate to MyAccount
            logger.info("Navigating to the Home Page.");
            HomePage HP = new HomePage(driver);

            logger.info("Clicking on 'My Account'.");
            HP.ClickOnMYaccount();

            logger.info("Clicking on 'Register'.");
            HP.clikRegister();

            // Fill in registration details
            logger.info("Filling out registration details.");
            AccountRegistrationPAge AP = new AccountRegistrationPAge(driver);

            String firstName = Ge.randomString().toLowerCase();
            logger.info("Generated First Name: " + firstName);
            AP.setFirstName(firstName);

            String lastName = Ge.randomString().toUpperCase();
            logger.info("Generated Last Name: " + lastName);
            AP.setLastName(lastName);

            String email = Ge.randomAplaNumeric() + "@gmail.com";
            logger.info("Generated Email: " + email);
            AP.setEmail(email);

            String phone = Ge.randomNumber();
            logger.info("Generated Phone Number: " + phone);
            AP.setTelephonel(phone);

            String password = Ge.randomAplaNumeric();
            logger.info("Generated Password: [hidden]");
            AP.setPassword(password);
            AP.enter_ConfirmPassword(password);

            logger.info("Accepting privacy policy.");
            AP.setPrivacy();

            logger.info("Clicking on 'Continue' to complete registration.");
            AP.ClickContinue();

            // Validate confirmation message
            logger.info("Validating expected confirmation message.");
            String confmsg = AP.getConfirmationMsg();
            logger.info("Confirmation Message Retrieved: " + confmsg);

            if (confmsg.equals("Your Account Has Been Created!")) {
                logger.info("Account registration successful.");
                Assert.assertTrue(true);
            } else {
                logger.error("Account registration failed. Expected message not found.");
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            logger.error("An exception occurred during account registration: " + e.getMessage());
            Assert.fail("Test case failed due to an exception.");
        }

        logger.info("***** Finished Test Case: TC01_AccountRegistrationTest *****");
    }
}
