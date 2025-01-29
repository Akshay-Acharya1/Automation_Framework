package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.searchPage;
import ReadConfiguration.Readconfiguration;
import testBase.BaseClass;

public class TC03_SearchTest extends BaseClass {

	Readconfiguration RC = new Readconfiguration();

	@Test(dataProvider = "searchData",groups = {"Regression","Sanity"})
	public void testSearchFunctionality(String keyword, boolean isValidProduct) {

		logger.info("Starting test Search Functionality with product -->" + keyword);
		searchPage SP = new searchPage(driver);

		logger.info("Entering search keyword:-->" + keyword);

		SP.enterSearchKeyword(keyword);

		logger.info("Clicking on the search button.");
		
		SP.clickSearchButton();

		if (isValidProduct) {
			logger.info("Verifying that the product is displayed for keyword: " + keyword);
			Assert.assertTrue(SP.isProductDisplayed(), "Expected product not found in search results.");
			logger.info("Product Found: {}" + SP.getFirstProductName());

		} else {
			logger.info("Verifying that no product is displayed for invalid keyword: " + keyword);

			Assert.assertFalse(SP.isProductDisplayed(), "Unexpected product found for invalid search.");
			logger.info("No result message: {}" + SP.getNoResultMessage());
		}
		
		logger.info("Test completed for product:" + keyword);

	}

	@DataProvider(name = "searchData")
	public Object[][] getSearchData() {
		return new Object[][] { { "Samsung", true }, { "iphone", true }, { "Macbook", true } };
	}
}
