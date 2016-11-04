package com.prosperworks.selenium;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Test
public class AbstractTests {

    enum DriverType { Chrome, Firefox, IE, Safari, Edge, Grid }
    DriverType driverType;

    String testName;

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    RemoteWebDriver webDriver;

    public AbstractTests(DriverType driverType) {
        this.driverType = driverType;
    }

    @BeforeMethod
    public void beforeMethod(Method aMethod) throws Exception {

        if (DriverType.Grid == driverType) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setBrowserName("firefox");

            URL remoteAddress = new URL("http://192.168.101.7:4444/wd/hub");
            webDriver = new RemoteWebDriver(remoteAddress,capabilities);

        } else if (DriverType.Safari == driverType) {
            webDriver = new SafariDriver();

        } else if (DriverType.Firefox == driverType) {
            String path = System.getenv("webdriver_firefox_marionette");
            System.setProperty("webdriver.firefox.marionette", path);
//            FirefoxBinary binary = new FirefoxBinary(new File(""));
//            FirefoxProfile profile = new FirefoxProfile(new File(""));
            webDriver = new FirefoxDriver(/*binary, profile*/);

        } else if (DriverType.Chrome == driverType) {
            String path = System.getenv("webdriver_chrome_driver");
            System.setProperty("webdriver.chrome.driver", path);
            webDriver = new ChromeDriver();

        } else if (DriverType.IE == driverType) {
            String path = System.getenv("webdriver_ie_driver");
            System.setProperty("webdriver.ie.driver", path);
            webDriver = new InternetExplorerDriver();

        } else {
            throw new UnsupportedOperationException(driverType.name());
        }

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testName = aMethod.getName() + " [" + driverType + " on " + webDriver.getCapabilities().getPlatform().name() + "]";
    }
}
