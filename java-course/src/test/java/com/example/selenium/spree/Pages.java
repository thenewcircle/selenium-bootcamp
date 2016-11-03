package com.example.selenium.spree;

        import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by jacobp on 11/2/2016.
 */
public class Pages {
    public static HomePage openHomePage(RemoteWebDriver webDriver) {
        webDriver.get("https://spreecommerce-demo.herokuapp.com");
        return new HomePage(webDriver);
    }

    public static CartPage openCartPage(RemoteWebDriver webDriver) {
        webDriver.navigate().to("https://spreecommerce-demo.herokuapp.com/cart");
        return new CartPage(webDriver);
    }

    public static ProductPage openProductPage(RemoteWebDriver webDriver, String prodName) {
        String url = ProductPage.getUrlFromName(prodName);
        webDriver.get(url);
        return new ProductPage(webDriver, prodName);
    }
}
