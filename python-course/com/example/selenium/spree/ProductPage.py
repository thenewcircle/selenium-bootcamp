from com.example.selenium.spree.SpreePage import SpreePage, BASE_URL

PRODUCT_PAGE_URL = BASE_URL + "products/spree-tote"

class ProductPage(SpreePage):
    def __init__(self, shoppingSpreeTests):
        SpreePage.__init__(self, shoppingSpreeTests)

    def validateTitle(self):
        self._validateTitle("Spree Tote - Spree Demo Site")

    def validateUrl(self):
        self._validateUrl(PRODUCT_PAGE_URL)
