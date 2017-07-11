from com.example.selenium.spree.SpreePage import SpreePage, BASE_URL

CART_PAGE_URL = BASE_URL + "cart"

class CartPage(SpreePage):
    def __init__(self, shoppingSpreeTests):
        SpreePage.__init__(self, shoppingSpreeTests)

    def validateTitle(self):
        self._validateTitle("Shopping Cart - Spree Demo Site")

    def validateUrl(self):
        self._validateUrl(CART_PAGE_URL)
