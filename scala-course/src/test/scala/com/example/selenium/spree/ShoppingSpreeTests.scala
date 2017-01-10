package com.example.selenium.spree

import org.scalatest._

class ShoppingSpreeTests extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll {

  override def beforeAll: Unit = {
    println("Before class")
  }

  override def beforeEach: Unit = {
    println("Before method")
  }

  test("testA") {
    println("Test A")
    assert(1 === 2)
  }

  test("testB") {
    println("Test B")
    assert(1 === 1)
  }

  override def afterEach {
    println("After method")
  }

  override def afterAll {
    println("After class")
  }
}