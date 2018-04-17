package com.example.selenium.spree;

import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PageObjectsTests extends BaseTests {
    Pages pages;

    public PageObjectsTests(DriverType driverType) {
        super(driverType);
        pages = new Pages();
    }

    @Factory()
    public static Object[] testFactory() {
        List<PageObjectsTests> data = new ArrayList<>();
        data.add(new PageObjectsTests(BaseTests.DriverType.Firefox));
        data.add(new PageObjectsTests(BaseTests.DriverType.Chrome));
        return data.toArray();
    }

    @Test
    public void testBadLogin() {
        HomePage homePage = pages.openHomePage(webDriver);
        LoginPage loginPage = homePage.navigateToLoginPage();

        Assert.assertNull(loginPage.signIn("admin@cisco.com", "bad-password"));
    }

    @Test
    public void testLoginPage() {
        LoginPage loginPage = pages.openLoginPage(webDriver);
        Assert.assertNotNull(loginPage);
    }
}
