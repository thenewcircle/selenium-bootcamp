from selenium.webdriver.remote.webelement import WebElement

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

    def __init__(self, test):
        self.test = test
        self.webDriver = test.webDriver

    def getDepartmentCmb(self) -> WebElement:
        return self.webDriver.find_element_by_id("taxon")

    def search(self, keywords):
        search_tf = self.webDriver.find_element_by_id("keywords")
        search_tf.send_keys(keywords)
        search_tf.submit()
        from com.example.selenium.spree.products_page import ProductsPage
        return ProductsPage(self.test)
    
    def validateSearchText(self, expected):
        search_tf = self.webDriver.find_element_by_id("keywords")
        
        actual = search_tf.get_attribute("value")
        self.test.assertEquals(expected, actual)
        
    def clearSearch(self):
        search_tf = self.webDriver.find_element_by_id("keywords")
        search_tf.clear()
        
    def clickHomeLink(self):
        link = self.webDriver.find_element_by_id("home-link")
        link.click()

        from com.example.selenium.spree.home_page import HomePage
        return HomePage(self.test)
    
    def clickLogo(self):
        logo = self.webDriver.find_element_by_id("logo")
        logo.click()

        from com.example.selenium.spree.home_page import HomePage
        return HomePage(self.test)
    
    def validateCartLink(self, quantity:int, amount:str):
        cart_lnk = self.webDriver.find_element_by_partial_link_text("Cart: ")
        actual = cart_lnk.text
        
        if quantity == 0:
            expected = "Cart: (Empty)"
            self.test.assertEquals(expected, actual)
        else:
            expected = "Cart: (" + str(quantity) + ") $" + amount
            self.test.assertEquals(expected, actual)

    def clickShoppingCart(self):
        cart_lnk = self.webDriver.find_element_by_partial_link_text("Cart: ")
        cart_lnk.click()

        from com.example.selenium.spree.cart_page import CartPage
        return CartPage(self.test)