package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    // Constructor to initialize the elements

	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath =  "//span[@id=\"cart-total\"]")
    WebElement cartButton;

    @FindBy(css = "ul.dropdown-menu li")
    WebElement cartItems;

    
    // Actions
    public String getCartItemDetails() {
        cartButton.click(); // Open the cart dropdown
        return cartItems.getText();
    }
}

//span[@id="cart-total"]