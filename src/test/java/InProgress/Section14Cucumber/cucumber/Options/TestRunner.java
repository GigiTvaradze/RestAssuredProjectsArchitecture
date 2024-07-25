package InProgress.Section14Cucumber.cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/InProgress/Section14Cucumber/features",plugin ="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"})
public class TestRunner {
//tags= {"@DeletePlace"}  compile test verify
}
