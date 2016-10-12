class ProductPage:
    
    def __init__(self, test):
        self.test = test

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Tote - Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/products/spree-tote", url)