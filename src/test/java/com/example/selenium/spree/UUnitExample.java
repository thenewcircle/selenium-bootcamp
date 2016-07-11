package com.example.selenium.spree;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UUnitExample {

  @BeforeClass
  public static void beforeEverything() {
    System.out.printf("Before Everthing: %s\n", System.currentTimeMillis());
  }

  @Before
  public void beforeSomething() {
    System.out.printf("Before: %s\n", System.currentTimeMillis());
  }

  @Test
  public void asdf() {
    System.out.printf("Test A: %s\n", System.currentTimeMillis());
  }

  @Test
  public void fdsa() {
    System.out.printf("Test B: %s\n", System.currentTimeMillis());
  }

  @Test
  public void fsfdsdf() {
    System.out.printf("Test C: %s\n", System.currentTimeMillis());
  }
  
  @After
  public void fsdf() {
    System.out.printf("After: %s\n\n", System.currentTimeMillis());
  }
  
  @AfterClass
  public static void afterEverthing() {
    System.out.printf("After Everything: %s\n\n", System.currentTimeMillis());
  }
}
