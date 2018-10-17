import time

from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support.expected_conditions import url_to_be

from com.example.selenium.spree.pages.BasePage import BasePage
from com.example.selenium.spree.pages.HomePage import HomePage


class ProductsPage(BasePage):
    title = "Spree Demo Site"
    url = "https://spreecommerce-demo.herokuapp.com/products?utf8=%E2%9C%93&taxon=&keywords="

    def __init__(self, test, keywords):
        BasePage.__init__(self, test, self.title, self.url)
        self.test = test
        self.keywords = keywords

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEqual(self.url + self.keywords, url)

    def getSearchBox(self) -> WebElement:
        return self.test.webDriver.find_element_by_id("keywords")

    def getLogo(self) -> WebElement:
        return self.test.webDriver.find_element_by_id("logo")

    def validateSearchText(self, text):
        self.test.assertEqual(
            self.getSearchBox().get_attribute("value"), text)

    def clearSearch(self):
        searchBoxWebElement = self.getSearchBox()
        searchBoxWebElement.clear()

    def clickLogo(self) -> HomePage:
        self.getLogo().click()

        from selenium.webdriver.support.wait import WebDriverWait
        WebDriverWait(self.test.webDriver, 30).until(
            url_to_be(self.url)
        )

        return HomePage(self.test)
