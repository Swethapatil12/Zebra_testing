package TestNGpackage;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class ReportHandler {
    private WebDriver dr;
    private StringBuilder reportContent = new StringBuilder();
    private static final String SCREENSHOT_FOLDER = "C:/Users/I19-labuser149440/eclipse-workspace/TestNGzebra/screenshot/";
    private static final String REPORT_FOLDER = "C:/Users/I19-labuser149440/eclipse-workspace/TestNGzebra/report/";
    private static final String FILE_PATH = "C:/Users/I19-labuser149440/eclipse-workspace/TestNGzebra/zebraTesting.txt";

    public ReportHandler(WebDriver dr) {
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


    public void writeToFile(String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            writer.write(content);
            writer.newLine();
            System.out.println("Successfully wrote to the file: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Failed to close the writer: " + e.getMessage());
            }
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
