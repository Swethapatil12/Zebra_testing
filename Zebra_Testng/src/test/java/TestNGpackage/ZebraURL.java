package TestNGpackage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


@Listeners(TestListener.class)
public class ZebraURL {
    WebDriver dr;
    WebDriverWait wait;
    private ReportHandler reportHandler;

    @BeforeClass
    public void setUp(ITestContext context) {
        dr = new ChromeDriver();
        dr.manage().window().maximize();
        dr.get("https://www.zebra.com/ap/en.html");
        reportHandler = new ReportHandler(dr);
        wait = new WebDriverWait(dr, Duration.ofSeconds(20));
        context.setAttribute("driver", dr);
        
    }
    @Test(priority = 1)
    public void closePopupAndTakeHomepageScreenshot(){
        String screenshotPath = "";
        boolean isSuccess = true;
        try {
            dr.findElement(By.xpath("//button[@aria-label='Close']")).click();
            screenshotPath = reportHandler.captureScreenshot("Homepage");

            dr.findElement(By.xpath("//span[text()='Industry']")).click();
            screenshotPath = reportHandler.captureScreenshot("IndustryPage");
            reportHandler.writeToFile("Industry section submenu's are:");
            List<WebElement> il = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("(//div[@class='navigation-item__scroll-section']/div)[position() <= 7]")));
            System.out.println("Industry section submenu's are:");
            for (WebElement i : il) {
                String elementText = i.getText();
                System.out.println(elementText);
                reportHandler.writeToFile(elementText);
            }

            WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Mobile Technology for Energy and Utilities']")));
            ((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
            elementToClick.click();
            screenshotPath = reportHandler.captureScreenshot("MobileTechPage");
            System.out.println();
            reportHandler.writeToFile("\n");
            reportHandler.writeToFile("Mobile tech sections available options:");
            List<WebElement> ill = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//p[contains(text(), 'Empower')]//following-sibling::div[contains(@class, 'navigation-item__menu-options-container')]/a/div/div")));
            System.out.println("Mobile tech sections available options:");
            for (WebElement in : ill) {
                String elementText = in.getText();
                System.out.println(elementText);
                reportHandler.writeToFile(elementText);
            }

        } catch (Exception e) {
            isSuccess = false;
            String screenshotPathFail = reportHandler.captureScreenshot("closePopupAndTakeHomepageScreenshot_Fail");
            reportHandler.addReportEntry("Executed closePopupAndTakeHomepageScreenshot", isSuccess, screenshotPathFail);
            Assert.fail("Test failed during closePopupAndTakeHomepageScreenshot: " + e.getMessage());
        } finally {
            reportHandler.addReportEntry("Executed closePopupAndTakeHomepageScreenshot", isSuccess, screenshotPath);
        }
    }

    @Test(priority = 2)
    public void navigateToDifferentSections(){
        String screenshotPath = "";
        boolean isSuccess = true;
        try {
            dr.findElement(By.xpath("//div[text()='Digital Safety Inspection and Compliance Reporting']")).click();
            screenshotPath = reportHandler.captureScreenshot("digitalSafetyPage");

            dr.findElement(By.xpath("//a[@href='#Hardware']")).click();
            screenshotPath = reportHandler.captureScreenshot("HardwareSection");

            dr.findElement(By.xpath("//a[@href='#Software']")).click();
            screenshotPath = reportHandler.captureScreenshot("SoftwareSection");

            dr.findElement(By.xpath("//a[@href='#Services']")).click();
            screenshotPath = reportHandler.captureScreenshot("ServicesSection");

            dr.findElement(By.xpath("//a[@href='#Supplies']")).click();
            screenshotPath = reportHandler.captureScreenshot("SuplliesSection");

            String originalWindow = dr.getWindowHandle();
      
            WebElement element = dr.findElement(By.xpath("(//i[@class='icon-arrow icon-arrow--white'])[3]"));
            element.click();
    
            for (String windowHandle : dr.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    dr.switchTo().window(windowHandle);
                    break;
                }
            }
            screenshotPath = reportHandler.captureScreenshot("FindAPartnerPage");

            dr.findElement(By.xpath("//button[@class='rounded-lightblue' and text()='PARTNER APPS AND OFFERINGS']")).click();
            screenshotPath = reportHandler.captureScreenshot("PartnerOfferingsPage");

        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            reportHandler.addReportEntry("Executed navigateToDifferentSections", isSuccess, screenshotPath);
        }
    }

        @Test(priority = 3, dataProvider = "searchData", dataProviderClass = DataProviderClass.class)
        public void searchPartnerByABC(String searchTerm){
            String screenshotPath = "";
            boolean isSuccess = true;
            try {
              
                WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search by name or keyword']")));
                searchInput.sendKeys(searchTerm);
                screenshotPath = reportHandler.captureScreenshot("SearchABC");

                WebElement searchButton = dr.findElement(By.xpath("//*[@id=\"partner_type\"]/div[2]/form[1]/div/div[2]/button/span"));
                searchButton.click();

                screenshotPath = reportHandler.captureScreenshot("SearchOptions");

                WebElement partnerResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='partner_search_listing']/div[3]/div[2]/div[3]/zbr-search-list-item/div[1]/div[3]/div[2]/a/button")));
                partnerResult.click();
                WebElement backToSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/ap/en/partners/partner-application-locator/offering-search-results.html']")));
                backToSearch.click();

                screenshotPath = reportHandler.captureScreenshot("BackToSearch");

                WebElement crossIndustry = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[@class='custom-control-label' and @for='crossIndustry_0'])[2]")));
                crossIndustry.click();

                screenshotPath = reportHandler.captureScreenshot("SelectCrossIndustry");

                WebElement osOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[@class='custom-control-label' and @for='suppOS_3'])[2]")));
                osOption.click();

                screenshotPath = reportHandler.captureScreenshot("SelectOS");

                WebElement filteredResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"partner_search_listing\"]/div[3]/div[2]/div[2]/zbr-search-list-item/div[1]/div[3]/div[2]/a/button")));
                filteredResult.click();

                screenshotPath = reportHandler.captureScreenshot("FilteredResult");

            } catch (Exception e) {
                isSuccess = false;
                e.printStackTrace();
                reportHandler.addReportEntry("Executed searchPartnerByABC", isSuccess, screenshotPath);
                Assert.fail("Test failed during searchPartnerByABC: " + e.getMessage());
            } finally {
                if (isSuccess) {
                    reportHandler.addReportEntry("Executed searchPartnerByABC", true, screenshotPath);
                }
            }
        }

        @Test(priority = 4, dataProvider = "searchQuery", dataProviderClass = DataProviderClass.class)
        public void searchPartnerByWave(String searchTerms){
            String screenshotPath = "";
            boolean isSuccess = true;
            try {
                WebElement backToResultsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='BACK TO RESULTS']")));
                backToResultsButton.click();

                screenshotPath = reportHandler.captureScreenshot("BackToSearch");

                if (searchTerms.equals("wave")) {
                    WebElement waveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"partner_type\"]/div[2]/div[1]/div[3]/a/zbr-button/button")));
                    waveButton.click();

                    WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search by name']")));
                    searchInput.sendKeys(searchTerms);
                    screenshotPath = reportHandler.captureScreenshot("SearchWave");

                    WebElement searchButton = dr.findElement(By.xpath("//*[@id=\"partner_search_listing\"]/div[3]/div[1]/zbr-search-name/div[1]/form/div/div[2]/button"));
                    searchButton.click();

                    WebElement partnerResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"partner_search_listing\"]/div[3]/div[2]/div[1]/zbr-search-list-item/div[1]/div[3]/div[2]/a/button")));
                    partnerResult.click();

                    screenshotPath = reportHandler.captureScreenshot("ContactPartner");
                }
            } catch (Exception e) {
                isSuccess = false;
                e.printStackTrace();
                reportHandler.addReportEntry("Executed searchPartnerByWave", isSuccess, screenshotPath);
                Assert.fail("Test failed during searchPartnerByWave: " + e.getMessage());
            } finally {
                if (isSuccess) {
                    reportHandler.addReportEntry("Executed searchPartnerByWave", true, screenshotPath);
                }
            }
        }

        @Test(priority = 5, dataProvider = "partnerData", dataProviderClass = DataProviderClass.class)
        public void fillPartnerContactForm(String firstName, String lastName, String company, String email){
            String screenshotPath = "";
            boolean isSuccess = true;
            try {
            	WebElement contactPartnerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Contact Partner']")));
            	contactPartnerButton.click();

                screenshotPath = reportHandler.captureScreenshot("DetailsFillingSection");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='$FirstName']")));
                firstNameField.sendKeys(firstName);
                dr.findElement(By.xpath("//input[@name='$LastName']")).sendKeys(lastName);

                dr.findElement(By.xpath("//input[@name='$Company']")).sendKeys(company);
             
                screenshotPath = reportHandler.captureScreenshot("basicDetails");

                dr.findElement(By.xpath("(//input[@name='$EmailAddress'])[2]")).sendKeys(email);
               
                dr.findElement(By.xpath("//input[@placeholder='Please select all options that apply']")).click();
                dr.findElement(By.xpath("(//span[text()='Software'])[2]")).click();
              
                screenshotPath = reportHandler.captureScreenshot("QueryDomain");
                WebElement d = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='$UserNeeds']")));
                ((JavascriptExecutor) dr).executeScript("arguments[0].click();", d);
                d.sendKeys("info");

                dr.findElement(By.xpath("(//label[@class='gcdc-form-label'])[15]")).click();

                WebElement b = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='gcdc-form-button'])[2]")));
                b.click();
              
                screenshotPath = reportHandler.captureScreenshot("Terms&Conditions");

                WebElement bu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@role='button'])[3]")));
                bu.click();
                screenshotPath = reportHandler.captureScreenshot("SubmittedForm");

                WebElement img = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='cmp-image__image'])[1]")));
                img.click();
                screenshotPath = reportHandler.captureScreenshot("BacktoHomePage");

            } catch (Exception e) {
                isSuccess = false;
                e.printStackTrace();
                reportHandler.addReportEntry("Executed fillPartnerContactForm", isSuccess, screenshotPath);
                Assert.fail("Test failed during fillPartnerContactForm: " + e.getMessage());
            } finally {
                if (isSuccess) {
                    reportHandler.addReportEntry("Executed fillPartnerContactForm", true, screenshotPath);
                }
            }
        }

        @AfterClass
        void close() {
            reportHandler.finalizeReport();
            if (dr != null) {
                dr.quit();
            }
        }
    }
