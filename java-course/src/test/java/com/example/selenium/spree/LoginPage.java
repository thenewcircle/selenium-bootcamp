package com.example.selenium.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BasePage {
    @FindBy(id ="login-button")
    WebElement loginButton;

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

    private WebElement getUserInput() {
        return webDriver.findElement(By.xpath("//*[@id=\"userInput\"]"));
    }

    private WebElement getPasswordInput() {
        return webDriver.findElement(By.id("passwordInput"));
    }

    private boolean login(String username, String password) {
        getUserInput().sendKeys(username);
        loginButton.click();

        WebElement passwordInput = getPasswordInput();
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(passwordInput));
        Assert.assertTrue(passwordInput.isDisplayed());
        Assert.assertTrue(passwordInput.isEnabled());

        passwordInput.sendKeys(password);
        loginButton.click();
        WebElement element = webDriver.findElement(By.cssSelector("#warning-msg > h3"));
        return !element.getText().equals("That login didn't work:");
    }
}
