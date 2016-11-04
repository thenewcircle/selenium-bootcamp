package com.prosperworks.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalAddMenu extends ProsperWorksPage{

    public GlobalAddMenu(RemoteWebDriver webDriver) {
        super(webDriver);

        new WebDriverWait(webDriver, PAGE_READY_DELAY).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".WebAppHeader_actionBar i.md-person.OptionListItem_icon"))
        );
    }

    public AddPersonDlg clickNewPerson() {
        webDriver.findElementByCssSelector("i.md-person.OptionListItem_icon").click();
        return new AddPersonDlg(webDriver);
    }
}
