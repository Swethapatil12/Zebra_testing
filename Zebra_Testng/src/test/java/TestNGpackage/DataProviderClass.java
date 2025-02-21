package TestNGpackage;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "partnerData")
    public Object[][] partnerData() {
        return new Object[][] {
            { "pooja", "patil", "ITC", "pooja@example.com" }  // First Name, Last Name, Company, Email
        };
    }

    @DataProvider(name = "searchData")
    public Object[][] searchData() {
        return new Object[][] {
            { "abc" },  
        };
    }
    @DataProvider(name = "searchQuery")
    public Object[][] searchQuery() {
        return new Object[][] {
           
            { "wave" } 
        };
    }
}
