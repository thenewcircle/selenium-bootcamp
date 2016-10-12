from com.example.selenium.spree.SpreePage import SpreePage


class ProductPage(SpreePage):
    
    def __init__(self, test):
        # self.test = test
        super().__init__(test)
        
    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Tote - Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/products/spree-tote", url)