package com.example.selenium.spree;

import org.testng.Assert;
import org.testng.annotations.*;
import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;

public class ShoppingSpreeTests {

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        // save for later
    }

    @Test
    public void testA() {
        Assert.assertTrue(true);
    }

    @Test
    public void testB() {
        Assert.assertEquals(1, 1);
    }

    @AfterMethod
    public void afterMethod() {
        // save for later
    }
}
