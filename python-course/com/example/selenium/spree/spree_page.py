# from com.example.selenium.spree.home_page import HomePage
# from com.example.selenium.spree.products_page import ProductsPage
import time

class url_to_be(object):

    def __init__(self, expected):
        self.expected = expected

    def __call__(self, webDriver):
        actual = webDriver.current_url
        return self.expected == actual

class url_contains(object):
    def __init__(self, expected):
        self.expected = expected

    def __call__(self, webDriver):
        actual = webDriver.current_url
        return self.expected in actual

class SpreePage:
    # from com.example.selenium.spree.products_page import ProductsPage
    
    def __init__(self, test, waitUntil):
        self.test = test

        from selenium.webdriver.support.wait import WebDriverWait
        wdw = WebDriverWait(self.test.webDriver, 3)
        wdw.until(waitUntil)

    def getDepartmentCmb(self):
        return self.test.webDriver.find_element_by_id("taxon")

    def is_chrome(self):
        return "chrome" == self.test.webDriver.capabilities["browserName"]

    def is_firefox(self):
        return "firefox" == self.test.webDriver.capabilities["browserName"]

    def is_ie(self):
        return "internet explorer" == self.test.webDriver.capabilities["browserName"]

    def search(self, text):
        searchTF = self.test.webDriver.find_element_by_id("keywords")
        searchTF.send_keys(text)
        searchTF.submit()

        from com.example.selenium.spree.products_page import ProductsPage
        return ProductsPage(self.test, text)

    def clickLogo(self):
        logo = self.test.webDriver.find_element_by_id("logo")
        logo.click()
        
        from com.example.selenium.spree.home_page import HomePage
        return HomePage(self.test)
        
    def validateSearchText(self, expected):
        searchTF = self.test.webDriver.find_element_by_id("keywords")
        actual = searchTF.get_attribute("value")
        self.test.assertEquals(expected, actual)
        
    def clearSearch(self):
        searchTF = self.test.webDriver.find_element_by_id("keywords")
        searchTF.clear()

    def validateCartLink(self, count, price):
        if (count == 0):
            expected = "Cart: (Empty)"
        else:
            expected = "Cart: (" + str(count) + ") $" + price
        
        cartLnk = self.test.webDriver.find_element_by_partial_link_text("Cart:")
        actual = cartLnk.text
        self.test.assertEquals(expected, actual)

    def clickShoppingCart(self):
        cartLnk = self.test.webDriver.find_element_by_partial_link_text("Cart:")
        cartLnk.click()

        from com.example.selenium.spree.cart_page import CartPage
        return CartPage(self.test)