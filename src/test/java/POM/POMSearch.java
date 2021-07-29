package POM;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.walmart.Base.Base;

public class POMSearch extends Base {
	@FindBy(xpath = "//input[@class='e1xoeh2i1 css-ymj5ct eesbt950']")
	WebElement SearchBox;
	@FindBy(xpath = "//button[@class='css-1pgwcoa e1xoeh2i2']")
	WebElement Search;
	@FindBy(id = "walmartblue_Layer_1")
	WebElement Homepage;
	@FindBy(xpath = "//button[@class='css-1v14l77 e1xoeh2i3']")
	WebElement Clear;
	@FindBy(xpath = "//ul[@class='css-rfy0o6 e125rdbf0']//li//span") // Search-box suggestion
	List<WebElement> Box;
	@FindBy(xpath = "//div[@data-automation='product']//p[@class='css-1p4va6y eudvd6x0']")
	List<WebElement> SRP;
	@FindBy(xpath = "//span[@class='css-ijjviy ed60zyg11']")
	List<WebElement> PageNo;
	@FindBy(xpath = "//a[contains(text(),'Next')]")
	WebElement Next;
	@FindBy(xpath = "//div[@data-automation='categories']//div[@class='css-1ik4laa eupzuf59']")
	WebElement Category;
	@FindBy(xpath = "//label[normalize-space()='Brand']")
	WebElement Brand;
	@FindBy(xpath = "//span[normalize-space()='Samsung']")
	WebElement Samsung;
	@FindBy(xpath = "//button[@data-automation='sort-by-dropdown']")
	WebElement Sorting;
	@FindBy(xpath = "//span[text()='Price: Low to High']")
	WebElement PriceAsc;
	@FindBy(xpath = "//img[@alt='Samsung Galaxy S10']")
	WebElement result;
	@FindBy(xpath = "//button[@id='accept-privacy-policies']") // pop-up while doing testing to accept cookies
	WebElement Cookiesclose;

	public POMSearch() {
		super();
		PageFactory.initElements(driver, this);
	}

	// Method to write in search-box or search-bar
	public void searchbox(String A) {
		SearchBox.sendKeys(A);
		logger.info("Send search content to search box.");
	}

	// Method to click on Search-box
	public void searchboxclk() {
		SearchBox.click();
		logger.info("Clicked on Search box.");
	}

	// Method to click on Search button
	public void search() {
		Search.click();
		logger.info("Clicked on search button");
	}

	// Method to click on desired text in suggestion list of Search-box
	public void Keywordsuggestion() {
		for (WebElement elemet : Box) {
			if (elemet.getText().equalsIgnoreCase("charger in Electronics")) {
				elemet.click();
				break;
			}
			logger.info("Clicked on desired suggestion in search suggestion");
		}
	}
	// Method to click on desired text in suggestion list of Search-box
	public void discriptionsuggestion() {
		for (WebElement eleme : Box) {
			if (eleme.getText().equalsIgnoreCase("Samsung Charger S10 in Electronics")) {
				eleme.click();
				break;
			}
			logger.info("Clicked on desired suggestion in search suggestion");
		}
	}

	// Method to count product on Search Result Page
	public int count() {
		List<WebElement> cnt = SRP;
		int a = cnt.size();
		logger.info("Counting product on page");
		logger.info("Product count is " + a);
		return a;

	}

	// Method for sorting where price is ascending order
	public void sort() throws InterruptedException {
		Sorting.click();
		logger.info("Clicked on sorting button");
		Thread.sleep(2000);
		PriceAsc.click();
		logger.info("Clicked on Price:Low to High");
		Thread.sleep(2000);
	}

	// Method to Filter search result according to brand Samsung
	public void Filteration() throws InterruptedException {
		Category.click();
		logger.info("Clicked on Category");
		Brand.click();
		logger.info("Brand option Selected");
		down();
		down();
		Thread.sleep(2000);
		Samsung.click();
		logger.info("Samsung selected");
	}

	// Method to click on Home-page button
	public void HP() {
		Homepage.click();
		logger.info("Clicked on Element to navigate to HomePage");
	}

	// Method to clear search bar
	public void clear() {
		Clear.click();
		logger.info("Cleared the text written in search-box");
	}

	// Method to click on one of the result on search page
	public void results() {
		result.click();
		logger.info("Clicked on specific result");
	}

	public void next() {
		Next.click();
		logger.info("Clicked on next button");
	}

	// Method for checking duplication
	public boolean pagination() throws InterruptedException {
		// Condition to click on pop-up box of cookies
		if (Cookiesclose.isDisplayed()) {
			Cookiesclose.click();
			logger.info("Clicked on cookies pop-up");
		}
		scrolldown();
		scrolldown();
		List<String> P1 = new ArrayList<String>(); // will store all web element as string from page 1
		for (WebElement element1 : SRP) {
			P1.add(element1.getText());
		}
		logger.info("All elements of Page-1 are added to list P1");
		logger.info("Count of Page1 list is : " + P1.size());
		Set<String> uniquevaluesP1 = new HashSet<String>();
		for (String A1 : P1) {
			if (uniquevaluesP1.add(A1) == false) {
				logger.warn("Duplicate in Page-1 : " + A1);
			}
		}
		int UVS1 = uniquevaluesP1.size();
		int RS1 = P1.size();
		int Repetation1 = RS1 - UVS1;
		if (UVS1 < RS1) {
			logger.info("Total Number of object repeated in page 1 are " + Repetation1);
		}
		logger.info("Page 1 list added to P1 and checked for duplication");
		next();
		scrolldown();
		scrolldown();
		List<String> P2 = new ArrayList<String>(); // will store all web element as string from page 2
		for (WebElement element2 : SRP) {
			P2.add(element2.getText());
		}
		logger.info("All elements of Page-2 are added to list P2.");
		logger.info("Count of Page2 list is " + P2.size());
		Set<String> uniquevaluesP2 = new HashSet<String>();
		for (String A2 : P2) {
			if (uniquevaluesP2.add(A2) == false) {
				logger.warn("Duplicate in Page-2 : " + A2);
			}
		}
		int UVS2 = uniquevaluesP2.size();
		int RS2 = P2.size();
		int Repetation2 = RS2 - UVS2;
		if (UVS2 < RS2) {
			logger.info("Total number of objects repeated in page 2 are " + Repetation2);
		}
		logger.info("Page 2 list added to P2 and checked for duplication");
		next();
		scrolldown();
		scrolldown();
		List<String> P3 = new ArrayList<String>();// will store all web element as string from page 3
		for (WebElement element3 : SRP) {
			P3.add(element3.getText());
		}
		logger.info("All elements of Page-3 are added to list P3.");
		logger.info("Count of Page3 list is " + P3.size());
		Set<String> uniquevaluesP3 = new HashSet<String>();
		for (String A3 : P3) {
			if (uniquevaluesP3.add(A3) == false) {
				logger.warn("Duplicate in Page-3 : " + A3);
			}
		}
		int UVS3 = uniquevaluesP3.size();
		int RS3 = P3.size();
		int Repetation3 = RS3 - UVS3;
		if (UVS3 < RS3) {
			logger.info("Total number of objects repeated in page 3 are " + Repetation3);
		}
		logger.info("Page 3 list added to P3 and checked for duplication");
		next();
		scrolldown();
		scrolldown();
		List<String> P4 = new ArrayList<String>();
		for (WebElement element4 : SRP) {
			P4.add(element4.getText());
		}
		logger.info("All Elements of Page-4 are added to list P4.");
		logger.info("Count of list 4 is " + P4.size());
		Set<String> uniquevaluesP4 = new HashSet<String>();
		for (String A4 : P4) {
			if (uniquevaluesP4.add(A4) == false) {
				logger.warn("Duplicate in Page-4 : " + A4);

			}

		}
		int UVS4 = uniquevaluesP4.size();
		int RS4 = P4.size();
		int Repetation4 = RS4 - UVS4;
		if (UVS4 < RS4) {
			logger.info("Total number of object repeated in page 4 are " + Repetation4);
		}
		logger.info("Page 1 list added to P1 and checked for duplication");
		next();
		if (Repetation1 > 1) {
			logger.error("There are duplicates in Page1.");
			for (int i = 0; i < RS1; i++) {
				if (P1.contains(P2.get(i))) {
					logger.error("This element is duplicate in Page 1 and Page 2 :" + P2.get(i));
				} else {
					System.out.println("Checking next item in list 1 and 2 ");
				}
			}
			if (Repetation2 > 1) {
				logger.error("There are duplicates in Page2");
				for (int ii = 0; ii < RS2; ii++) {
					if (P2.contains(P3.get(ii))) {
						logger.error("This element is duplicate in Page 2 and Page 3 : " + P3.get(ii));
					} else {
						System.out.println("Checking next item in list 2 and 3 ");
					}
				}

				if (Repetation3 > 1) {
					logger.error("There are duplicates in Page3");
					for (int iii = 0; iii < RS3; iii++) {
						if (P3.contains(P4.get(iii))) {
							logger.error("This element is duplicate in Page 3 and Page 4: " + P3.get(iii));
						} else {
							System.out.println("Checking next item in list 3 and 4 ");
						}
					}

					if (Repetation4 > 1) {
						logger.error("There are duplicates in Page4");

					}
				}
			}

			return false;
		}

		else {
			return true;
		}

	}

}
