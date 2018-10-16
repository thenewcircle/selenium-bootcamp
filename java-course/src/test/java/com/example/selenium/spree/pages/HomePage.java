package com.example.selenium.spree.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage {
    public static final String URL = "https://spreecommerce-demo.herokuapp.com/";

    public static final String TITLE = "Spree Demo Site";

    public HomePage(RemoteWebDriver webDriver) {
        super(webDriver, TITLE, URL);
    }

    public WebElement getDepartmentCmb() {
        return webDriver.findElementById("taxon");
    }

    public WebElement getCartValue() {
        List<WebElement> webElements =
                webDriver.findElementsByClassName("cart-info");
        return webElements.get(0);
    }
}