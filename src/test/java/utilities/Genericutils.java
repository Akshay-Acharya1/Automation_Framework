package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;

public class Genericutils extends BaseClass {

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("./screenshots/" + tname + "_" + timeStamp + ".png");

		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

	public static WebElement WaitforElement(WebDriver driver, WebElement Locator, int timeout) {

		WebDriverWait Mywait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		Mywait.until(ExpectedConditions.presenceOfElementLocated((By) Locator));

		return driver.findElement((By) Locator);
	}

	public void waitForVisibility(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Wait for an element to be clickable
	public void waitForClickability(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Generate a random string
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		logger.info("Generated random string: " + generatedString);
		return generatedString;
	}

	// Generate a random number
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		logger.info("Generated random number: " + generatedNumber);
		return generatedNumber;
	}

	// Generate a random alphanumeric string
	public String randomAplaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		String result = generatedString + generatedNumber;
		logger.info("Generated random alphanumeric string: " + result);
		return result;
	}

}
