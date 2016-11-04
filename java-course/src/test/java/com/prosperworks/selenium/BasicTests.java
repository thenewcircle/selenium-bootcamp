package com.prosperworks.selenium;

import com.prosperworks.selenium.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test
public class BasicTests extends AbstractTests {

    public BasicTests(DriverType driverType) {
        super(driverType);
    }

    public void login() {
        webDriver.get("https://app.prosperworks.com/users/sign_in");
        LoginPage loginPage = new LoginPage(webDriver);

        Dashboard dashboard = loginPage.login("me@jacobparr.com", "testing123");

        HamburgerMenu menu = dashboard.clickHamburgerMenu();
        CompaniesPage companiesPage = menu.clickCompanies();

        menu = companiesPage.clickHamburgerMenu();
        dashboard = menu.clickDashboard();

        GlobalAddMenu globalMenu = dashboard.clickGlobalAdd();

        AddPersonDlg addPersonDlg = globalMenu.clickNewPerson();
        addPersonDlg.clickCancel();

//        menu.clickNewCompany();
//        menu.clickNewOpportunity();

        System.out.print("");


    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @Factory()
    public static Object[] testFactory() {
        List<BasicTests> data = new ArrayList<>();
        // data.add(new BasicTests(DriverType.IE));
        // data.add(new BasicTests(DriverType.Safari));
        // data.add(new BasicTests(DriverType.Firefox));
         data.add(new BasicTests(DriverType.Chrome));
//        data.add(new BasicTests(DriverType.Grid));
        return data.toArray();
    }
}
