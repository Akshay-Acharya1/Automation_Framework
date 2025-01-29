package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.BaseClass;

public class Listeners extends BaseClass implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReportTestNG.getReportObject();

    // Using ThreadLocal to manage test instances in parallel execution
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        // Log when the test starts
        System.out.println("Starting Test: " + result.getMethod().getMethodName());
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // 1
        extentTest.get().log(Status.INFO, "Test " + result.getMethod().getMethodName() + " has started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log when the test passes
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log when the test fails
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.FAIL, "Failure Reason: " + result.getThrowable());

        WebDriver driver = BaseClass.driver; // Directly access the driver

        if (driver != null) {
            try {
                // Capture screenshot and add it to the report
                String screenshotPath = Genericutils.getScreenshotAs(result.getMethod().getMethodName(), driver);
                extentTest.get().log(Status.INFO, "Attempting to attach a screenshot...");
                extentTest.get().addScreenCaptureFromPath(screenshotPath, "Screenshot of failure");
                extentTest.get().log(Status.INFO, "Screenshot attached successfully.");
            } catch (IOException e) {
                // Log if screenshot capture fails
                extentTest.get().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
                System.err.println("Error capturing screenshot: " + e.getMessage());
            }
        } else {
            // Log if WebDriver is null
            extentTest.get().log(Status.WARNING, "WebDriver instance is null, skipping screenshot capture.");
            System.err.println("WebDriver instance is null, unable to capture screenshot.");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Log when the test suite finishes
        System.out.println("Test Suite Execution Finished: " + context.getName());
        extentTest.get().log(Status.INFO, "Test Suite Execution Finished: " + context.getName());
        extent.flush();
    }
}
