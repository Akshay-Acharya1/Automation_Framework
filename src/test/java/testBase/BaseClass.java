package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import ReadConfiguration.Readconfiguration;

public class BaseClass {

	public static Logger logger;
	public static WebDriver driver;
	Properties prop;
	Properties pro;

	Readconfiguration readconfig = new Readconfiguration();

	public String BaseURL = readconfig.getApllicationURL(); // Applicat URL
	public String UserEmail = readconfig.userEmail();
	public String password = readconfig.Password();

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "browser" })
	public void setup(String br) throws IOException {

		// Initialize logger and configure log4j properties
		logger = Logger.getLogger("14 Sep Automation Framework");
		PropertyConfigurator.configure("./src/test/resources/Configuration/log4j.properties");
		logger.info("Logger initialized");

		// Browser setup
		logger.info("Setting up the browser: " + br);
		switch (br.toLowerCase()) {
		case "chrome":
			logger.info("Launching Chrome browser");
			driver = new ChromeDriver();
			break;
		case "firefox":
			logger.info("Launching Firefox browser");
			driver = new FirefoxDriver();
			break;
		case "edge":
			logger.info("Launching Edge browser");
			driver = new EdgeDriver();
			break;
		default:
			logger.error("Invalid browser name: " + br);
			throw new IllegalArgumentException("Invalid browser name");
		}

		logger.info("Browser launched successfully");

		// Browser settings
		logger.info("Maximizing browser window");
		driver.manage().window().maximize();

		logger.info("Setting implicit wait to 20 seconds");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Launch the application
		logger.info("Navigating to application URL: " + BaseURL);
		driver.get(BaseURL);
		logger.info("Application opened successfully");
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void teardown() {
		// Close browser
		if (driver != null) {
			logger.info("Closing the browser");
			driver.quit();
			logger.info("Browser closed successfully");
		} else {
			logger.warn("Driver is already null. Skipping browser teardown");
		}
	}
	
	public static String getScreenshotAs(String TestCasename, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String Timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String Name = "- Screenshot -" + Timestamp;

		Path resourceDirectory = Paths.get("src", "test", "resources");

		String AbsolutePath = resourceDirectory.toFile().getAbsolutePath();

		String Destination = AbsolutePath + "/Screenshots/" + Name + ".png";

		FileUtils.copyFile(source, new File(Destination));

		return Destination;
	}

}