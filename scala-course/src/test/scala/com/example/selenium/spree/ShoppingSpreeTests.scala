package com.example.selenium.spree

import org.scalatest._

class ShoppingSpreeTests extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll {

  override def beforeEach: Unit = {
  }

  test("testA") {
    assert(true)
  }

  test("testB") {
    assert(1 === 1)
  }

  override def afterEach {
  }
}