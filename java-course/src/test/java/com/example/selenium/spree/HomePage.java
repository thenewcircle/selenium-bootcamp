package com.example.selenium.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends BasePage {
    public HomePage(RemoteWebDriver webDriver, boolean navigateTo) {
        super(webDriver,
                "https://www.cisco.com/",
                "Cisco - Global Home Page",
                navigateTo);
    }

    public static HomePage navigateTo(RemoteWebDriver webDriver, boolean navigateTo) {
        HomePage homePage = new HomePage(webDriver, navigateTo);
        return homePage;
    }

    public LoginPage navigateToLoginPage() {
        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"actions\"]/li[1]/a"));
        loginButton.click();

        LoginPage loginPage = new LoginPage(webDriver, false);
        loginPage.assertOnPage();

        return loginPage;
    }
}
