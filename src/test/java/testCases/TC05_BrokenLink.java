package testCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.WebElement;
import java.util.List;
import PageObjects.HomePage;
import testBase.BaseClass;

public class TC05_BrokenLink extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void validateBrokenLinks() {
		logger.info("Test Case: Validate Broken Links started");

		HomePage homePage = new HomePage(driver);

		logger.info("Fetching all links and images from the homepage");
		List<WebElement> allLinksAndImages = homePage.getAllLinksAndImages();
		logger.info("Total links including images found: " + allLinksAndImages.size());

		logger.info("Filtering active links");
		List<WebElement> activeLinks = homePage.getActiveLinks(allLinksAndImages);
		logger.info("Total active links found: " + activeLinks.size());

		int brokenLinksCount = 0;

		for (WebElement link : activeLinks) {
			try {
				String url = link.getAttribute("href");
				logger.debug("Checking URL: " + url);
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.connect();

				int statusCode = connection.getResponseCode();
				if (statusCode >= 400) {
					logger.error("Broken link ---> [Status code: ] URL: " + statusCode);
					brokenLinksCount++;
				} else {
					logger.info("Valid link ---> [Status code: ] URL: " + statusCode + " " + url);
				}
			} catch (Exception e) {
				logger.warn("Error checking link: {}. Exception: ");
			}
		}

		logger.info("Number of broken links: " + brokenLinksCount);

		logger.info("Test Case: Validate Broken Links completed");
	}
}