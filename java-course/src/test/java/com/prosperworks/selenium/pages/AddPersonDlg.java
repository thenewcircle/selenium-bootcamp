package com.prosperworks.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by jacobp on 11/3/2016.
 */
public class AddPersonDlg extends ModalPage {
    public AddPersonDlg(RemoteWebDriver webDriver) {
        super(webDriver);

        new WebDriverWait(webDriver, PAGE_READY_DELAY).until(
                ExpectedConditions.textToBe(By.cssSelector("div.Modal_header.u-bold"), "Add a New Person")
        );
    }

    public WebElement getFrame() {
        return webDriver.findElementByCssSelector(".Modal_frame");
    }

    public Dashboard clickCancel() {
//        webDriver.findElementByCssSelector("button[name='CANCEL']").click();
//        webDriver.findElementByCssSelector(".Modal_frame button").click();
//        return new Dashboard(webDriver);

        List<WebElement> elements = getFrame().findElements(By.tagName("button"));
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.equals("CANCEL")) {
                element.click();
                return new Dashboard(webDriver);
            }
        }
        throw new RuntimeException("I didn't find the cancel button");
    }
}
