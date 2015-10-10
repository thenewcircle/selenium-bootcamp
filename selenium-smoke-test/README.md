# selenium-smoke-test
Smoke test for Selenium and Cucumber setup

## Requirements
* JDK (version 1.8)
* Firefox (version 39.0 works with Selenium 2.46.0)
 
## Run from Eclipse
* In `src/test/java` run `com.example.calculator.RunCukesTest` as a Java application
* In `src/test/java` run `com.example.shopping.RunSeleniumSmokeTest` as a Java application

## Run from command line
* `mvn test` - runs all tests in `src/test/java`
* `mvn -Dtest=RunSeleniumSmokeTest test` - runs only Selenium smoke test
* `mvn -Dtest=RunCukesTest test` - runs only Cucumber feature tests

