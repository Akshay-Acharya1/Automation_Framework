package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class searchPage extends BasePage{

	// Constructor to initialize elements
	public searchPage(WebDriver driver) {
		super(driver);
	
	}

	WebDriver driver;


    // Locators using @FindBy
    @FindBy(name = "search")
    private WebElement searchBox;

    @FindBy(css = "button.btn.btn-default.btn-lg")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='caption']//h4/a")
    private WebElement searchResult;

    @FindBy(xpath = "//p[contains(text(), 'no product')]")
    private WebElement noResultMessage;

    
    // Actions
    public void enterSearchKeyword(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public boolean isProductDisplayed() {
        return searchResult.isDisplayed();
    }

    public String getNoResultMessage() {
        if (noResultMessage.isDisplayed()) {
            return noResultMessage.getText();
        }
        return null;
    }

    public String getFirstProductName() {
        return searchResult.getText();
    }
}
