package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.walmart.Base.Base;

import POM.POMSearch;
import POM.POMShoppingCart;

public class ShoppingCart extends Base {

	POMSearch PS;
	POMShoppingCart PSC;

	public ShoppingCart() {
		super();
	}
    


	@BeforeMethod
	public void open() {
		initate();
		PS = new POMSearch();
		PSC = new POMShoppingCart();
		logger.info("Starting Test of ShoppingCart test class.");

	}

	@Test
	public void TC91_ADD_One_item_in_SC() throws InterruptedException {
		PS.searchbox(PO.getProperty("discription"));
		PS.search();
		scrolldown();
		PS.results();
		Thread.sleep(2000);
		logger.info("Thread Sleep Time out of 2 seconds.");
		tabchange();
		Thread.sleep(5000);
		logger.info("Thread Sleep Time out of 5 seconds.");
		scrolldown();
		PSC.ATCS();

	}

	@Test
	public void TC92_Increase_Same_Item_Quantity() throws InterruptedException {
		PS.searchbox(PO.getProperty("discription"));
		PS.search();
		down();
		Thread.sleep(2000);
		logger.info("Thread Sleep Time out of 2 seconds.");
		PSC.ATCS();
		Thread.sleep(2000);
		logger.info("Thread Sleep Time out of 2 seconds.");
		PSC.Add();
	}

	@Test
	public void TC93_ADD_Multiple_Item_in_SC() throws InterruptedException {
		PS.searchbox(PO.getProperty("discription"));
		PS.search();
		down();
		PSC.ATC2();
	}

	@Test
	public void TC94_Remove_one_Item_From_SC() throws InterruptedException {
		PS.searchbox(PO.getProperty("discription"));
		PS.search();
		down();
		PSC.ATC2();
		Thread.sleep(2000);
		logger.info("Thread Sleep Time out of 2 seconds.");
		PSC.Remove();
	}

	@Test
	public void TC95_Remove_Multiple_Item_From_SC() throws InterruptedException {
		PS.searchbox(PO.getProperty("discription"));
		PS.search();
		down();
		PSC.ATC2();
		Thread.sleep(2000);
		logger.info("Thread Sleep Time out of 2 seconds.");
		PSC.Removeall();
	}

	@Test
	public void TC96_Click_Item_In_SC() throws InterruptedException {
		PS.searchbox(PO.getProperty("discription"));
		PS.search();
		down();
		PSC.ATC2();
		Thread.sleep(2000);
		logger.info("Thread Sleep Time out of 2 seconds.");
		PSC.SCItemclick();
	}

	@AfterMethod
	public void close() throws InterruptedException {
		Teardown();
	}

	@AfterClass
	public void finish() {
		logger.info("Finished all test in ShoppingCart test class.");
	}
}
