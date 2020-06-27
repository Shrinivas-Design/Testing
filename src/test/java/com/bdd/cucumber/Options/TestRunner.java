package com.bdd.cucumber.Options;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/bdd/FeatureFiles",plugin="json:target/jsonReports/cucumber-report.json",glue= {"com.bdd.stepDefination"})

public class TestRunner {
 	//, tags= {"@DeletePlace"}
}
