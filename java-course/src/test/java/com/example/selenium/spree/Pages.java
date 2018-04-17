package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Pages {
    public HomePage openHomePage(RemoteWebDriver webDriver) {
        return HomePage.navigateTo(webDriver, true);
    }

    public LoginPage openLoginPage(RemoteWebDriver webDriver) {
        return new LoginPage(webDriver, true);
    }
}
