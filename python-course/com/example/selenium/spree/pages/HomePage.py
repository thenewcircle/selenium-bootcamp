import time

from selenium.webdriver.remote.webelement import WebElement

from com.example.selenium.spree.pages.BasePage import BasePage
from com.example.selenium.spree.pages.ProductPage import ProductPage
from com.example.selenium.spree.pages.ProductsPage import ProductsPage


class HomePage(BasePage):
    title = "Spree Demo Site"
    url = "https://spreecommerce-demo.herokuapp.com/"

    def __init__(self, test):
        BasePage.__init__(self, test, self.title, self.url)
        self.test = test

    def getDepartmentCmb(self):
        return self.test.webDriver.find_element_by_id("taxon")

    def getSearchBox(self) -> WebElement:
        return self.test.webDriver.find_element_by_id("keywords")

    def search(self, text):
        self.getSearchBox().send_keys(text)
        self.getSearchBox().submit()

        time.sleep(2)
        return ProductsPage(self.test, text)
