package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtilities {

	   WebDriver driver;

	    public ActionUtilities(WebDriver driver) {
	    	
	        this.driver = driver;
	    }
	    /**
	     * Hover over an element.
	     * @param element WebElement to hover over.
	     */
	    public void hoverOverElement(WebElement element) {
	        Actions actions = new Actions(driver);
	        actions.moveToElement(element).perform();
	    }

	    /**
	     * Right-click on an element.
	     * @param element WebElement to right-click on.
	     */
	    public void rightClickOnElement(WebElement element) {
	        Actions actions = new Actions(driver);
	        actions.contextClick(element).perform();
	    }

	    /**
	     * Double-click on an element.
	     * @param element WebElement to double-click on.
	     */
	    public void doubleClickOnElement(WebElement element) {
	        Actions actions = new Actions(driver);
	        actions.doubleClick(element).perform();
	    }

	    /**
	     * Drag and drop an element from source to target.
	     * @param source WebElement to drag.
	     * @param target WebElement to drop.
	     */
	    public void dragAndDrop(WebElement source, WebElement target) {
	        Actions actions = new Actions(driver);
	        actions.dragAndDrop(source, target).perform();
	    }

	    /**
	     * Click and hold an element.
	     * @param element WebElement to click and hold.
	     */
	    public void clickAndHold(WebElement element) {
	        Actions actions = new Actions(driver);
	        actions.clickAndHold(element).perform();
	    }

	    /**
	     * Release a clicked and held element.
	     * @param element WebElement to release.
	     */
	    public void releaseElement(WebElement element) {
	        Actions actions = new Actions(driver);
	        actions.release(element).perform();
	    }
	}