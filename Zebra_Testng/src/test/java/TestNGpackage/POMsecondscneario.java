package TestNGpackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class POMsecondscneario {
	WebDriver driver;
	@BeforeClass
	public void openurlagain(ITestContext context) {
		
		driver = new ChromeDriver();
        driver.get("https://www.zebra.com/");
        System.out.println("WebDriver initialized and navigated to zebra.com");
        context.setAttribute("driver", driver);
	}
	
	@Test
	public void clickcookies() {
		
		ZebraMainClass zeb= new  ZebraMainClass(driver);
		 zeb.clickAcceptCookies();	
	}
	@AfterClass
	public void closeurlagain() {
		driver.quit();
	}
	
	
}

