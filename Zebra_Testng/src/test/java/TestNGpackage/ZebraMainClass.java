package TestNGpackage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class ZebraMainClass {
	WebDriver driver;
    @FindBy(xpath = "//button[text()=Accept Cookies']")
    WebElement acceptCookiesButton;

    public ZebraMainClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptCookies() {
        acceptCookiesButton.click();
    }
}