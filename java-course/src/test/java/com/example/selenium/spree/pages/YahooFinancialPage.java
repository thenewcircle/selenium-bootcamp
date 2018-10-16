package com.example.selenium.spree.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class YahooFinancialPage extends BasePage {
    public static final String URL = "https://finance.yahoo.com/quote/AAPL/financials?p=AAPL";

    public static final String TITLE = "Spree Demo Site";

    public YahooFinancialPage(RemoteWebDriver webDriver) {
        super(webDriver, TITLE, URL);
    }

    public WebElement getTable() {
        return webDriver.findElementByXPath("//*[@id='Col1-1-Financials-Proxy']/section/div[3]/table");
    }
}