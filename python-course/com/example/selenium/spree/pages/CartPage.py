from com.example.selenium.spree.pages.BasePage import BasePage

class CartPage(BasePage):
    title = "Shopping Cart - Spree Demo Site"
    url = "https://spreecommerce-demo.herokuapp.com/cart"

    def __init__(self, test):
        BasePage.__init__(self, test, self.title, self.url)
        self.test = test
