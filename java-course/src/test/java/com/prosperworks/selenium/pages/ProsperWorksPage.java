package com.prosperworks.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProsperWorksPage extends AbstractPage {

    public ProsperWorksPage(RemoteWebDriver webDriver) {
        super(webDriver);
    }

    public GlobalAddMenu clickGlobalAdd() {
        WebElement addBtn = webDriver.findElementByCssSelector(".Dropdown-globalAddNew > i");
        addBtn.click();

        return new GlobalAddMenu(webDriver);
    }

    public HamburgerMenu clickHamburgerMenu() {
        webDriver.findElementByCssSelector(".NavLogo").click();
        return new HamburgerMenu(webDriver);
    }
}
