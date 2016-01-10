package Assignment;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty",
	"html:target/test-report",
	"json:target/test-report.json",
	"junit:target/test-report.xml"},
	features="src/main/java/Assignment")
public class AmazonSearchTest {
}
