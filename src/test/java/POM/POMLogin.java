package POM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.walmart.Base.Base;

public class POMLogin extends Base {

	@FindBy(id = "username")
	WebElement Username;
	@FindBy(id = "password")
	WebElement Password;
	@FindBy(css = "#login-form > div > div:nth-child(3) > div.css-1alnb86.erl5ymg0 > button")
	WebElement Show;
	@FindBy(css = "#rememberme")
	WebElement KeepSignIn;
	@FindBy(css = "#login-form > div > div:nth-child(7) > button")
	WebElement SignIn;
	@FindBy(linkText = "Create account")
	WebElement SignUp;

	public POMLogin() {
		PageFactory.initElements(driver, this);
	}

	public void Usernme(String U) {
		Username.sendKeys(U);
	}

	public void Passwd(String P) {
		Password.sendKeys(P);
	}

	public void Shw() {
		Show.click();
	}

	public void Remember() {
		KeepSignIn.click();
	}

	public void Submit() {
		SignIn.click();
	}

	public void Signup() {
		SignUp.click();
	}
}
