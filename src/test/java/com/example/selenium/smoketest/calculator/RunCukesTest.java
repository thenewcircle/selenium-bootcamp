package com.example.selenium.smoketest.calculator;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Runnable driver class for running Cucumber feature tests.
 * 
 * @author @techjedi
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report.html" })
public class RunCukesTest {

}
