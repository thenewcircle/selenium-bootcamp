# from com.example.selenium.spree.HomePage import HomePage
# from com.example.selenium.spree.ProductsPage import ProductsPage


class SpreePage:

    def __init__(self, test):
        self.test = test

    def getDepartmentCmb(self):
        return self.test.webDriver.find_element_by_id("taxon")

    def search(self, keywords):
        searchTF = self.test.webDriver.find_element_by_id("keywords")
        searchTF.send_keys(keywords)
        searchTF.submit()
        
        from com.example.selenium.spree.ProductsPage import ProductsPage
        return ProductsPage(self.test)

    def validateSearchText(self, expected):
        searchTF = self.test.webDriver.find_element_by_id("keywords")
        actual = searchTF.get_attribute("value")
        self.test.assertEquals(expected, actual)
        
    def clearSearch(self):
        searchTF = self.test.webDriver.find_element_by_id("keywords")
        searchTF.clear()
        
    def clickLogo(self):
        logo = self.test.webDriver.find_element_by_id("logo")
        logo.click()

        from selenium.webdriver.support.wait import WebDriverWait
        WebDriverWait(self.test.webDriver, 30).until(
            url_to_be("https://spreecommerce-demo.herokuapp.com/")
        )

        from com.example.selenium.spree.HomePage import HomePage
        return HomePage(self.test)


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
