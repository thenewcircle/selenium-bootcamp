from com.example.selenium.spree.spreepage import SpreePage


class ProductsPage(SpreePage):
    
    def __init__(self, test):
        super().__init__(test)
        
        from selenium.webdriver.support.wait import WebDriverWait
        from com.example.selenium.spree.spreepage import url_contains
        WebDriverWait(self.test.webDriver, 5).until(
            url_contains("https://spreecommerce-demo.herokuapp.com/products")
        )

    def validateUrl(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)
    
    def validateTitle(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag", url)
    
    def clickProductLink(self, name):
        link = self.test.webDriver.find_element_by_link_text(name)
        link.click()
        
        from com.example.selenium.spree.productpage import ProductPage
        return ProductPage(self.test, name)
        
    
    
    