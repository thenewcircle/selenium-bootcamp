import time


class SpreePage:
    def __init__(self, shoppingSpreeTests):
        self.shoppingSpreeTests = shoppingSpreeTests
        self.webDriver = shoppingSpreeTests.webDriver

    def _validateTitle(self, expectedTitle):
        title = self.webDriver.title
        self.shoppingSpreeTests.assertEquals(expectedTitle, title)

    def _validateUrl(self, expectedURL):
        url = self.webDriver.current_url
        self.shoppingSpreeTests.assertEquals(expectedURL, url)

    def search(self, keywords):
        searchTF = self.webDriver.find_element_by_id("keywords")
        searchTF.send_keys(keywords)
        searchTF.submit()

        time.sleep(2)

        from com.example.selenium.spree.ProductsPage import ProductsPage
        return ProductsPage(self.shoppingSpreeTests)

    def validateSearchText(self, expected):
        searchTF = self.webDriver.find_element_by_id("keywords")
        actual = searchTF.get_attribute("value")
        self.shoppingSpreeTests.assertEquals(expected, actual)

    def clearSearch(self):
        searchTF = self.webDriver.find_element_by_id("keywords")
        searchTF.clear()

    def clickLogo(self):
        logo = self.webDriver.find_element_by_id("logo")
        logo.click()

        from selenium.webdriver.support.wait import WebDriverWait
        from com.example.selenium.spree.HomePage import BASE_URL
        WebDriverWait(self.webDriver, 30).until(
            url_to_be(BASE_URL)
        )

        from com.example.selenium.spree.HomePage import HomePage
        return HomePage(self.shoppingSpreeTests)


class url_to_be(object):
    def __init__(self, expected):
        self.expected = expected

    def __call__(self, webDriver):
        return self.expected == webDriver.current_url


class url_contains(object):
    def __init__(self, expected):
        self.expected = expected

    def __call__(self, webDriver):
        return self.expected in webDriver.current_url
