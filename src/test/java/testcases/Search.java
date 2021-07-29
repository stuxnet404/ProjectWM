package testcases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.walmart.Base.Base;

import POM.POMLogin;
import POM.POMSearch;

public class Search extends Base {
	POMLogin PM;
	POMSearch PS;
	int expectedcount = 60;

	SoftAssert asrt;

	public Search() {
		super();
	}

	

	@BeforeMethod // Will open browser before every test run
	public void open() {
		initate();
		PM = new POMLogin();
		PS = new POMSearch();
		asrt = new SoftAssert();
		logger.info("Starting Test of ShoppingCart test class.");
	}

	@Test(priority = 1)
	public void TC81KeywordSearching() throws IOException {
		PS.searchbox(PO.getProperty("Keyword"));
		PS.Keywordsuggestion();
	}

	@Test(priority = 2)
	public void TC82DiscriptionSearching() throws IOException {
		PS.searchbox(PO.getProperty("discription"));
		PS.search();
		PS.clear();
		PS.searchbox(PO.getProperty("discription"));
		PS.discriptionsuggestion();
	}

	@Test(priority = 3)
	public void TC83Partial() throws InterruptedException {
		PS.searchbox(PO.getProperty("partial"));
		Thread.sleep(3000);
		logger.info("Thread Sleep Time out of 3 seconds.");
	}

	@Test(priority = 4)
	public void TC84ItemNUmber() {
		PS.searchbox(PO.getProperty("itemnumber"));
		PS.search();
	}

	@Test(priority = 5)
	public void TC85Partialnumber() {
		PS.searchbox(PO.getProperty("partialnumber"));
		PS.search();
	}

	@Test(priority = 6)
	public void TC86Productcount() throws InterruptedException {
		PS.searchbox(PO.getProperty("Keyword"));
		PS.Keywordsuggestion();
		scrolldown();
		scrolldown();
		int actual = PS.count();
		asrt.assertEquals(actual, expectedcount);
		logger.info("Comparing actual result and expected result.");
		asrt.assertAll();
		logger.info("Results compared.");
	}

	@Test(priority = 7)
	public void TC87Pagination() throws InterruptedException {
		PS.searchbox(PO.getProperty("Keyword"));
		PS.search();
		asrt.assertEquals(PS.pagination(), true);
		logger.info("Comapring actual result and expected result.");
		asrt.assertAll();
		logger.info("Results compared.");
	}

	@Test(priority = 8)
	public void TC88Filtration() throws InterruptedException {
		PS.searchbox(PO.getProperty("Keyword"));
		PS.Keywordsuggestion();
		PS.Filteration();
	}

	@Test(priority = 9)
	public void TC89Sorting() throws InterruptedException {
		PS.searchbox(PO.getProperty("Keyword"));
		PS.Keywordsuggestion();
		PS.sort();
	}

	@AfterMethod
	public void close() throws InterruptedException {
		Teardown();
	}

	@AfterClass
	public void finish() {
		logger.info("Finished all test in Search test class.");
	}

}
