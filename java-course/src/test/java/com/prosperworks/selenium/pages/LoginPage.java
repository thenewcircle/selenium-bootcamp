package com.prosperworks.selenium.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends ProsperWorksPage {

    public LoginPage(RemoteWebDriver webDriver) {
        super(webDriver);
    }

    public Dashboard login(String username, String password) {
        webDriver.findElementById("user_email").sendKeys(username);
        webDriver.findElementById("user_password").sendKeys(password);
        webDriver.findElementById("user_password").submit();

        return new Dashboard(webDriver);
    }
}
