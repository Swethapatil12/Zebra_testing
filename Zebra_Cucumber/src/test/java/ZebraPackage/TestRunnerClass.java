package ZebraPackage;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/zebra.feature", 
    glue = {"ZebraPackage"}, 
    plugin = {"pretty", "html:target/cucumber-reports.html"} 
)
public class TestRunnerClass extends AbstractTestNGCucumberTests {
}