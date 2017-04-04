class CartPage:
    
    def __init__(self, test):
        self.test = test

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Shopping Cart - Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://selenium.jacobparr.com/cart", url)
