package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.io.IOException;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.ExcelUtility;

public class TC04_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData")
    public void Verify_LoginSST(String email, String PWD, String Exp) {

        logger.info("Starting Test Case: TC04_LoginDDT"); // Log test start

        try {
            logger.info("Navigating to the Home Page");
            HomePage HP = new HomePage(driver);

            logger.info("Clicking on 'My Account'");
            HP.ClickOnMYaccount();
            HP.clickLogin();

            // Login
            logger.info("Attempting login with email: " + email + " and password: " + PWD);
            LoginPage LP = new LoginPage(driver);
            LP.setEmailAddress(email);
            LP.setPassword(PWD);
            LP.ClickLogin();

            // MyAccount Verification
            MyAccountPage mcc = new MyAccountPage(driver);
            boolean targetPage = mcc.isMyaccountPage();

            if (Exp.equalsIgnoreCase("Valid")) {
                logger.info("Expected login status: Valid");
                if (targetPage) {
                    logger.info("Login successful, navigating to logout");
                    mcc.ClickLogout();
                    Assert.assertTrue(true, "Login test passed for valid credentials");
                    logger.info("Logout successful");
                } else {
                    logger.error("Login test failed for valid credentials");
                    Assert.assertTrue(false, "Login test failed for valid credentials");
                }
            }

            if (Exp.equalsIgnoreCase("Invalid")) {
                logger.info("Expected login status: Invalid");
                if (targetPage) {
                    logger.error("Login test failed for invalid credentials");
                    Assert.assertTrue(false, "Login test failed for invalid credentials");
                } else {
                    logger.info("Login test passed for invalid credentials");
                    Assert.assertTrue(true, "Login test passed for invalid credentials");
                }
            }

        } catch (Exception e) {
            logger.error("An exception occurred during the login test: " + e.getMessage());
            Assert.fail("Test case execution failed due to an exception");
        }

        logger.info("Test Case Completed: TC04_LoginDDT"); // Log test completion
    }

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        logger.info("Fetching data from Excel file for data-driven testing");

        String path = "/Users/ranjeetkendre/eclipse-workspace/14SetAutomation_Framework/testData/TestData.xlsx"; // Excel file path
        ExcelUtility xlutil = new ExcelUtility(path); // Create an object for ExcelUtility

        int totalRows = xlutil.getRowCount("Sheet1"); // Get total rows
        int totalCols = xlutil.getCellCount("Sheet1", 1); // Get total columns

        logger.info("Total rows: " + totalRows + ", Total columns: " + totalCols);

        String[][] loginData = new String[totalRows][totalCols]; // Create 2D array to store data

        // Read the data from Excel and store it in the 2D array
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
                logger.info("Read data from Excel: [" + (i - 1) + "][" + j + "] = " + loginData[i - 1][j]);
            }
        }

        logger.info("Data fetching completed");
        return loginData; // Return 2D array
    }
}
