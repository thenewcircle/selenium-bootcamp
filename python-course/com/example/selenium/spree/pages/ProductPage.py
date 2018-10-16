from com.example.selenium.spree.pages.BasePage import BasePage


class ProductPage(BasePage):
    title = "Spree Tote - Spree Demo Site"
    url = "https://spreecommerce-demo.herokuapp.com/products/spree-tote"

    def __init__(self, test):
        BasePage.__init__(self, test, self.title, self.url)
        self.test = test
