from com.example.selenium.spree.SpreePage import SpreePage


class ProductsPage(SpreePage):

    def __init__(self, test):
        super().__init__(test)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag", url)
    
    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)