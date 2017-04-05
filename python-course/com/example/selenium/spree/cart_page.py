from com.example.selenium.spree.spree_page import SpreePage, url_to_be


class CartPage(SpreePage):
    
    def __init__(self, test):
        super().__init__(test, url_to_be("https://selenium.jacobparr.com/cart"))

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Shopping Cart - Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://selenium.jacobparr.com/cart", url)

    def clickContinueShopping(self):
        cont = self.test.webDriver.find_element_by_link_text("Continue shopping")
        cont.click()

        from com.example.selenium.spree.products_page import ProductsPage
        return ProductsPage(self.test, "all")
    