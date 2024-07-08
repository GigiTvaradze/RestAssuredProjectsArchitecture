package InProgress.Section14CucumberBDD.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/InProgress/Section14CucumberBDD/FeatureFiles",
                glue = {"stepDefinition"},
                plugin = {"pretty", "html:target/cucumber-reports"},
                monochrome = true)
public class TestRunner {}
