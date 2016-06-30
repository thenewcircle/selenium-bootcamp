package com.example.selenium.spree.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report.html" })
public class RunCucumberTest {

}
