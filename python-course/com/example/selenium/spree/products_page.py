from com.example.selenium.spree.spree_page import SpreePage, url_contains


class ProductsPage(SpreePage):
    def __init__(self, test):
        super().__init__(test)

        from selenium.webdriver.support.wait import WebDriverWait
        WebDriverWait(self.test.webDriver, 30).until(
            url_contains("https://spreecommerce-demo.herokuapp.com/products")
        )

    def validateTitle(self):
        title = self.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        msg = "Found " + url
        self.test.assertTrue(url.startswith("https://spreecommerce-demo.herokuapp.com/products"), msg)

    def clickProductLnk(self, product):
        link = self.webDriver.find_element_by_link_text(product)
        link.click()

        from com.example.selenium.spree.product_page import ProductPage
        return ProductPage(self.test, product)
    
    
    
    
    