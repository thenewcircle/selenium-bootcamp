package com.prosperworks.selenium.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {

    protected static final long PAGE_READY_DELAY = 5;

    RemoteWebDriver webDriver;

    public AbstractPage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

    }
}
