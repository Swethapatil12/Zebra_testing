package ZebraPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class ReportGenerator {

    private WebDriver dr;
    private String SCREENSHOT_FOLDER = "C:/Users/I19-labuser149440/eclipse-workspace/ZebraCucumber/ScreeshotFolder/";
    private String REPORT_FOLDER = "C:/Users/I19-labuser149440/eclipse-workspace/ZebraCucumber/ReportFolder/";

    private StringBuilder reportContent = new StringBuilder();
    public ReportGenerator(WebDriver dr) {
        this.dr = dr; 
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_FOLDER));
            Files.createDirectories(Paths.get(REPORT_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        reportContent.append("<html><head><title>Test Report</title></head><body>");
        reportContent.append("<h1>Test Execution Report</h1>");
        reportContent.append("<h3>Test Executed on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</h3>");
        reportContent.append("<table border='1'><tr><th>Test Step</th><th>Status</th><th>Screenshot</th></tr>");
    }

    public String captureScreenshot(String screenshotName) {
        try {
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            String timestamp = sdf.format(new Date());

            String screenshotPath = SCREENSHOT_FOLDER + screenshotName + "_" + timestamp + ".png";
            File screenshotDir = new File(SCREENSHOT_FOLDER);

            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            File screenshot = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(screenshotPath));
            return screenshotPath; 
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void addReportEntry(String testStep, boolean isSuccess, String screenshotPath) {
        String status = isSuccess ? "Pass" : "Fail";
        reportContent.append("<tr><td>").append(testStep).append("</td><td>").append(status)
                     .append("</td><td><img src='").append(screenshotPath)
                     .append("' width='200'></td></tr>");
    }

    public void generateReport() {
        reportContent.append("</table></body></html>");
        try {
         
            Files.write(Paths.get(REPORT_FOLDER + "TestReport.html"), reportContent.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void finalizeReport() {
        reportContent.append("</table></body></html>");
        String reportFileName = "TestReport_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".html";
        String reportFilePath = REPORT_FOLDER + reportFileName;
        try (FileWriter writer = new FileWriter(reportFilePath)) {
            writer.write(reportContent.toString());
            System.out.println("HTML Report generated at: " + reportFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
