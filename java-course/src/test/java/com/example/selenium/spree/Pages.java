package com.example.selenium.spree;

import com.example.selenium.spree.pages.CartPage;
import com.example.selenium.spree.pages.HomePage;
import com.example.selenium.spree.pages.ProductPage;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Pages {
    public static HomePage openHomePage(RemoteWebDriver webDriver) {
        webDriver.get(HomePage.URL);
        return new HomePage(webDriver);
    }

    public static CartPage openCartPage(RemoteWebDriver webDriver) {
        webDriver.get(CartPage.URL);
        return new CartPage(webDriver);
    }

    public static ProductPage openProductPage(RemoteWebDriver webDriver) {
        webDriver.get(ProductPage.URL);
        return new ProductPage(webDriver);
    }
}
