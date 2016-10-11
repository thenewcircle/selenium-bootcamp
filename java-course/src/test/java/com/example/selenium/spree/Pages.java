package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Pages {

    public static HomePage openHomePage(RemoteWebDriver webDriver) {
        webDriver.get("https://spreecommerce-demo.herokuapp.com");
        return new HomePage(webDriver);
    }

    public static CartPage openCartPage(RemoteWebDriver webDriver) {
        webDriver.get("https://spreecommerce-demo.herokuapp.com/cart");
        return new CartPage(webDriver);
    }

    public static ProductPage openProductPage(RemoteWebDriver webDriver, String productName) {
        String url = ProductPage.getUrl(productName);
        webDriver.get(url);
        return new ProductPage(webDriver, productName);
    }
}
