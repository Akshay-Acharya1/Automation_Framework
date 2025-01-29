package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	WebDriver driver;

	// Step 1
	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	WebElement LinkMyAccount;

	@FindBy(xpath = "//a[contains(text(),'Register')]")
	WebElement LinkRegister;

	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement LoginButton;

	@FindBy(tagName = "a")
	List<WebElement> allLinks;

	@FindBy(tagName = "img")
	List<WebElement> allImages;

	@FindBy(linkText = "Desktops")
	WebElement desktopsMenu;

	@FindBy(linkText = "Show AllDesktops")
	WebElement showAllDesktopsSubmenu;

	@FindBy(name = "search")
	WebElement searchBox;

	@FindBy(css = "button.btn.btn-default.btn-lg")
	WebElement searchButton;

	public HomePage(WebDriver driver) {

		super(driver);

	}

	public void ClickOnMYaccount() {

		LinkMyAccount.click();
	}

	public void clikRegister() {

		LinkRegister.click();
	}

	public void clickLogin() {

		LoginButton.click();
	}

	public List<WebElement> getAllLinksAndImages() {
		List<WebElement> elements = new ArrayList<>();
		elements.addAll(allLinks);
		elements.addAll(allImages);
		return elements;
	}

	// Method to filter active links (non-JavaScript, non-null)
	public List<WebElement> getActiveLinks(List<WebElement> allElements) {
		List<WebElement> activeLinks = new ArrayList<>();
		for (WebElement element : allElements) {
			try {
				String href = element.getAttribute("href");
				if (href != null && !href.contains("javascript")) {
					activeLinks.add(element);
				}
			} catch (Exception e) {
				System.out.println("Error processing element: " + element.getText());
			}
		}
		return activeLinks;
	}

	public WebElement getDesktopsMenu() {
		return desktopsMenu;
	}

	public WebElement getShowAllDesktopsSubmenu() {
		return showAllDesktopsSubmenu;
	}

	public void searchProduct(String productName) {
		searchBox.sendKeys(productName);
		searchButton.click();
	}
}
