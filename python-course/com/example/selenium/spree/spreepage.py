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

    def getDepartmentCmb(self):
        return self.test.webDriver.find_element_by_id("taxon")
    
    def search_tf(self):
        return self.test.webDriver.find_element_by_id("keywords")
    
    def search(self, keywords):
        searchTF = self.search_tf()
        searchTF.clear()
        searchTF.send_keys(keywords)
        searchTF.submit()

        from com.example.selenium.spree.productspage import ProductsPage
        return ProductsPage(self.test)

    def validateSearchText(self, keywords):
        searchTF = self.search_tf()
        actual = searchTF.get_attribute("value")
        self.test.assertEquals(keywords, actual)
        
    def clearSearch(self):
        searchTF = self.search_tf()
        searchTF.clear()
        
    def clickLogo(self):
        logo = self.test.webDriver.find_element_by_id("logo")
        logo.click()

        from com.example.selenium.spree.homepage import HomePage
        return HomePage(self.test)