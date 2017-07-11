# from com.example.selenium.spree.HomePage import HomePage
# from com.example.selenium.spree.ProductsPage import ProductsPage

BASE_URL = "https://selenium.jacobparr.com/"


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
