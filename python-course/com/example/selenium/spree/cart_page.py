from com.example.selenium.spree.spree_page import SpreePage, url_to_be


class CartPage(SpreePage):
    def __init__(self, test):
        super().__init__(test)

        from selenium.webdriver.support.wait import WebDriverWait
        WebDriverWait(self.test.webDriver, 30).until(
            url_to_be("https://spreecommerce-demo.herokuapp.com/cart")
        )

    def validateTitle(self):
        title = self.webDriver.title
        self.test.assertEquals("Shopping Cart - Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/cart", url)

    def clickContinueShopping(self):
        link = self.webDriver.find_element_by_link_text("Continue shopping")
        link.click()

        from com.example.selenium.spree.products_page import ProductsPage
        return ProductsPage(self.test)