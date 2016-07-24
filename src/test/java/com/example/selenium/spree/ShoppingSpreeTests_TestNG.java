package com.example.selenium.spree;

/*
import org.testng.Assert;
import org.testng.annotations.*;
*/
import com.example.selenium.spree.support.LogbackUtils;

import ch.qos.logback.classic.Level;

public class ShoppingSpreeTests_TestNG {

  static {
    LogbackUtils.initLogback(Level.WARN);
  }
  
/*
  @BeforeClass
  public void beforeClass() throws Exception {
    System.out.println("Before class");
  }
  
  @BeforeMethod
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

  @AfterMethod
  public void afterMethod() {
    System.out.println("After method\n");
  }

  @AfterClass
  public void afterClass() {
    System.out.println("After class");
  }
*/
}
