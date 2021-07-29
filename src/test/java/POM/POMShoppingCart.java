package POM;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.walmart.Base.Base;

import utilities.ATCN;

public class POMShoppingCart extends Base {
	@FindBy(xpath = "//div[@class='css-nk32ej e1nkqonp3']//a[@aria-label='Your cart; is empty']//*[local-name()='svg']")
	WebElement SC;
	@FindBy(xpath = "//button[contains(text(),'Add to cart')]")
	List<WebElement> ATC;
	@FindBy(xpath = "//button[normalize-space()='+']") // increase quantity
	WebElement ADD;
	@FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
	WebElement CSH;
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement Checkout;
	@FindBy(xpath = "//button[@aria-label='Close']") // close shopping cart pop-up
	WebElement Close;
	@FindBy(xpath = "//span[contains(text(),'Remove')]")
	List<WebElement> Remove;
	@FindBy(xpath = "//a[@data-automation='product-item-title']")
	List<WebElement> SCPAGEItems;

	public POMShoppingCart() {
		PageFactory.initElements(driver, this);
	}

	// Method to click on Shopping Cart
	public void Scart() {
		SC.click();
		logger.info("Clicked on Shopping Cart.");
	}

	// This method will add one item to Shopping Cart(SC)
	public void ATCS() {
		List<WebElement> a = ATC;
		a.get(0).click();
		logger.info("Item added in Shopping Cart.");
	}

	// Method to increase quantity of same product in SC
	public void Add() {
		ADD.click();
		logger.info("Same item added in Shopping Cart.");
	}

	// Method to close SC pop-up
	public void close() {
		Close.click();
		logger.info("Closed Shopping Cart window.");
	}

	// Method to Add multiple items in SC , you can change item count in config file
	public void ATC2() throws InterruptedException {
		List<WebElement> b = ATC;
		for (int i = 0; i < ATCN.A; i++) {
			if (i == ATCN.A - 1) {
				down();
				Thread.sleep(1000);
				logger.info("Thread Sleep Time out of 1 second.");
				b.get(i).click();
				logger.info("Last item added to Shopping cart.");
				Thread.sleep(2000);
				logger.info("Thread Sleep Time out of 2 seconds.");
				Checkout();
			} else {
				b.get(i).click();
				logger.info("Item added to Shopping Cart");
				Thread.sleep(2000);
				logger.info("Thread Sleep Time out of 2 seconds.");
				Close.click();
				logger.info("Closed Shopping Cart window.");
			}

		}

	}

	// Method to click on Continue Shopping in SC
	public void CSHOP() {
		CSH.click();
		logger.info("Clicked on Continue Shopping.");
	}

	// Method to click on Checkout button
	public void Checkout() {
		Checkout.click();
		logger.info("Clicked on Checkout.");
	}

	// Method to Remove one item from SC
	public void Remove() {
		List<WebElement> a = Remove;
		a.get(0).click();
		logger.info("One Item removed from Shopping Cart.");
	}

	// Method to Remove all items from SC
	public void Removeall() throws InterruptedException {
		List<WebElement> c = Remove;
		int d = c.size();
		for (int i = d - 1; i > -1; i--) {
			Remove.get(i).click();
			logger.info("Item removed from Shopping Cart.");
			Thread.sleep(2000);
			logger.info("Thread Sleep Time out of 2 seconds.");
		}
		logger.info("All items removed from Shopping Cart.");
	}

	// Method to open item detail page from Shopping Cart
	public void SCItemclick() {
		List<WebElement> d = SCPAGEItems;
		d.get(0).click();
		logger.info("Clicked on selected item.");
	}
}
