package testCases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import testBase.BaseClass;
import utilities.ActionUtilities;

public class TC06_TestHoverAndClickAction extends BaseClass {

    @Test
    public void testHoverAndClick() {
        logger.info("Starting test case: testHoverAndClick"); // Log test start

        ActionUtilities Ac = new ActionUtilities(driver);
        logger.info("Initialized ActionUtilities");

        HomePage HM = new HomePage(driver);
        logger.info("Initialized HomePage object");

        try {
            // Hover over the "Desktops" menu
            logger.info("Hovering over the 'Desktops' menu");
            WebElement desktopsMenu = HM.getDesktopsMenu();
            Ac.hoverOverElement(desktopsMenu);
            logger.info("Successfully hovered over 'Desktops' menu");

            // Click the "Show All Desktops" submenu
            logger.info("Hovering over 'Show All Desktops' submenu");
            WebElement showAllDesktops = HM.getShowAllDesktopsSubmenu();
            Ac.hoverOverElement(showAllDesktops);
            logger.info("Successfully hovered over 'Show All Desktops' submenu");

            logger.info("Clicking on 'Show All Desktops'");
            showAllDesktops.click();
            logger.info("Successfully clicked on 'Show All Desktops'");

        } catch (Exception e) {
            logger.error("An error occurred during hover and click actions: " + e.getMessage());
            throw e; // Re-throw the exception for test failure reporting
        }

        logger.info("Completed test case: testHoverAndClick"); // Log test completion
    }
}
