package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Pages {
    public static HomePage openHomePage(RemoteWebDriver webDriver) {
        webDriver.get("https://spreecommerce-demo.herokuapp.com");
        return new HomePage(webDriver);
    }
}
