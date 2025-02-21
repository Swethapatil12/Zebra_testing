package ZebraPackage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class MainClass {
   
    static WebDriver driver;
    static WebDriverWait wait;
    static ReportGenerator reportGenerator;  
    String FILE_PATH = "C:/Users/I19-labuser149440/eclipse-workspace/ZebraCucumber/cucumber.txt";

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.zebra.com/ap/en.html");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        reportGenerator = new ReportGenerator(driver);
    }
    
    @Given("I create a ChromeDriver object")
    public void createChromeDriverObject() {
        System.out.println("WebDriver is already initialized in @Before");
    }
    @When("I access the specified URL using Chrome")
    public void accessSpecifiedURL() {
        try {
            driver.get("https://www.zebra.com/ap/en.html");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Close']"))).click();
            String screenshotPath = reportGenerator.captureScreenshot("accessURL"); // Capture screenshot
            reportGenerator.addReportEntry("Accessed the specified URL", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("accessURL_Error");
            reportGenerator.addReportEntry("Failed to access the URL", false, screenshotPath);
        }
    }

    @Then("I click on the Industry menu")
    public void clickOnIndustryMenu() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Industry']"))).click();
            String screenshotPath = reportGenerator.captureScreenshot("clickIndustryMenu");
            reportGenerator.addReportEntry("Clicked on Industry menu", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("clickIndustryMenu_Error");
            reportGenerator.addReportEntry("Failed to click on Industry menu", false, screenshotPath);
        }
    }

    @Then("I capture all menu options under the Industry section and store them in a text file")
    public void captureIndustryMenuOptions() {
        try {
        	 writeToFile("Industry's submenu are:");
        	  List<WebElement> il = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                      By.xpath("(//div[@class='navigation-item__scroll-section']/div)[position() <= 7]")));
              for (WebElement i : il) {
                  String elementText = i.getText();
                  System.out.println(elementText);
                  writeToFile(elementText);
              }

            String screenshotPath = reportGenerator.captureScreenshot("captureIndustryMenuOptions");
            reportGenerator.addReportEntry("Captured Industry menu options", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("captureIndustryMenuOptions_Error");
            reportGenerator.addReportEntry("Failed to capture Industry menu options", false, screenshotPath);
        }
    }

    @And("I click on Mobile Technology for Energy and Utilities menu")
    public void clickOnMobileTechMenu() {
        try {
            WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Mobile Technology for Energy and Utilities']"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
            elementToClick.click();
            String screenshotPath = reportGenerator.captureScreenshot("clickMobileTechMenu");
            reportGenerator.addReportEntry("Clicked on Mobile Technology for Energy and Utilities menu", true, screenshotPath);
            writeToFile("\nMobile tech sections available options:");
            List<WebElement> ill = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//p[contains(text(), 'Empower')]//following-sibling::div[contains(@class, 'navigation-item__menu-options-container')]/a/div/div")));
            System.out.println("Mobile tech sections available options:");
            for (WebElement in : ill) {
                String elementText = in.getText();
                System.out.println(elementText);
                writeToFile(elementText);
            }
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("clickMobileTechMenu_Error");
            reportGenerator.addReportEntry("Failed to click on Mobile Technology menu", false, screenshotPath);
        }
    }


    //2
    @When("I click on Digital Safety Inspection and Compliance Reporting menu")
    public void clickOnDigitalSafetyInspection() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Digital Safety Inspection and Compliance Reporting']")
            ));
            element.click();
            
            String screenshotPath = reportGenerator.captureScreenshot("clickDigitalSafetyInspection");
            reportGenerator.addReportEntry("Clicked on Digital Safety Inspection and Compliance Reporting menu", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("clickDigitalSafetyInspection_Error");
            reportGenerator.addReportEntry("Failed to click on Digital Safety Inspection and Compliance Reporting menu", false, screenshotPath);
        }
    }

    @When("I navigate through the Hardware section")
    public void navigateThroughHardwareSection() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Hardware']"))).click();
            String screenshotPath = reportGenerator.captureScreenshot("navigateHardwareSection");
            reportGenerator.addReportEntry("Navigated through the Hardware section", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("navigateHardwareSection_Error");
            reportGenerator.addReportEntry("Failed to navigate through the Hardware section", false, screenshotPath);
        }
    }

    @And("I navigate through the Software section")
    public void navigateThroughSoftwareSection() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Software']"))).click();
            String screenshotPath = reportGenerator.captureScreenshot("navigateSoftwareSection");
            reportGenerator.addReportEntry("Navigated through the Software section", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("navigateSoftwareSection_Error");
            reportGenerator.addReportEntry("Failed to navigate through the Software section", false, screenshotPath);
        }
    }

    @And("I navigate through the Service and Maintenance section")
    public void navigateThroughServiceMaintenanceSection() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Services']"))).click();
            String screenshotPath = reportGenerator.captureScreenshot("navigateServiceMaintenanceSection");
            reportGenerator.addReportEntry("Navigated through the Service and Maintenance section", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("navigateServiceMaintenanceSection_Error");
            reportGenerator.addReportEntry("Failed to navigate through the Service and Maintenance section", false, screenshotPath);
        }
    }

    @And("I navigate through the Supplies section")
    public void navigateThroughSuppliesSection() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Supplies']"))).click();
            String screenshotPath = reportGenerator.captureScreenshot("navigateSuppliesSection");
            reportGenerator.addReportEntry("Navigated through the Supplies section", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("navigateSuppliesSection_Error");
            reportGenerator.addReportEntry("Failed to navigate through the Supplies section", false, screenshotPath);
        }
    }

    @Then("I click on the Find Partner section")
    public void launchFindPartnerURL() {
        try {
        	 String originalWindow = driver.getWindowHandle();
             
             WebElement element = driver.findElement(By.xpath("(//i[@class='icon-arrow icon-arrow--white'])[3]"));
             element.click();
     
             for (String windowHandle : driver.getWindowHandles()) {
                 if (!windowHandle.equals(originalWindow)) {
                     driver.switchTo().window(windowHandle);
                     break;
                 }
             }
            String screenshotPath = reportGenerator.captureScreenshot("launchFindPartnerURL");
            reportGenerator.addReportEntry("Launched Find Partner URL", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("launchFindPartnerURL_Error");
            reportGenerator.addReportEntry("Failed to launch Find Partner URL", false, screenshotPath);
        }
    }

    @And("I click on Partner Apps and Offerings section")
    public void clickOnPartnerApps() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='rounded-lightblue' and text()='PARTNER APPS AND OFFERINGS']"))
            ).click();
            
            String screenshotPath = reportGenerator.captureScreenshot("clickPartnerApps");
            reportGenerator.addReportEntry("Clicked on Partner Apps and Offerings section", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("clickPartnerApps_Error");
            reportGenerator.addReportEntry("Failed to click on Partner Apps and Offerings section", false, screenshotPath);
        }
    }
    //3

    @When("In the search bar, I type {string} and click search")
    public void searchPartnerByABC(String searchTerm) {
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search by name or keyword']")));
            searchInput.sendKeys(searchTerm);
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"partner_type\"]/div[2]/form[1]/div/div[2]/button/span"));
            searchButton.click();
            
            String screenshotPath = reportGenerator.captureScreenshot("searchPartnerByABC");
            reportGenerator.addReportEntry("Searched for partner: " + searchTerm, true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("searchPartnerByABC_Error");
            reportGenerator.addReportEntry("Failed to search for partner: " + searchTerm, false, screenshotPath);
        }
    }

    @And("I capture the search results")
    public void captureSearchResults() {
        try {
           
            String screenshotPath = reportGenerator.captureScreenshot("captureSearchResults");
            reportGenerator.addReportEntry("Captured search results", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("captureSearchResults_Error");
            reportGenerator.addReportEntry("Failed to capture search results", false, screenshotPath);
        }
    }

    @Then("I click on 'View Profile' link next to 'Abetech' in the search results")
    public void clickOnViewProfile() {
        try {
            WebElement partnerResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='partner_search_listing']/div[3]/div[2]/div[3]/zbr-search-list-item/div[1]/div[3]/div[2]/a/button")));
            partnerResult.click();
            String screenshotPath = reportGenerator.captureScreenshot("clickViewProfile");
            reportGenerator.addReportEntry("Clicked on 'View Profile' next to 'Abetech'", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("clickViewProfile_Error");
            reportGenerator.addReportEntry("Failed to click on 'View Profile' next to 'Abetech'", false, screenshotPath);
        }
    }

    @And("I click on 'Back to search results'")
    public void clickOnBackToSearchResults() {
        try {
            WebElement backToSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/ap/en/partners/partner-application-locator/offering-search-results.html']")));
            backToSearch.click();
            String screenshotPath = reportGenerator.captureScreenshot("clickBackToSearchResults");
            reportGenerator.addReportEntry("Clicked on 'Back to search results'", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("clickBackToSearchResults_Error");
            reportGenerator.addReportEntry("Failed to click on 'Back to search results'", false, screenshotPath);
        }
    }

    //4
    @When("I filter search by selecting 'RFID' at Cross Vertical and 'LINUX' at Supported OS")
    public void filterSearchByRFIDAndLinux() throws InterruptedException {
        try {
            WebElement crossIndustry = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[@class='custom-control-label' and @for='crossIndustry_0'])[2]")));
            crossIndustry.click();
            WebElement osOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[@class='custom-control-label' and @for='suppOS_3'])[2]")));
            osOption.click();

            String screenshotPath = reportGenerator.captureScreenshot("filterSearchByRFIDAndLinux");
            reportGenerator.addReportEntry("Filtered search by selecting 'RFID' at Cross Vertical and 'LINUX' at Supported OS", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("filterSearchByRFIDAndLinux_Error");
            reportGenerator.addReportEntry("Failed to filter search by RFID and LINUX", false, screenshotPath);
        }
    }

    @When("I click on 'Nedap Harmony' from filtered results and view the profile")
    public void clickFilteredResultAndViewProfile() throws InterruptedException {
        try {
            WebElement filteredResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"partner_search_listing\"]/div[3]/div[2]/div[2]/zbr-search-list-item/div[1]/div[3]/div[2]/a/button")));
            filteredResult.click();

            WebElement backToResultsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='BACK TO RESULTS']")));
            backToResultsButton.click();

            String screenshotPath = reportGenerator.captureScreenshot("clickFilteredResultAndViewProfile");
            reportGenerator.addReportEntry("Clicked on 'Nedap Harmony' and viewed the profile", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("clickFilteredResultAndViewProfile_Error");
            reportGenerator.addReportEntry("Failed to click on 'Nedap Harmony' from filtered results", false, screenshotPath);
        }
    }

    @Then("In the search bar, I type {string} and clicks search")
    public void searchPartnerByWave(String searchTerms) throws InterruptedException {
        try {
            WebElement waveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"partner_type\"]/div[2]/div[1]/div[3]/a/zbr-button/button")));
            waveButton.click();
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search by name']")));
            searchInput.sendKeys(searchTerms);
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"partner_search_listing\"]/div[3]/div[1]/zbr-search-name/div[1]/form/div/div[2]/button"));
            searchButton.click();
            String screenshotPath = reportGenerator.captureScreenshot("searchPartnerByWave");
            reportGenerator.addReportEntry("Searched for partner: " + searchTerms, true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("searchPartnerByWave_Error");
            reportGenerator.addReportEntry("Failed to search for partner: " + searchTerms, false, screenshotPath);
        }
    }

    @When("I search and click on Wave Warehouse Management System")
    public void searchAndClickOnPartner() throws InterruptedException {
        try {
            WebElement partnerResult = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='partner_search_listing']/div[3]/div[2]/div[1]/zbr-search-list-item/div[1]/div[3]/div[2]/a/button")));
            partnerResult.click();

            String screenshotPath = reportGenerator.captureScreenshot("searchAndClickOnPartner");
            reportGenerator.addReportEntry("Searched and clicked on Wave Warehouse Management System", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("searchAndClickOnPartner_Error");
            reportGenerator.addReportEntry("Failed to search and click on Wave Warehouse Management System", false, screenshotPath);
        }
    }

    @And("I fill in the inquiry form with {string}, {string}, {string}, and {string}")
    public void fillInquiryForm(String firstName, String lastName, String company, String email) throws InterruptedException {
        try {
        	WebElement contactPartnerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Contact Partner']")));
        	contactPartnerButton.click();
        	WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='$FirstName']")));
            firstNameField.sendKeys(firstName);

            driver.findElement(By.xpath("//input[@name='$LastName']")).sendKeys(lastName);
            driver.findElement(By.xpath("//input[@name='$Company']")).sendKeys(company);
            driver.findElement(By.xpath("(//input[@name='$EmailAddress'])[2]")).sendKeys(email);
            driver.findElement(By.xpath("//input[@placeholder='Please select all options that apply']")).click();
            driver.findElement(By.xpath("(//span[text()='Software'])[2]")).click();
            WebElement queryBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='$UserNeeds']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", queryBox);
            queryBox.sendKeys("info");
            driver.findElement(By.xpath("(//label[@class='gcdc-form-label'])[15]")).click();
            
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class='gcdc-form-button'])[2]")));
            submitButton.click();
            WebElement bu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@role='button'])[3]")));
            bu.click();

            String screenshotPath = reportGenerator.captureScreenshot("fillInquiryForm");
            reportGenerator.addReportEntry("Filled the inquiry form with provided details", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("fillInquiryForm_Error");
            reportGenerator.addReportEntry("Failed to fill the inquiry form", false, screenshotPath);
        }
    }

    @Then("I click on the Zebra logo image to return to the homepage")
    public void returnToHomepage() {
        try {
            WebElement homeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='cmp-image__image'])[1]")));
            homeButton.click();
            
            String screenshotPath = reportGenerator.captureScreenshot("returnToHomepage");
            reportGenerator.addReportEntry("Clicked on Zebra logo to return to homepage", true, screenshotPath);
        } catch (Exception e) {
            String screenshotPath = reportGenerator.captureScreenshot("returnToHomepage_Error");
            reportGenerator.addReportEntry("Failed to click on Zebra logo to return to homepage", false, screenshotPath);
        }
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

        @AfterAll
        public static void close() {
        	 reportGenerator.finalizeReport();
            if (driver != null) {
                driver.quit(); 
            }
        }

}


