package com.example.selenium.spree;

        import org.openqa.selenium.ie.InternetExplorerDriver;
        import org.openqa.selenium.remote.RemoteWebDriver;

public class IeDriverFactory extends WebDriverFactory {
    @Override
    public RemoteWebDriver create() {
        System.setProperty("webdriver.ie.driver", System.getenv("webdriver.ie.driver"));
        return new InternetExplorerDriver();
    }
}
