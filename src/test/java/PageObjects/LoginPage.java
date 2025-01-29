package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-email")
	WebElement textEmailAddress;

	@FindBy(id = "input-password")
	WebElement textPassword;

	@FindBy(xpath = "//input[@value=\"Login\"]")
	WebElement btnLogin;

	public void setEmailAddress(String email) {

		textEmailAddress.sendKeys(email);
	}

	public void setPassword(String pass) {

		textPassword.sendKeys(pass);
	}

	public void ClickLogin() {

		btnLogin.click();
	}
}
