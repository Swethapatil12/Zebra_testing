package TestNGpackage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;
    private WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report.html");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Extent Report Example");
        htmlReporter.config().setReportName("Test Execution Report");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        driver = (WebDriver) result.getTestContext().getAttribute("driver");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
        String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());
        String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String captureScreenshot(String methodName) {
        try {
            String timestamp = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path screenshotDir = Paths.get("screenshotsss");
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }
            String screenshotPath = "screenshotsss/" + methodName + "_" + timestamp + ".png";
            File destFile = new File(screenshotPath);
            Files.copy(screenshotFile.toPath(), destFile.toPath());
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
