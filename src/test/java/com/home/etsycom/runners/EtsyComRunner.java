package com.home.etsycom.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.home.etsycom.stepsdef",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }
)
public class EtsyComRunner {
}
