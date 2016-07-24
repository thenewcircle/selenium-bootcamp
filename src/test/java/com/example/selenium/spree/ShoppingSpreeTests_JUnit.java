package com.example.selenium.spree;

/*
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
*/

import com.example.selenium.spree.support.LogbackUtils;

import ch.qos.logback.classic.Level;

public class ShoppingSpreeTests_JUnit {

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

/*
    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Before class");
    }

    @Before
    public void beforeMethod() throws Exception {
        System.out.println("Before method");
    }

    @Test
    public void testA() {
        System.out.println("Test A");
        Assert.assertEquals(1, 2);
    }

    @Test
    public void testB() {
        System.out.println("Test B");
        Assert.assertEquals(1, 1);
    }

    @After
    public void afterMethod() {
        System.out.println("After method\n");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After class");
    }
*/
}
