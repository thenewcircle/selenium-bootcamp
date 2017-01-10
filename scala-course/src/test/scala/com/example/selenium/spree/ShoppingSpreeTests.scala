package com.example.selenium.spree

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.scalatest._

class ShoppingSpreeTests extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll {

  var webDriver: RemoteWebDriver = null

  override def beforeEach: Unit = {
    // System.setProperty("webdriver.gecko.driver", "c:\\tools\\selenium\\geckodriver.exe")
    // webDriver = new FirefoxDriver()

    System.setProperty("webdriver.chrome.driver", "c:\\tools\\selenium\\chromedriver.exe")
    webDriver = new ChromeDriver()
  }

  test("Home Page") {
    webDriver.get("https://spreecommerce-demo.herokuapp.com")
    val title = webDriver.getTitle()
    assert(title === "Spree Demo Site")
  }

  test("testA") {
    assert(true)
  }

  test("testB") {
    assert(1 === 1)
  }

  override def afterEach: Unit = {
    webDriver.quit()
  }
}