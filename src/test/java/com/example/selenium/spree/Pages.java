package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Pages {

    public static HomePage openHomePage(RemoteWebDriver webDriver) {
        webDriver.get("http://spree.newcircle.com");
        return new HomePage(webDriver);
    }

    public static CartPage openCartPage(RemoteWebDriver webDriver) {
        webDriver.navigate().to("http://spree.newcircle.com/cart");
        return new CartPage(webDriver);
    }
}
