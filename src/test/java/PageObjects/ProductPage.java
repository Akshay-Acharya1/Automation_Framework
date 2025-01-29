package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

	    // Locators using @FindBy
	    @FindBy(xpath = "//span[contains(text(),'Add to Cart')]")
	    WebElement addToCartButton;

	    @FindBy(css = ".alert.alert-success")
	    WebElement successMessage;

	    // Constructor to initialize the elements
	    public ProductPage(WebDriver driver) {
	        super(driver);
	       
	    }

	    // Actions
	    public void clickAddToCart() {
	        addToCartButton.click();
	    }

	    public String getSuccessMessage() {
	        return successMessage.getText();
	    }
	    
}
