package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistrationPAge extends BasePage {

	public AccountRegistrationPAge(WebDriver driver) {

		super(driver);

	}

	@FindBy(id = "input-firstname")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id=\"input-lastname\"]")
	WebElement textLastname;

	@FindBy(id = "input-email")
	WebElement textEmail;

	@FindBy(id = "input-telephone")
	WebElement textPhoneNumber;

	@FindBy(id = "input-password")
	WebElement textPassword;

	@FindBy(id = "input-confirm")
	WebElement textconfPassword;

	@FindBy(name = "agree")
	WebElement checkPolicy;

	@FindBy(xpath = "//input[@value=\"Continue\"]")
	WebElement btncontinue;

	@FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
	WebElement msgconfirmation;

	public void setFirstName(String Fname) {
		txtFirstName.sendKeys(Fname);

	}

	public void setLastName(String Lname) {
		textLastname.sendKeys(Lname);

	}

	public void setEmail(String email) {
		textEmail.sendKeys(email);

	}

	public void setTelephonel(String tel) {
		textPhoneNumber.sendKeys(tel);

	}

	public void setPassword(String Pwd) {
		textPassword.sendKeys(Pwd);

	}

	public void enter_ConfirmPassword(String ConfirmPass) {
		textconfPassword.sendKeys(ConfirmPass);
	}

	public void setPrivacy() {
		checkPolicy.click();

	}

	public void ClickContinue() {
		btncontinue.click();
	}

	public String getConfirmationMsg() {

		try {
			return (msgconfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}

	}

}
