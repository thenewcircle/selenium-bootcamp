package com.example.selenium.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public LoginPage(RemoteWebDriver webDriver, boolean navigateTo) {
        super(webDriver,
                "https://cloudsso.cisco.com/sp/startSSO.ping?SpSessionAuthnAdapterId=standardnomfa&TargetResource=https://sso.cisco.com/autho/login/loginaction.html",
                "Cisco.com Login Page",
                navigateTo);
    }

    @Override
    protected void assertOnPage() {
        WebElement innerTitle = webDriver.findElement(By.xpath("//*[@id=\"fw-title-nav-login\"]/h1"));
        Assert.assertEquals(innerTitle.getText(), "Log in to your account");
    }

    public NewsFeedPage signIn(String username, String password) {
        if (!login(username, password)) {
            return null;
        }
        return new NewsFeedPage();
    }

    public void navigateTo() {
        webDriver.navigate().to(URL);
        Assert.assertEquals(webDriver.getTitle(), "Cisco.com Login Page");
    }

    private WebElement getLoginButton() {
        return webDriver.findElement(By.id("login-button"));
    }

    private WebElement getUserInput() {
        return webDriver.findElement(By.xpath("//*[@id=\"userInput\"]"));
    }

    private WebElement getPasswordInput() {
        return webDriver.findElement(By.id("passwordInput"));
    }

    private boolean login(String username, String password) {
        getUserInput().sendKeys(username);
        getLoginButton().click();

        WebElement passwordInput = getPasswordInput();
        if (!passwordInput.isDisplayed()) {
            return false;
        }

        passwordInput.sendKeys(password);
        getLoginButton().click();
        WebElement element = webDriver.findElement(By.cssSelector("#warning-msg > h3"));
        return !element.getText().equals("That login didn't work:");
    }
}
