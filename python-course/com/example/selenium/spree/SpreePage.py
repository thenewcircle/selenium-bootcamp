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
        
        from com.example.selenium.spree.HomePage import HomePage
        return HomePage(self.test)