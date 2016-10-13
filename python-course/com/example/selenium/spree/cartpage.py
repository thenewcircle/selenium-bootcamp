from com.example.selenium.spree.spreepage import SpreePage


class CartPage(SpreePage):
    def __init__(self, test):
        # self.test = test
        super().__init__(test)

        from selenium.webdriver.support.wait import WebDriverWait
        from com.example.selenium.spree.spreepage import url_to_be
        WebDriverWait(self.test.webDriver, 5).until(
            url_to_be("https://spreecommerce-demo.herokuapp.com/cart")
        )

        title = self.test.webDriver.title
        self.test.assertEquals("Shopping Cart - Spree Demo Site", title)

    # def validateUrl(self):
    #     url = self.test.webDriver.current_url
    #     self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/cart", url)
    #
    # def validateTitle(self):
    #     title = self.test.webDriver.title
    #     self.test.assertEquals("Shopping Cart - Spree Demo Site", title)
