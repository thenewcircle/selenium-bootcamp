from com.example.selenium.spree.HomePage import BASE_URL
from com.example.selenium.spree.SpreePage import SpreePage

PRODUCTS_PAGE_URL = BASE_URL + "search/"


class ProductsPage(SpreePage):
    def __init__(self, shoppingSpreeTests):
        SpreePage.__init__(self, shoppingSpreeTests)

    def validateTitle(self):
        self._validateTitle("Spree Demo Site")

    def validateUrl(self, searchTerm):
        self._validateUrl(PRODUCTS_PAGE_URL + searchTerm.lower())
